package com.notes.zk.newnotes.callback;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

public class ItemTouchHelperCallback extends ItemTouchHelper.Callback{
    private final int TYPE_NORMAL = 1;

    private onMoveAndSwipedListener moveAndSwipedListener;

    public ItemTouchHelperCallback(onMoveAndSwipedListener listener) {
        this.moveAndSwipedListener = listener;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager){
            // for recyclerView with gridLayoutManager, support drag all directions, not support swipe
            // 对于使用gridLayoutManager的recyclerView，支持拖动所有方向，不支持滑动
            final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
            final int swipeFlags = 0;
            return makeMovementFlags(dragFlags, swipeFlags);
        } else {
            // for recyclerView with linearLayoutManager, support drag up and down, and swipe lift and right
            // 对于使用linearLayoutManager的recyclerView，支持向上和向下拖动，向右和向右滑动
            if (viewHolder.getItemViewType() == TYPE_NORMAL) {
                final int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                final int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                return makeMovementFlags(dragFlags, swipeFlags);
            } else {
                return 0;
            }
        }
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
        // If the 2 items are not the same type, no dragging
        if (viewHolder.getItemViewType() != viewHolder1.getItemViewType()) {
            return false;
        }
        moveAndSwipedListener.onItemMove(viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        moveAndSwipedListener.onItemDismiss(viewHolder.getAdapterPosition());
    }
}
