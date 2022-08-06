package com.rbhp.geohandbook.ui.news;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;

public class NewsRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private NewsItemListener newsItemListener;
    private ImageView imageView;
    private TextView titleText;

    public NewsRecyclerViewHolder(@NonNull View itemView, NewsItemListener newsItemListener) {
        super(itemView);
        imageView = itemView.findViewById(R.id.news_image);
        titleText = itemView.findViewById(R.id.news_title_text);
        this.newsItemListener = newsItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.newsItemListener.onNewsItemClick(getBindingAdapterPosition());
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public TextView getTitleText() {
        return titleText;
    }

    public void setTitleText(TextView titleText) {
        this.titleText = titleText;
    }
}
