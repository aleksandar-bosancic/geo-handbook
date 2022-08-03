package com.rbhp.geohandbook.ui.news;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;

public class NewsRecyclerViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    TextView titleText;
    TextView dateText;
    TextView authorText;
    TextView summaryText;

    public NewsRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.news_image);
        titleText = itemView.findViewById(R.id.news_title_text);
        dateText = itemView.findViewById(R.id.news_date_text);
        authorText = itemView.findViewById(R.id.news_author_text);
        summaryText = itemView.findViewById(R.id.news_summary_text);
    }
}
