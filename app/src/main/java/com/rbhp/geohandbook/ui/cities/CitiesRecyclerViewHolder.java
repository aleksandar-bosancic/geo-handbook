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
import com.squareup.picasso.Picasso;

public class CitiesRecyclerViewHolder extends RecyclerView.ViewHolder {
    private CityData city;
    private final TextView textView;
    private final Button button;
    private final ViewDataBinding binding;
    private final ImageView imageView;

    public CitiesRecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
        textView = itemView.findViewById(R.id.title_text_view);
        button = itemView.findViewById(R.id.show_on_map_button);
        imageView = itemView.findViewById(R.id.city_image_view);
    }

    public void bind(Object object, CityListener listener) {
        binding.setVariable(BR.city, object);
        binding.setVariable(BR.listener, listener);
        binding.setVariable(BR.viewModel, listener);
        binding.executePendingBindings();
    }

    public CityData getCity() {
        return city;
    }

    public void setCity(CityData city) {
        this.city = city;
    }
}
