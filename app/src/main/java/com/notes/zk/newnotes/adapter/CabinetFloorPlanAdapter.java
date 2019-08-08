package com.notes.zk.newnotes.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.notes.zk.newnotes.R;
import com.notes.zk.newnotes.bean.Cabinet;

import java.util.List;

/**
 * Created by ZK on 2017/12/11.
 */

public class CabinetFloorPlanAdapter extends RecyclerView.Adapter<CabinetFloorPlanAdapter.ViewHolder> {
    public static final String TAG = "AccessListAdapter";

    private List<Cabinet> list;

    public CabinetFloorPlanAdapter(List<Cabinet> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        TextView adapter_cabinet_floor_plan_name_tv;
        TextView adapter_cabinet_floor_plan_type_tv;
        TextView adapter_cabinet_floor_plan_user_tv;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_access_item, viewGroup,false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.adapter_cabinet_floor_plan_name_tv = view.findViewById(R.id.adapter_cabinet_floor_plan_name_tv);
        viewHolder.adapter_cabinet_floor_plan_type_tv = view.findViewById(R.id.adapter_cabinet_floor_plan_type_tv);
        viewHolder.adapter_cabinet_floor_plan_user_tv = view.findViewById(R.id.adapter_cabinet_floor_plan_user_tv);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
//        viewHolder.adapter_access_item_ll.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
        Cabinet cabinet = list.get(position);

        if (cabinet.getState() == 1){
            viewHolder.adapter_cabinet_floor_plan_name_tv.setVisibility(View.VISIBLE);
            viewHolder.adapter_cabinet_floor_plan_type_tv.setVisibility(View.VISIBLE);
            viewHolder.adapter_cabinet_floor_plan_name_tv.setText(cabinet.getName() + cabinet.getCode());
        }else {
            viewHolder.adapter_cabinet_floor_plan_name_tv.setVisibility(View.GONE);
            viewHolder.adapter_cabinet_floor_plan_type_tv.setVisibility(View.GONE);

            viewHolder.adapter_cabinet_floor_plan_user_tv.setText("主\n\n屏\n\n幕");
        }




        //如果设置了回调，则设置点击事件
        if (mOnItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int positionTemp = viewHolder.getAdapterPosition();
                    if (positionTemp != -1) {
                        // 当ViewHolder处于FLAG_REMOVED 等状态时会返回NO_POSITION-1
                        mOnItemClickListener.onItemClick(positionTemp);
                    }
                }
            });

        }

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }


    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


}
