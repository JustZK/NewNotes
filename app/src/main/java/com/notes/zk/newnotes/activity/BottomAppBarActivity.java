package com.notes.zk.newnotes.activity;

import android.databinding.DataBindingUtil;
import android.support.design.bottomappbar.BottomAppBar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.notes.zk.newnotes.R;
import com.notes.zk.newnotes.adapter.RecyclerViewAdapter;
import com.notes.zk.newnotes.databinding.ActivityBottomAppBarBinding;
import com.notes.zk.newnotes.databinding.ActivityBottomNavigationBinding;

import java.util.ArrayList;
import java.util.List;

public class BottomAppBarActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityBottomAppBarBinding binding;

    private RecyclerViewAdapter adapter;
    private List<Integer> data;

    boolean isFabAlignRight = false;
    boolean isCutoutMarginZero = false;
    boolean isCutoutRadiusZero = false;
    boolean showBottomBarTitle = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_bottom_app_bar);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_bottom_app_bar);
        binding.setOnClickListener(this);

        setSupportActionBar(binding.bottomAppBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        data = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            data.add(i);
        }


        if (getScreenWidthDp() >= 1200) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            binding.bottomAppBarRv.setLayoutManager(gridLayoutManager);
        } else if (getScreenWidthDp() >= 800) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            binding.bottomAppBarRv.setLayoutManager(gridLayoutManager);
        } else {
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            binding.bottomAppBarRv.setLayoutManager(linearLayoutManager);
        }

        adapter = new RecyclerViewAdapter(this);
        binding.bottomAppBarRv.setAdapter(adapter);
        adapter.setItems(data);


        binding.bottomAppBarPositionChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isFabAlignRight = b;
                resetBottomAppBar();
            }
        });
        binding.bottomAppBarRadiusChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isCutoutRadiusZero = b;
                resetBottomAppBar();
            }
        });
        binding.bottomAppBarMarginChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isCutoutMarginZero = b;
                resetBottomAppBar();
            }
        });
        binding.bottomAppBarShowTitleChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                showBottomBarTitle = b;
                resetBottomAppBar();
            }
        });
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                adapter.addItem(0, "1");
//                mRecyclerView.smoothScrollToPosition(0);
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottom_app_bar_fab:
                Snackbar.make(v, getString(R.string.feature_is_not_completed), Snackbar.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void resetBottomAppBar() {
        binding.bottomAppBar.setFabAlignmentMode(isFabAlignRight ? BottomAppBar.FAB_ALIGNMENT_MODE_END : BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
        binding.bottomAppBar.setFabCradleMargin(isCutoutMarginZero ? 0 : 17f); //initial default value 17f
        binding.bottomAppBar.setFabCradleRoundedCornerRadius(isCutoutRadiusZero ? 0 : 28f); //initial default value 28f

        binding.bottomAppBarTitleTv.setVisibility(showBottomBarTitle ? View.VISIBLE : View.GONE); //By Default its not suggested to add title but this is just a method if required.
        if (showBottomBarTitle)
            binding.bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);

        binding.bottomAppBar.invalidate();
    }

    private int getScreenWidthDp() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) (displayMetrics.widthPixels / displayMetrics.density);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.popup_menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
