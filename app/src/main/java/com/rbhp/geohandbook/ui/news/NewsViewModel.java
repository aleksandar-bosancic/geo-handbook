package com.rbhp.geohandbook.ui.news;

import android.app.Application;
import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;

import com.rbhp.geohandbook.data.NewsData;
import com.rbhp.geohandbook.data.NewsFeed;
import com.rbhp.geohandbook.data.dao.NewsDao;
import com.rbhp.geohandbook.data.entities.NewsEntity;
import com.rbhp.geohandbook.database.Database;
import com.rbhp.geohandbook.executor.AsyncExecutor;
import com.rbhp.geohandbook.http.APIInterface;
import com.rbhp.geohandbook.http.RetrofitHttp;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends AndroidViewModel {
    private static final String TAG_CACHE_LOAD = "Picasso";

    private MutableLiveData<List<NewsData>> newsItemList;
    private NewsDao newsDao;
    private final Executor executor;

    public NewsViewModel(Application application) {
        super(application);
        executor = new AsyncExecutor();
        Database database = Room.databaseBuilder(
                        getApplication().getApplicationContext(),
                        Database.class,
                        "NewsDatabase")
                .build();
        newsDao = database.newsDao();

        newsItemList = new MutableLiveData<>();

        APIInterface apiInterface = RetrofitHttp.getRetrofit().create(APIInterface.class);

        apiInterface.getNews().enqueue(new Callback<NewsFeed>() {
            @Override
            public void onResponse(@NonNull Call<NewsFeed> call, @NonNull Response<NewsFeed> response) {
                if (response.body() != null) {
                    List<NewsData> items = response.body().getItems();
                    newsItemList.setValue(items);
                    executor.execute(persistToDb);
                }
            }

            @Override
            public void onFailure(@NonNull Call<NewsFeed> call, @NonNull Throwable t) {
                Log.e("failed request", "couldn't get news data");
                executor.execute(getFromDb);
            }
        });
    }

    Runnable persistToDb = () -> {
        if (newsItemList.getValue() == null) {
            return;
        }
        newsDao.deleteAll();
        List<NewsEntity> entities = newsItemList.getValue().stream()
                .map(item -> new NewsEntity(item.getTitle(), item.getPubDate(), item.getLink(), item.getGuid(), item.getAuthor(), item.getDescription(), item.getContent(), item.getEnclosure().getLink())).collect(Collectors.toList());
        newsDao.insertAll(entities);
    };

    Runnable getFromDb = () -> {
        List<NewsEntity> persistedNews = newsDao.getAll();
        newsItemList.postValue(persistedNews.stream().map(item -> {
            NewsData newsData = new NewsData(item.title, item.pubDate, item.link, item.guid, item.author, item.description, item.content);
            NewsData.Enclosure enclosure = newsData.new Enclosure(item.enclosureLink, item.enclosureType);
            newsData.setEnclosure(enclosure);
            return newsData;
        }).collect(Collectors.toList()));
    };

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, NewsData newsData) {
        Picasso.get()
                .load(newsData.getEnclosure().getLink())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        Log.i(TAG_CACHE_LOAD, "News onSuccess: Loaded from cache");
                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(newsData.getEnclosure().getLink())
                                .into(imageView);
                    }
                });
    }

    public MutableLiveData<List<NewsData>> getNewsItemList() {
        return newsItemList;
    }

    public void setNewsItemList(MutableLiveData<List<NewsData>> newsItemList) {
        this.newsItemList = newsItemList;
    }
}