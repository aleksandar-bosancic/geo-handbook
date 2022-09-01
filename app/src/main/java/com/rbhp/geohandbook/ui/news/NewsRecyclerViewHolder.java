package com.rbhp.geohandbook.ui.news;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.BR;
import com.rbhp.geohandbook.data.NewsData;

public class NewsRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final ViewDataBinding binding;

    public NewsRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
//        this.newsItemListener.onNewsItemClick(getBindingAdapterPosition());
    }

    public void bind(NewsData newsData, NewsViewModel viewModel) {
        binding.setVariable(BR.viewModel, viewModel);
        binding.setVariable(BR.newsdata, newsData);
        binding.executePendingBindings();
    }
}
