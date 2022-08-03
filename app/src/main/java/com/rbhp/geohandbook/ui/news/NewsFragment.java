package com.rbhp.geohandbook.ui.news;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.databinding.FragmentNewsBinding;

public class NewsFragment extends Fragment {

    private static final String TAG = "huha";
    private FragmentNewsBinding binding;
    private RecyclerView recyclerView;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        NewsViewModel newsViewModel =
                new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.setViewmodel(newsViewModel);
        binding.setLifecycleOwner(this);

        recyclerView = root.findViewById(R.id.news_recycler_view);
        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter();
        newsViewModel.getNewsItemList().observe(getViewLifecycleOwner(), newsItems -> {
            Log.println(Log.ASSERT,TAG, "onCreateView: ovdje");
            newsRecyclerViewAdapter.updateList(newsItems);
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(newsRecyclerViewAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}