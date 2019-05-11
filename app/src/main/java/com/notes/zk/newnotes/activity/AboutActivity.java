package com.notes.zk.newnotes.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.notes.zk.newnotes.R;
import com.notes.zk.newnotes.constant.Constant;
import com.notes.zk.newnotes.databinding.ActivityAboutBinding;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityAboutBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_about);
        binding.setOnClickListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

//        getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimary));

        initView();
    }

    private void initView() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_about_card_show);
        binding.scrollAbout.startAnimation(animation);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(300);
        alphaAnimation.setStartOffset(600);

        binding.cardAbout1.aboutVersionTv.setText(getVersionName(this));
        binding.cardAbout1.aboutVersionTv.startAnimation(alphaAnimation);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public String getVersionName(Context context) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            return context.getString(R.string.about_version) + " " + version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.card_about_2_shop_ll:
                Toast.makeText(AboutActivity.this, getString(R.string.about_google_play_error), Toast.LENGTH_SHORT).show();
                break;
            case R.id.card_about_2_email_ll:
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse(Constant.EMAIL));
                intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.about_email_intent));
                //intent.putExtra(Intent.EXTRA_TEXT, "Hi,");
                try {
                    startActivity(intent);
                } catch (Exception e) {
                    Toast.makeText(AboutActivity.this, getString(R.string.about_not_found_email), Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.card_about_2_git_hub_ll:
                intent.setData(Uri.parse(Constant.GIT_HUB));
                intent.setAction(Intent.ACTION_VIEW);
                startActivity(intent);
                break;
            case R.id.card_about_2_location_ll:
                break;
            case R.id.card_about_source_licenses:
                Toast.makeText(AboutActivity.this, getString(R.string.feature_is_not_completed), Toast.LENGTH_SHORT).show();
                break;
            case R.id.fab_about_share:
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
