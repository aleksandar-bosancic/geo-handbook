package com.rbhp.geohandbook.ui.settings;

import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rbhp.geohandbook.MainActivity;
import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.NumberInputFilter;
import com.rbhp.geohandbook.databinding.FragmentSettingsBinding;
import com.rbhp.geohandbook.util.LocaleHelper;

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

        viewModel.getCheckedLanguage().observe(getViewLifecycleOwner(), integer -> {
            Toast.makeText(requireContext(), integer, Toast.LENGTH_SHORT).show();
//            viewModel.setLocale(requireActivity(), "en");
//            ((MainActivity)requireActivity()).updateBaseContextLocale(requireContext(), "sr");
            LocaleHelper.setLocale(requireContext(), "sr");
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
        if (!hasFocus) {
            Log.println(Log.ASSERT, "lost", v.toString());
        }
    }
}