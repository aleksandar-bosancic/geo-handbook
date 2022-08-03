package com.rbhp.geohandbook.ui.cities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CityListItemData;

import java.util.ArrayList;
import java.util.List;

public class CitiesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final List<CityListItemData> cities;

    public CitiesRecyclerViewAdapter(){
        cities = new ArrayList<>();
        CityListItemData city1 = new CityListItemData("Banja Luka");
        CityListItemData city2 = new CityListItemData("Prijedor");
        CityListItemData city3 = new CityListItemData("Bijeljina");
        CityListItemData city4 = new CityListItemData("Trebinje");
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
        CityListItemData city = cities.get(position);
        CitiesRecyclerViewHolder viewHolder = (CitiesRecyclerViewHolder) holder;
        viewHolder.textView.setText(city.getName());
    }

    @Override
    public int getItemCount() {
        return cities.size();
    }
}
