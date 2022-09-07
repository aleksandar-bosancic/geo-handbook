package com.rbhp.geohandbook.ui.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.databinding.FragmentVideoBinding;

public class VideoFragment extends Fragment {
    private String videoUrl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentVideoBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_video, container, false);
        if (getArguments() != null) {
            videoUrl = getArguments().getString("videoUrl");
        }
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (videoUrl == null || videoUrl.isEmpty()){
            view.findViewById(R.id.video_not_available_text).setVisibility(View.VISIBLE);
            view.findViewById(R.id.video_view).setVisibility(View.GONE);
        }
        YouTubePlayerView videoView = requireView().findViewById(R.id.video_view);
        videoView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                YouTubePlayerUtils.loadOrCueVideo(youTubePlayer, requireActivity().getLifecycle(), videoUrl, 0f);
            }
        });
    }
}
