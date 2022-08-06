package com.rbhp.geohandbook.ui.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.data.NewsItem;
import com.rbhp.geohandbook.databinding.FragmentNewsBinding;

import java.util.Objects;

public class NewsFragment extends Fragment implements NewsItemListener {

    private static final String TAG = "huha";
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
        binding.setLifecycleOwner(this);

        recyclerView = root.findViewById(R.id.news_recycler_view);
        newsRecyclerViewAdapter = new NewsRecyclerViewAdapter(this);
        newsViewModel.getNewsItemList().observe(getViewLifecycleOwner(), newsItems -> {
            Log.println(Log.ASSERT, TAG, "onCreateView: ovdje");
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

    @Override
    public void onNewsItemClick(int newsItemPosition) {
        NewsItem news = Objects.requireNonNull(newsViewModel.getNewsItemList().getValue()).get(newsItemPosition);
        Intent openNewsLink = new Intent(Intent.ACTION_VIEW, Uri.parse(news.getLink()));
        startActivity(openNewsLink);
    }
}