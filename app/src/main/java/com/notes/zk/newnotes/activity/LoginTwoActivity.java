package com.notes.zk.newnotes.activity;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.databinding.DataBindingUtil;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;

import com.notes.zk.newnotes.R;
import com.notes.zk.newnotes.databinding.ActivityLoginTwoBinding;

import java.io.IOException;

public class LoginTwoActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityLoginTwoBinding binding;
    private MediaPlayer mediaPlayer;
    private int mCurrentPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.hide();
        }
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_two);
        binding.setOnClickListener(this);

        init();
        hideBars();
    }

    private void init(){
        mediaPlayer = new MediaPlayer();
        binding.loginTwoBgSv.getHolder().setKeepScreenOn(true);
        binding.loginTwoBgSv.getHolder().addCallback(new SurfaceViewLis());

        mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START)
                    binding.loginTwoBgIv.setVisibility(View.GONE);
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_two_login_btn:
                Intent intent = new Intent();
                intent.setClass(this, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    private class SurfaceViewLis implements SurfaceHolder.Callback {

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {

        }

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                play();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {

        }

    }

    public void play() throws IllegalArgumentException, SecurityException,
            IllegalStateException, IOException {
        mediaPlayer.reset();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        AssetFileDescriptor fd = getResources().openRawResourceFd(R.raw.mox);
        mediaPlayer.setDataSource(fd.getFileDescriptor(), fd.getStartOffset(),
                fd.getLength());
        mediaPlayer.setLooping(true);
        mediaPlayer.setDisplay(binding.loginTwoBgSv.getHolder());
        // 通过异步的方式装载媒体资源
        mediaPlayer.prepareAsync();
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                // 装载完毕回调
                mediaPlayer.seekTo(mCurrentPosition);
                mediaPlayer.start();
//                mHandler.sendEmptyMessage(SURFACE_VIEW_SHOW);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
//        if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
//
//            mediaPlayer.start();
//        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            mCurrentPosition = mediaPlayer.getCurrentPosition();
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                mediaPlayer.release();
                mediaPlayer = null;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void finish() {
        super.finish();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    private void hideBars() {
        binding.loginTwoBgSv.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }
}
