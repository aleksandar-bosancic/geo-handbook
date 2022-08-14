package com.rbhp.geohandbook.ui.cities;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CityData;

public class CitiesRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final CityListener cityListener;
    private CityData city;
    TextView textView;
    Button button;
    ImageView cityImageView;
    ViewDataBinding binding;

    public CitiesRecyclerViewHolder(@NonNull View itemView, CityListener cityListener) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        this.cityListener = cityListener;
        textView = itemView.findViewById(R.id.title_text_view);
        cityImageView = itemView.findViewById(R.id.city_image_view);
        button = itemView.findViewById(R.id.show_on_map_button);
        button.setOnClickListener(this);
        itemView.setOnClickListener(this);
    }

    public void bind(Object object) {
        binding.setVariable(BR.city, object);
        binding.executePendingBindings();
    }

    @Override
    public void onClick(View v) {
        this.cityListener.OnCityClick(getBindingAdapterPosition());
    }

    public CityData getCity() {
        return city;
    }

    public void setCity(CityData city) {
        this.city = city;
    }
}
