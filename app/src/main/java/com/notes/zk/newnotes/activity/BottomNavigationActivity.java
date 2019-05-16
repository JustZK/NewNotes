package com.notes.zk.newnotes.activity;

import android.animation.ArgbEvaluator;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.notes.zk.newnotes.R;
import com.notes.zk.newnotes.databinding.ActivityBottomNavigationBinding;

import java.util.ArrayList;
import java.util.List;

public class BottomNavigationActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityBottomNavigationBinding binding;

    private List<View> viewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bottom_navigation);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_navigation);
        binding.setOnClickListener(this);

        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        // getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.argb(33, 0, 0, 0));

        initView();
    }

    private void initView() {
        View view1 = getLayoutInflater().inflate(R.layout.item_view_pager_1, null);
        View view2 = getLayoutInflater().inflate(R.layout.item_view_pager_2, null);
        View view3 = getLayoutInflater().inflate(R.layout.item_view_pager_3, null);
        View view4 = getLayoutInflater().inflate(R.layout.item_view_pager_4, null);

        viewList = new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);

        binding.bottomNavigationVp.setAdapter(pagerAdapter);
        binding.bottomNavigationVp.addOnPageChangeListener(pageChangeListener);

        binding.bottomNavigationBnv.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            ArgbEvaluator evaluator = new ArgbEvaluator();
            int evaluate = getResources().getColor(R.color.app_blue);
            if (position == 0) {
                evaluate = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.app_blue), getResources().getColor(R.color.app_green));
            } else if (position == 1) {
                evaluate = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.app_green), getResources().getColor(R.color.app_yellow));
            } else if (position == 2) {
                evaluate = (Integer) evaluator.evaluate(positionOffset, getResources().getColor(R.color.app_yellow), getResources().getColor(R.color.app_red));
            } else {
                evaluate = getResources().getColor(R.color.app_red);
            }
            ((View) binding.bottomNavigationVp.getParent()).setBackgroundColor(evaluate);
        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    binding.bottomNavigationBnv.setSelectedItemId(R.id.bottom_navigation_blue);
                    break;
                case 1:
                    binding.bottomNavigationBnv.setSelectedItemId(R.id.bottom_navigation_green);
                    break;
                case 2:
                    binding.bottomNavigationBnv.setSelectedItemId(R.id.bottom_navigation_yellow);
                    break;
                case 3:
                    binding.bottomNavigationBnv.setSelectedItemId(R.id.bottom_navigation_red);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.bottom_navigation_blue:
                    binding.bottomNavigationVp.setCurrentItem(0);
                    return true;
                case R.id.bottom_navigation_green:
                    binding.bottomNavigationVp.setCurrentItem(1);
                    return true;
                case R.id.bottom_navigation_yellow:
                    binding.bottomNavigationVp.setCurrentItem(2);
                    return true;
                case R.id.bottom_navigation_red:
                    binding.bottomNavigationVp.setCurrentItem(3);
                    return true;
            }
            return false;
        }
    };

    private PagerAdapter pagerAdapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return viewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(viewList.get(position));
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(viewList.get(position));
            return viewList.get(position);
        }
    };

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
