package com.rbhp.geohandbook.ui.attractions;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.AttractionData;

public class AttractionsRecyclerViewAdapter extends RecyclerView.Adapter<AttractionsRecyclerViewHolder> {
    private final AttractionsViewModel viewModel;
    private boolean favouritesSelected = false;

    public AttractionsRecyclerViewAdapter(AttractionsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public AttractionsRecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.list_item_attraction,
                parent,
                false);
        return new AttractionsRecyclerViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AttractionsRecyclerViewHolder holder, int position) {
        AttractionData attractionData = (favouritesSelected) ?
                viewModel.getFavouriteAttractionLiveData().getValue().get(position) :
                viewModel.getAttractionLiveData().getValue().get(position);
        holder.bind(attractionData, viewModel);
    }

    @Override
    public int getItemCount() {
        return (favouritesSelected) ?
                viewModel.getFavouriteAttractionLiveData().getValue().size() :
                viewModel.getAttractionLiveData().getValue().size();
    }

    public boolean isFavouritesSelected() {
        return favouritesSelected;
    }

    public void setFavouritesSelected(boolean favouritesSelected) {
        this.favouritesSelected = favouritesSelected;
        notifyDataSetChanged();
    }
}
