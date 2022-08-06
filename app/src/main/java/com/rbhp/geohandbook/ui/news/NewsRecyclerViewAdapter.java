package com.rbhp.geohandbook.ui.news;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.NewsItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<NewsItem> newsItemList;
    private NewsItemListener newsItemListener;

    public NewsRecyclerViewAdapter(NewsItemListener newsItemListener) {
        this.newsItemListener = newsItemListener;
        newsItemList = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_news, parent, false);
        return new NewsRecyclerViewHolder(view, newsItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        NewsItem item = newsItemList.get(position);
        NewsRecyclerViewHolder viewHolder = (NewsRecyclerViewHolder) holder;
        Picasso.get().load(item.getEnclosure().getLink()).into(viewHolder.getImageView());
        viewHolder.getTitleText().setText(item.getTitle());
    }

    public void updateList(List<NewsItem> news) {
        newsItemList = null;
        this.newsItemList = new ArrayList<>(news);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return newsItemList.size();
    }
}
