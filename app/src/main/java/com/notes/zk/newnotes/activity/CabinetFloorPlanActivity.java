package com.notes.zk.newnotes.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.notes.zk.newnotes.R;
import com.notes.zk.newnotes.adapter.FragmentAdapter;
import com.notes.zk.newnotes.databinding.ActivityCabinetFloorPlanBinding;
import com.notes.zk.newnotes.fragment.CabinetFloorPlanFragment;

import java.util.ArrayList;
import java.util.List;

public class CabinetFloorPlanActivity extends AppCompatActivity implements CabinetFloorPlanFragment.OnFragmentInteractionListener{
    private ActivityCabinetFloorPlanBinding binding;
    private List<String> menus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cabinet_floor_plan);
        menus.add("A柜");
        menus.add("B柜");
        menus.add("C柜");
        menus.add("D柜");
        menus.add("E柜");
        menus.add("F柜");
        menus.add("G柜");
        menus.add("H柜");
        menus.add("I柜");


        initView();
        initViewPage();
    }

    private void initView() {
        setSupportActionBar(binding.cabinetFloorPlanTb);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


    }

    private void initViewPage() {
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0 ; i < menus.size(); i++) {
            binding.cabinetFloorPlanTl.addTab(binding.cabinetFloorPlanTl.newTab().setText(menus.get(i)));
            fragments.add(CabinetFloorPlanFragment.newInstance(menus.get(i)));
        }

        binding.cabinetFloorPlanVp.setOffscreenPageLimit(2);

        FragmentAdapter mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments, menus);
        binding.cabinetFloorPlanVp.setAdapter(mFragmentAdapter);
        binding.cabinetFloorPlanTl.setupWithViewPager(binding.cabinetFloorPlanVp);
        binding.cabinetFloorPlanTl.setTabsFromPagerAdapter(mFragmentAdapter);

        binding.cabinetFloorPlanVp.addOnPageChangeListener(mOnPageChangeListener);

        if (menus.size() <= 3) binding.cabinetFloorPlanTl.setTabMode(TabLayout.MODE_FIXED);
        else binding.cabinetFloorPlanTl.setTabMode(TabLayout.MODE_SCROLLABLE);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int select = item.getItemId();
        if (select == android.R.id.home) finish();
        else {
            for (int i = 0; i < menus.size(); i++){
                if (select == Menu.FIRST + i){
                    binding.cabinetFloorPlanVp.setCurrentItem(i);
                    break;
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        super.onCreateOptionsMenu(menu);
        for (int i = 0; i < menus.size(); i++){
            menu.add(Menu.NONE, Menu.FIRST + i , 0, menus.get(i));
        }

        return true;
    }

    private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
