package com.notes.zk.newnotes.fragment;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.notes.zk.newnotes.R;
import com.notes.zk.newnotes.databinding.FragmentCardsBinding;

public class CardsFragment extends Fragment implements View.OnClickListener, View.OnTouchListener{

    FragmentCardsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cards, container, false);
        binding.setOnClickListener(this);
        //set variables in Binding
        return binding.getRoot();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_main1_action1_btn:
                Snackbar.make(v, getString(R.string.main_flat_button_1_clicked), Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.card_main1_action2_btn:
                Snackbar.make(v, getString(R.string.main_flat_button_1_clicked), Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.card_main_1_1:

                break;
        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
