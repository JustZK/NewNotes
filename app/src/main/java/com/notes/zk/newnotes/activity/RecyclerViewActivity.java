package com.notes.zk.newnotes.activity;

import android.databinding.DataBindingUtil;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;

import com.notes.zk.newnotes.R;
import com.notes.zk.newnotes.adapter.RecyclerViewAdapter;
import com.notes.zk.newnotes.callback.ItemTouchHelperCallback;
import com.notes.zk.newnotes.databinding.ActivityRecyclerViewBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityRecyclerViewBinding binding;

    private boolean loading;

    private List<Integer> data;
    private RecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recycler_view);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_recycler_view);
        binding.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        initView();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.recycler_view_fab:

                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void initView() {
        data = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            data.add(i);
        }

        if (getScreenWidthDp() >= 1200) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
            binding.contentRecyclerView.recyclerViewRv.setLayoutManager(gridLayoutManager);
        } else if (getScreenWidthDp() >= 800) {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            binding.contentRecyclerView.recyclerViewRv.setLayoutManager(gridLayoutManager);
        } else {
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            binding.contentRecyclerView.recyclerViewRv.setLayoutManager(linearLayoutManager);
        }

        adapter = new RecyclerViewAdapter(this);
        binding.contentRecyclerView.recyclerViewRv.setAdapter(adapter);
        adapter.addHeader();
        adapter.setItems(data);
        adapter.addFooter();

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
//                adapter.addItem(linearLayoutManager.findFirstVisibleItemPosition() + 1, insertData);
//            }
//        });

        ItemTouchHelper.Callback callback = new ItemTouchHelperCallback(adapter);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(binding.contentRecyclerView.recyclerViewRv);

        binding.contentRecyclerView.recyclerViewSrl.setColorSchemeResources(R.color.google_blue, R.color.google_green, R.color.google_red, R.color.google_yellow);
        binding.contentRecyclerView.recyclerViewSrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        if (color > 4) {
//                            color = 0;
//                        }
//                        adapter.setColor(++color);
                        binding.contentRecyclerView.recyclerViewSrl.setRefreshing(false);
                    }
                }, 2000);

            }
        });

        binding.contentRecyclerView.recyclerViewRv.addOnScrollListener(scrollListener);
    }

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);

            final LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
            if (!loading && linearLayoutManager.getItemCount() == (linearLayoutManager.findLastVisibleItemPosition() + 1)) {
                loading = true;
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        if (loadTimes <= 5) {
                            adapter.removeFooter();
                            loading = false;
                            adapter.addItems(data);
                            adapter.addFooter();
//                            loadTimes++;
//                        } else {
//                            adapter.removeFooter();
//                            Snackbar.make(mRecyclerView, getString(R.string.no_more_data), Snackbar.LENGTH_SHORT).setCallback(new Snackbar.Callback() {
//                                @Override
//                                public void onDismissed(Snackbar transientBottomBar, int event) {
//                                    super.onDismissed(transientBottomBar, event);
//                                    loading = false;
//                                    adapter.addFooter();
//                                }
//                            }).show();
//                        }
                    }
                }, 1500);


            }
        }
    };


    private int getScreenWidthDp() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return (int) (displayMetrics.widthPixels / displayMetrics.density);
    }
}
