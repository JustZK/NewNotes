package com.notes.zk.newnotes.activity;

import android.animation.ObjectAnimator;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;

import com.notes.zk.newnotes.R;
import com.notes.zk.newnotes.databinding.ActivityShareViewBinding;

public class ShareViewActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityShareViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_share_view);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_share_view);
        binding.setOnClickListener(this);

        binding.shareViewToolbar.setSubtitle("Shared Element Transitions");
        binding.shareViewToolbar.setNavigationIcon(R.drawable.ic_close_white_24dp);
        setSupportActionBar(binding.shareViewToolbar);

        // Let the logic of click navigation arrow the same as back key , or has not the animation
        // 让点击导航箭头的逻辑与后退键相同，或者没有动画
        binding.shareViewToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        binding.shareViewCv.setOnTouchListener(touchListener);

        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
        alphaAnimation.setDuration(1500);
        alphaAnimation.setStartOffset(1000);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                binding.shareViewTipTv.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        binding.shareViewTipTv.startAnimation(alphaAnimation);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            switch (motionEvent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    ObjectAnimator upAnim = ObjectAnimator.ofFloat(view, "translationZ", 0);
                    upAnim.setDuration(200);
                    upAnim.setInterpolator(new DecelerateInterpolator());
                    upAnim.start();
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    ObjectAnimator downAnim = ObjectAnimator.ofFloat(view, "translationZ", 20);
                    downAnim.setDuration(200);
                    downAnim.setInterpolator(new AccelerateInterpolator());
                    downAnim.start();
                    break;
            }
            return false;
        }
    };
}
