package com.notes.zk.newnotes.activity;

import android.annotation.SuppressLint;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.notes.zk.newnotes.R;
import com.notes.zk.newnotes.databinding.ActivityFullscreenBinding;

import java.lang.ref.WeakReference;

public class FullscreenActivity extends AppCompatActivity {
    private boolean isShowBar;
    private ActivityFullscreenBinding binding;

    private FullscreenActivityHandler fullscreenActivityHandler;

    private final int MESSAGE_HIDE_BARS = 0x001;
    private final int MESSAGE_VIDEO_PREPARED = 0x002;

    private void handleMessage(Message msg) {
        switch (msg.what) {
            case MESSAGE_HIDE_BARS:
                hideBars();
                break;
            case MESSAGE_VIDEO_PREPARED:
                Animation animation = new AlphaAnimation(1.0f, 0.0f);
                animation.setDuration(500);
                binding.fullscreenRl.startAnimation(animation);
                binding.fullscreenRl.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fullscreen);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.hide();
        }

        fullscreenActivityHandler = new FullscreenActivityHandler(this);
    }

    private static class FullscreenActivityHandler extends Handler {
        private final WeakReference<FullscreenActivity> fullscreenActivityWeakReference;

        FullscreenActivityHandler(FullscreenActivity fullscreenActivity) {
            super();
            fullscreenActivityWeakReference = new WeakReference<>(fullscreenActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (fullscreenActivityWeakReference.get() != null) {
                fullscreenActivityWeakReference.get().handleMessage(msg);
            }
        }
    }


    @SuppressLint("ClickableViewAccessibility")
    private void playVideo() {
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.full_screen_google);
        binding.fullscreenVv.setVideoURI(uri);
        binding.fullscreenVv.start();

        binding.fullscreenVv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                fullscreenActivityHandler.sendEmptyMessageDelayed(MESSAGE_VIDEO_PREPARED, 500);
            }
        });

        binding.fullscreenVv.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                binding.fullscreenVv.start();
            }
        });

        binding.fullscreenVv.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                binding.fullscreenPb.setVisibility(View.VISIBLE);
                return true;
            }
        });

        binding.fullscreenVv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    if (!isShowBar) {
                        showBars();
                    } else {
                        hideBars();
                    }
                return true;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideBars();
        playVideo();
    }

    @Override
    protected void onStop() {
        super.onStop();
        binding.fullscreenRl.setVisibility(View.VISIBLE);
    }

    private void showBars() {
        isShowBar = true;
        binding.fullscreenVv.setSystemUiVisibility(View.VISIBLE);

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.GRAY);
        window.setNavigationBarColor(Color.GRAY);

        fullscreenActivityHandler.removeMessages(MESSAGE_HIDE_BARS);
        fullscreenActivityHandler.sendEmptyMessageDelayed(MESSAGE_HIDE_BARS, 2000);

    }

    private void hideBars() {
        isShowBar = false;
        binding.fullscreenVv.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

    @Override
    protected void onDestroy() {
        fullscreenActivityHandler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
