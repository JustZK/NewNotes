package com.notes.zk.newnotes.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.notes.zk.newnotes.R;

public class DialogsFragment extends Fragment {
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void initView(){
        view.findViewById(R.id.fragment_dialogs_12_bt).setOnClickListener(mOnClickListener);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_dialogs, container, false);

        initView();

        return view;
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.fragment_dialogs_12_bt:
                    View view = getLayoutInflater().inflate(R.layout.dialog_rounded_shape, null);
                    new AlertDialog.Builder(getContext(), R.style.RoundedShapeDialog)
                            .setView(view)
                            .setTitle(getString(R.string.dialog_rounded_shape))
                            .setPositiveButton(getString(R.string.sure), null)
                            .setNegativeButton(getString(R.string.cancel), null)
                            .show();
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
