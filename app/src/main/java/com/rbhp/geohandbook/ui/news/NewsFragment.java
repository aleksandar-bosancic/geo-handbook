package com.rbhp.geohandbook.ui.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.NewsData;
import com.rbhp.geohandbook.databinding.FragmentNewsBinding;

import java.util.Objects;

public class NewsFragment extends Fragment implements NewsItemListener {
    private FragmentNewsBinding binding;
    private RecyclerView recyclerView;
    private NewsRecyclerViewAdapter newsRecyclerViewAdapter;
    private NewsViewModel newsViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.setViewmodel(newsViewModel);
        binding.setLifecycleOwner(requireActivity());

        recyclerView = root.findViewById(R.id.news_recycler_view);
        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(this, newsViewModel);
        newsViewModel.getNewsItemList().observe(getViewLifecycleOwner(), newsItems -> newsRecyclerViewAdapter.updateList());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(newsRecyclerViewAdapter);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onNewsItemClick(NewsData newsData) {
        Intent openNewsLink = new Intent(Intent.ACTION_VIEW, Uri.parse(newsData.getLink()));
        startActivity(openNewsLink);
    }
}