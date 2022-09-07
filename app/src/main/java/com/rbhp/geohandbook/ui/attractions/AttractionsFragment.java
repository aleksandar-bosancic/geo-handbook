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
import androidx.navigation.Navigation;
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
        binding.setLifecycleOwner(requireActivity());

        recyclerView = root.findViewById(R.id.attractions_recycler_view);
        recyclerViewAdapter = new AttractionsRecyclerViewAdapter(viewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(recyclerViewAdapter);

        toggleGroup = root.findViewById(R.id.toggle_button_group);
        toggleGroup.addOnButtonCheckedListener((group, checkedId, isChecked) -> {
            if (isChecked) {
                recyclerViewAdapter.setFavouritesSelected(checkedId != R.id.all_attractions_button);
            }
        });

        viewModel.getClickedAttractionMap().observe(getViewLifecycleOwner(), attractionData -> {
            if (attractionData == null) {
                return;
            }
            Bundle arguments = new Bundle();
            arguments.putBoolean("Attraction", true);
            Navigation.findNavController(requireView()).navigate(R.id.attraction_navigate_to_maps, arguments);
        });
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getFavouritesSelected().observe(getViewLifecycleOwner(),
                aBoolean -> recyclerViewAdapter.setFavouritesSelected(aBoolean));
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (viewModel != null) {
            viewModel.persistFavourites();
        }
        binding = null;
    }
}