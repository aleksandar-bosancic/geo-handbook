package com.rbhp.geohandbook.ui.news;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.NewsData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NewsData> newsDataList;
    private final NewsItemListener newsItemListener;

    public NewsRecyclerViewAdapter(NewsItemListener newsItemListener) {
        this.newsItemListener = newsItemListener;
        newsDataList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news, parent, false);
        return new NewsRecyclerViewHolder(view, newsItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewsData item = newsDataList.get(position);
        NewsRecyclerViewHolder viewHolder = (NewsRecyclerViewHolder) holder;
        Picasso.get().load(item.getEnclosure().getLink()).into(viewHolder.getImageView());
        viewHolder.getTitleText().setText(item.getTitle());
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList(List<NewsData> news) {
        newsDataList = null;
        this.newsDataList = new ArrayList<>(news);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return newsDataList.size();
    }
}
