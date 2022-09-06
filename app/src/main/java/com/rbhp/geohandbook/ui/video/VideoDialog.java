package com.rbhp.geohandbook.ui.video;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.YouTubePlayerUtils;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
import com.rbhp.geohandbook.R;
import com.rbhp.geohandbook.databinding.DialogVideoBinding;

public class VideoDialog extends DialogFragment {
    private String videoUrl;

    public VideoDialog() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DialogVideoBinding binding = DataBindingUtil.inflate(inflater, R.layout.dialog_video, container, false);
        binding.setVideourl("nesto");
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        YouTubePlayerView videoView = requireView().findViewById(R.id.video_view);
//        requireActivity().getLifecycle().addObserver(videoView);
//        videoView.getYouTubePlayerWhenReady(youTubePlayer -> youTubePlayer.loadVideo(cityData.getVideoUrl(), 0));
        videoView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                YouTubePlayerUtils.loadOrCueVideo(youTubePlayer, requireActivity().getLifecycle(), cityData.getVideoUrl(), 0f);
            }
        });
//        MediaController mediaController = new MediaController(this.getContext());
//        mediaController.setAnchorView(videoView);
//        videoView.setMediaController(mediaController);
//        videoView.setVideoPath("https://youtu.be/Nzuiwri8RSU");
//        videoView.start();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        return dialog;
    }
}
