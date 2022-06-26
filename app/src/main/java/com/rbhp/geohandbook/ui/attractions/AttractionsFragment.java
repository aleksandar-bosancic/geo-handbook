package com.rbhp.geohandbook.ui.attractions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rbhp.geohandbook.databinding.FragmentAttractionsBinding;

public class AttractionsFragment extends Fragment {
    private FragmentAttractionsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        AttractionsViewModel attractionsViewModel =
                new ViewModelProvider(this).get(AttractionsViewModel.class);

        binding = FragmentAttractionsBinding.inflate(inflater, container, false);

        binding.setViewmodel(attractionsViewModel);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }

}