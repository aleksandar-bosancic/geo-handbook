package com.rbhp.geohandbook.ui.attractions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.databinding.FragmentAttractionsBinding;

public class AttractionsFragment extends Fragment {
    private FragmentAttractionsBinding binding;
    private RecyclerView recyclerView;
    private AttractionsRecyclerViewAdapter recyclerViewAdapter;
    private AttractionsViewModel viewModel;
    private MaterialButtonToggleGroup toggleGroup;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(AttractionsViewModel.class);
        binding = FragmentAttractionsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);

        recyclerView = root.findViewById(R.id.attractions_recycler_view);
        recyclerViewAdapter = new AttractionsRecyclerViewAdapter(viewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(recyclerViewAdapter);

        toggleGroup = root.findViewById(R.id.toggle_button_group);
        toggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                if (checkedId == R.id.all_attractions_button) {
                    viewModel.updateFavourites();
                    recyclerViewAdapter.setFavouritesSelected(false);
                } else {
                    viewModel.updateFavourites();
                    recyclerViewAdapter.setFavouritesSelected(true);
                }
            }
        });

        viewModel.getClickedAttractionMap().observe(getViewLifecycleOwner(), attractionData -> {
            //TODO mapa za pritisnut marker, za favourite i za sve ostale
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getFavouritesSelected().observe(getViewLifecycleOwner(),
                aBoolean -> {
                    viewModel.updateFavourites();
                    recyclerViewAdapter.setFavouritesSelected(aBoolean);
                });
    }
}