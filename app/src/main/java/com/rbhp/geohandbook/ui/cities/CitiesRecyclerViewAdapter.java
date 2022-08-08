package com.rbhp.geohandbook.ui.cities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CityData;

import java.util.ArrayList;
import java.util.List;

public class CitiesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CityData> cities;

    public CitiesRecyclerViewAdapter(CitiesViewModel viewModel){
        cities = new ArrayList<>();
        CityData city1 = new CityData("Banja Luka");
        CityData city2 = new CityData("Prijedor");
        CityData city3 = new CityData("Bijeljina");
        CityData city4 = new CityData("Trebinje");
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        cities.add(city4);
        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
        cities.add(city4);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_city, parent, false);
        return new CitiesRecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CityData city = cities.get(position);
        CitiesRecyclerViewHolder viewHolder = (CitiesRecyclerViewHolder) holder;
        viewHolder.textView.setText(city.getName());
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
