package com.rbhp.geohandbook.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsFeed {
    @SerializedName("items")
    List<NewsItem> items = null;

    public List<NewsItem> getItems() {
        return items;
    }

    public void setItems(List<NewsItem> items) {
        this.items = items;
    }
}
