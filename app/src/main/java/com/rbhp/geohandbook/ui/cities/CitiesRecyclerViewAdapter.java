package com.rbhp.geohandbook.ui.cities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.CityListItemData;

import java.util.List;

public class CitiesRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CityListItemData> cities;

    public CitiesRecyclerViewAdapter(){
        CityListItemData city1 = new CityListItemData("Banja Luka");
        CityListItemData city2 = new CityListItemData("Prijedor");
        CityListItemData city3 = new CityListItemData("Bijeljina");
        CityListItemData city4 = new CityListItemData("Trebinje");
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

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
