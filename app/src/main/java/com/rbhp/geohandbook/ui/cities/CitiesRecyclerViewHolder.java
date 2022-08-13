package com.rbhp.geohandbook.ui.cities;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;

public class CitiesRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final CityListener cityListener;
    TextView textView;
    Button button;

    public CitiesRecyclerViewHolder(@NonNull View itemView, CityListener cityListener) {
        super(itemView);
        this.cityListener = cityListener;
        textView = itemView.findViewById(R.id.textView);
        button = itemView.findViewById(R.id.sample_button);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        this.cityListener.OnCityClick(getBindingAdapterPosition());
    }
}
