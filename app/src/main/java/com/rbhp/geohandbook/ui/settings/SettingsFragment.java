package com.rbhp.geohandbook.ui.settings;

import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.NumberInputFilter;
import com.rbhp.geohandbook.databinding.FragmentSettingsBinding;

import java.util.Objects;

public class SettingsFragment extends Fragment implements View.OnFocusChangeListener {
    private SettingsViewModel viewModel;
    private FragmentSettingsBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(requireActivity()).get(SettingsViewModel.class);

        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        binding.setLifecycleOwner(requireActivity());
        binding.setViewmodel(viewModel);

        EditText cache = root.findViewById(R.id.cache_size_edit_text);
        EditText images = root.findViewById(R.id.number_of_images_edit_text);
        cache.setFilters(new InputFilter[]{new NumberInputFilter(1, 200)});
        cache.setOnFocusChangeListener(this);
        images.setFilters(new InputFilter[]{new NumberInputFilter(1, 10)});
        images.setOnFocusChangeListener(this);

        View buttons = root.findViewById(R.id.decision_buttons);

        viewModel.initialSelectedLanguage();

        viewModel.getCheckedLanguage().observe(getViewLifecycleOwner(), integer -> {
            if (!Objects.equals(viewModel.getInitialLanguage(), integer)) {
                String languageCode = (integer == R.id.sr) ? "sr" : "en";
                viewModel.setLocale(languageCode);
                requireActivity().recreate();
            }
        });

        viewModel.getCacheSize().observe(getViewLifecycleOwner(), integer -> {
            if (!Objects.equals(integer, viewModel.getInitialCacheSize())) {
                buttons.setVisibility(View.VISIBLE);
            }
        });

        viewModel.getNumberOfImages().observe(getViewLifecycleOwner(), integer -> {
            if (!Objects.equals(integer, viewModel.getInitialNumberOfImages())) {
                buttons.setVisibility(View.VISIBLE);
            }
        });

        viewModel.getSomethingChanged().observe(getViewLifecycleOwner(), aBoolean -> {
            if (!aBoolean) {
                buttons.setVisibility(View.GONE);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        ((EditText) v).setCursorVisible(hasFocus);
    }
}