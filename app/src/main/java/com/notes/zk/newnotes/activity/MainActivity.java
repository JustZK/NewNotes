package com.notes.zk.newnotes.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.notes.zk.newnotes.R;
import com.notes.zk.newnotes.adapter.FragmentAdapter;
import com.notes.zk.newnotes.databinding.ActivityMainBinding;
import com.notes.zk.newnotes.databinding.NavHeaderMainBinding;
import com.notes.zk.newnotes.fragment.CardsFragment;
import com.notes.zk.newnotes.fragment.DialogsFragment;
import com.notes.zk.newnotes.fragment.WidgetsFragment;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ActivityMainBinding binding;

    private final int MESSAGE_SHOW_DRAWER_LAYOUT = 0x01;
    private final int MESSAGE_SHOW_START_PAGE = 0x02;

    private boolean showPageStart = true;

    private MainActivityHandler mainActivityHandler;

    private void handleMessage(Message msg) {
        switch (msg.what) {
            case MESSAGE_SHOW_DRAWER_LAYOUT:

                break;
            case MESSAGE_SHOW_START_PAGE:
                AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
                alphaAnimation.setDuration(2 * 1000);
                alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        binding.mainPageStart.mainRelative.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                binding.mainPageStart.mainRelative.startAnimation(alphaAnimation);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setOnClickListener(this);
        binding.mainNavView.setNavigationItemSelectedListener(this);

        NavHeaderMainBinding navHeaderMainBinding = NavHeaderMainBinding.bind(binding.mainNavView.getHeaderView(0));
        navHeaderMainBinding.setOnClickListener(this);

        initView();

        mainActivityHandler = new MainActivityHandler(this);

        if (showPageStart) {
            showPageStart = false;
            initStartPage();
        }

        initViewPage();
    }

    private void initStartPage() {
        binding.mainPageStart.mainRelative.setVisibility(View.VISIBLE);
        mainActivityHandler.sendEmptyMessageDelayed(MESSAGE_SHOW_START_PAGE, 1000);
    }

    private void initView() {
        setSupportActionBar(binding.mainAppBar.mainAppBarTb);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.mainDrawerLayout, binding.mainAppBar.mainAppBarTb, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        binding.mainDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initViewPage() {

        List<String> titles = new ArrayList<>();
        titles.add(getString(R.string.tab_title_main_1));
        titles.add(getString(R.string.tab_title_main_2));
        titles.add(getString(R.string.tab_title_main_3));
        binding.mainAppBar.mainAppBarTl.addTab(binding.mainAppBar.mainAppBarTl.newTab().setText(titles.get(0)));
        binding.mainAppBar.mainAppBarTl.addTab(binding.mainAppBar.mainAppBarTl.newTab().setText(titles.get(1)));
        binding.mainAppBar.mainAppBarTl.addTab(binding.mainAppBar.mainAppBarTl.newTab().setText(titles.get(2)));

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new CardsFragment());
        fragments.add(new DialogsFragment());
        fragments.add(new WidgetsFragment());

        binding.mainAppBar.mainAppBarVp.setOffscreenPageLimit(2);

        FragmentAdapter mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        binding.mainAppBar.mainAppBarVp.setAdapter(mFragmentAdapter);
        binding.mainAppBar.mainAppBarTl.setupWithViewPager(binding.mainAppBar.mainAppBarVp);
        binding.mainAppBar.mainAppBarTl.setTabsFromPagerAdapter(mFragmentAdapter);

        binding.mainAppBar.mainAppBarVp.addOnPageChangeListener(mOnPageChangeListener);
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position == 2) {
                binding.mainAppBar.mainFab.show();
            } else {
                binding.mainAppBar.mainFab.hide();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onBackPressed() {
        if (binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Intent intent = new Intent();
        switch (item.getItemId()) {
            case R.id.nav_about:
                intent.setClass(this, AboutActivity.class);
                break;
            case R.id.nav_scrolling:
                intent.setClass(this, ScrollingActivity.class);
                break;
            default:
                intent.setClass(this, AboutActivity.class);
                break;
        }
        startActivity(intent);
        binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private static class MainActivityHandler extends Handler {
        private final WeakReference<MainActivity> mainActivityWeakReference;

        MainActivityHandler(MainActivity mainActivity) {
            super();
            mainActivityWeakReference = new WeakReference<>(mainActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mainActivityWeakReference.get() != null) {
                mainActivityWeakReference.get().handleMessage(msg);
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_fab:
                Snackbar.make(v, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                break;
            case R.id.nav_header:
                Snackbar.make(v, "未完成", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START);
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
