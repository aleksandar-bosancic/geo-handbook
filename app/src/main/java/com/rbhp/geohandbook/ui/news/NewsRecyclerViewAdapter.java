package com.rbhp.geohandbook.ui.news;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.NewsData;

public class NewsRecyclerViewAdapter extends RecyclerView.Adapter<NewsRecyclerViewHolder> {
    private final NewsViewModel viewModel;
    private final NewsItemListener newsItemListener;

    public NewsRecyclerViewAdapter(NewsItemListener newsItemListener, NewsViewModel viewModel) {
        this.viewModel = viewModel;
        this.newsItemListener = newsItemListener;
    }

    @NonNull
    @Override
    public NewsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_news,
                parent,
                false);
        return new NewsRecyclerViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerViewHolder holder, int position) {
        if (viewModel.getNewsItemList().getValue() != null) {
            NewsData newsData = viewModel.getNewsItemList().getValue().get(position);
            holder.bind(newsData, viewModel, newsItemListener);
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    public void updateList() {
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (viewModel.getNewsItemList().getValue() == null) {
            return 0;
        }
        return viewModel.getNewsItemList().getValue().size();
    }
}
