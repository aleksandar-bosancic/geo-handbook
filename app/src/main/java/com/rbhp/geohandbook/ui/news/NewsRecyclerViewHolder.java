package com.rbhp.geohandbook.ui.news;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.BR;
import com.rbhp.geohandbook.data.NewsData;

public class NewsRecyclerViewHolder extends RecyclerView.ViewHolder {
    private final ViewDataBinding binding;

    public NewsRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void bind(NewsData newsData, NewsViewModel viewModel, NewsItemListener listener) {
        binding.setVariable(BR.viewModel, viewModel);
        binding.setVariable(BR.newsdata, newsData);
        binding.setVariable(BR.listener, listener);
        binding.executePendingBindings();
    }
}
