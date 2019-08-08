package com.notes.zk.newnotes.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.notes.zk.newnotes.R;
import com.notes.zk.newnotes.activity.CabinetFloorPlanActivity;
import com.notes.zk.newnotes.adapter.CabinetFloorPlanAdapter;
import com.notes.zk.newnotes.bean.Cabinet;
import com.notes.zk.newnotes.databinding.FragmentCabinetFloorPlanBinding;

import java.util.ArrayList;
import java.util.List;

public class CabinetFloorPlanFragment extends Fragment {
    private static final String ARG_PARAM1 = "BOX_NAME";

    private FragmentCabinetFloorPlanBinding binding;

    private List<Cabinet> list;
    private CabinetFloorPlanAdapter mAdapter;

    private String mBoxName;

    private OnFragmentInteractionListener mListener;

    public CabinetFloorPlanFragment() {
        // Required empty public constructor
    }

    public static CabinetFloorPlanFragment newInstance(String boxName) {
        CabinetFloorPlanFragment fragment = new CabinetFloorPlanFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, boxName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBoxName = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cabinet_floor_plan, container, false);

        initView();

        return binding.getRoot();
    }


    private void initView() {
        list = new ArrayList<>();
        if (mBoxName.equals("A柜")) {
            for (int i = 1; i <= 11; i++) {
                Cabinet cabinet = new Cabinet();
                cabinet.setName(mBoxName);
                if (i == 2) {
                    cabinet.setState(2);
                    cabinet.setCode(i);
                    cabinet.setProportion(2);
                } else {
                    cabinet.setState(1);
                    if (i > 2) cabinet.setCode(i - 1);
                    else cabinet.setCode(i);
                    cabinet.setProportion(1);
                }
                list.add(cabinet);
            }
        } else {
            for (int i = 1; i <= 12; i++) {
                Cabinet cabinet = new Cabinet();
                cabinet.setState(1);
                cabinet.setName(mBoxName);
                cabinet.setCode(i);
                cabinet.setProportion(1);
                list.add(cabinet);
            }
        }

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 6, LinearLayoutManager.HORIZONTAL, false);
        manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                Cabinet bean = list.get(position);
                return bean.getProportion();
            }
        });
//        binding.fragmentCabinetFloorRv.addItemDecoration(new SpaceItemDecoration());
        binding.fragmentCabinetFloorRv.setLayoutManager(manager);
        binding.fragmentCabinetFloorRv.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new CabinetFloorPlanAdapter(list);
        binding.fragmentCabinetFloorRv.setAdapter(mAdapter);
//        mAdapter.setOnItemClickListener(mOnItemClickListener);

    }


    public class SpaceItemDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

            int position = parent.getChildAdapterPosition(view);

            outRect.set(5, 0, 5, 0);
        }

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
