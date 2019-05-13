package com.notes.zk.newnotes.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.notes.zk.newnotes.R;
import com.notes.zk.newnotes.constant.Constant;
import com.notes.zk.newnotes.databinding.ActivityScrollingBinding;

public class ScrollingActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityScrollingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_scrolling);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scrolling);
        binding.setOnClickListener(this);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

        setSupportActionBar(binding.scrollingToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Glide.with(this).load(R.drawable.material_design_3).apply(new RequestOptions().fitCenter()).into(binding.scrollingTopIv);

    }

    @Override
    protected void onResume() {
        super.onResume();

        Configuration configuration = getResources().getConfiguration();
        if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            CollapsingToolbarLayout collapsing_toolbar_layout = findViewById(R.id.collapsing_toolbar_layout);
            collapsing_toolbar_layout.setExpandedTitleTextColor(ColorStateList.valueOf(Color.TRANSPARENT));
        } else {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.scrolling_fab:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, Constant.SHARE_CONTENT);
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, getString(R.string.share_with)));
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
