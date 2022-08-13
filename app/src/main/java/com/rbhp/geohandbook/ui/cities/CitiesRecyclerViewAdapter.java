package com.rbhp.geohandbook.ui.cities;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CityData;

import java.util.List;

public class CitiesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<CityData> cities;
    private final CityListener cityListener;

    public CitiesRecyclerViewAdapter(CitiesViewModel viewModel, CityListener cityListener) {
        this.cityListener = cityListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_city, parent, false);
        return new CitiesRecyclerViewHolder(view, cityListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CityData city = cities.get(position);
        CitiesRecyclerViewHolder viewHolder = (CitiesRecyclerViewHolder) holder;
        viewHolder.textView.setText(city.getName());
        Log.println(Log.ASSERT, "cities", "bindviewholder");
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }

    public List<CityData> getCities() {
        return cities;
    }

    public void setCities(List<CityData> cities) {
        this.cities = cities;
    }
}
