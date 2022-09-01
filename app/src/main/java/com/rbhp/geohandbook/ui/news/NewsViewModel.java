package com.rbhp.geohandbook.ui.news;

import android.util.Log;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rbhp.geohandbook.data.CityData;
import com.rbhp.geohandbook.data.NewsData;
import com.rbhp.geohandbook.data.NewsFeed;
import com.rbhp.geohandbook.http.APIInterface;
import com.rbhp.geohandbook.http.RetrofitHttp;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {
    private MutableLiveData<List<NewsData>> newsItemList;

    public NewsViewModel() {
        newsItemList = new MutableLiveData<>();


        APIInterface apiInterface = RetrofitHttp.getRetrofit().create(APIInterface.class);

        apiInterface.getNews().enqueue(new Callback<NewsFeed>() {
            @Override
            public void onResponse(@NonNull Call<NewsFeed> call, @NonNull Response<NewsFeed> response) {
                if (response.body() != null) {
                    List<NewsData> items = response.body().getItems();
                    newsItemList.setValue(items);
                }

            }

            @Override
            public void onFailure(@NonNull Call<NewsFeed> call, @NonNull Throwable t) {
                Log.e("failed request", "couldn't get news data");
            }
        });

    }

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView, NewsData newsData) {
        Picasso.get()
                .load(newsData.getEnclosure().getLink())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .into(imageView, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        // Do nothing
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