package com.rbhp.geohandbook.ui.attractions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.databinding.FragmentAttractionsBinding;

public class AttractionsFragment extends Fragment {
    private FragmentAttractionsBinding binding;
    private RecyclerView recyclerView;
    private AttractionsRecyclerViewAdapter recyclerViewAdapter;
    private AttractionsViewModel viewModel;

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

        return binding.getRoot();
    }

}