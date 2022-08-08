package com.rbhp.geohandbook.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NewsFeed {
    @SerializedName("items")
    List<NewsData> items = null;

    public List<NewsData> getItems() {
        return items;
    }

    public void setItems(List<NewsData> items) {
        this.items = items;
    }
}
