package com.rbhp.geohandbook.ui.news;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.rbhp.geohandbook.data.NewsFeed;
import com.rbhp.geohandbook.data.NewsData;
import com.rbhp.geohandbook.http.APIInterface;
import com.rbhp.geohandbook.http.RetrofitHttp;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<String> mEnteredText;
    private MutableLiveData<List<NewsData>> newsItemList;

    public NewsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
        mEnteredText = new MutableLiveData<>();
        newsItemList = new MutableLiveData<>();


        APIInterface apiInterface = RetrofitHttp.getRetrofit().create(APIInterface.class);

        apiInterface.getNews().enqueue(new Callback<NewsFeed>() {
            @Override
            public void onResponse(@NonNull Call<NewsFeed> call, @NonNull Response<NewsFeed> response) {
                if (response.body() != null) {
                    List<NewsData> items = response.body().getItems();
                    newsItemList.setValue(items);
                    for (NewsData item : items) {
                        Log.println(Log.ASSERT, "title", item.getTitle());
                    }
                }

            }

            @Override
            public void onFailure(@NonNull Call<NewsFeed> call, @NonNull Throwable t) {
                Log.e("failed request", "couldn't get news data");
            }
        });

    }

    public MutableLiveData<List<NewsData>> getNewsItemList() {
        return newsItemList;
    }

    public void setNewsItemList(MutableLiveData<List<NewsData>> newsItemList) {
        this.newsItemList = newsItemList;
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<String> getEnteredText() {
        return mEnteredText;
    }

    public void setEnteredText(String mEnteredText) {
        this.mEnteredText.setValue(mEnteredText);
    }

    public void setText(String text) {
        this.mText.setValue(text);
    }
}