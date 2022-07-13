package com.rbhp.geohandbook.ui.cities;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;

public class CitiesRecyclerViewHolder extends RecyclerView.ViewHolder {
    TextView textView;
    Button button;

    public CitiesRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        textView = itemView.findViewById(R.id.textView);
        button = itemView.findViewById(R.id.sample_button);
    }
}
