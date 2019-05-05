package com.notes.zk.newnotes.fragment;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.DecelerateInterpolator;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.notes.zk.newnotes.R;
import com.notes.zk.newnotes.databinding.FragmentCardsBinding;

public class CardsFragment extends Fragment implements View.OnClickListener, View.OnTouchListener {

    private FragmentCardsBinding binding;
    private AlphaAnimation alphaAnimation, alphaAnimationShowIcon;

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

        initView();

        return binding.getRoot();
    }

    private void initView() {
        Glide.with(getContext()).load(R.drawable.material_design_2).apply(new RequestOptions().fitCenter()).into(binding.fragmentCardMain1.cardMain1Iv);
        Glide.with(getContext()).load(R.drawable.material_design_4).apply(new RequestOptions().fitCenter()).into(binding.fragmentCardMain2.cardMain2Iv);
        Glide.with(getContext()).load(R.drawable.material_design_11).apply(new RequestOptions().fitCenter()).into(binding.fragmentCardMain3.cardMain3Iv);
        Glide.with(getContext()).load(R.drawable.material_design_1).apply(new RequestOptions().fitCenter()).into(binding.fragmentCardMain41.mainCard41Iv);
        Glide.with(getContext()).load(R.drawable.material_design_1).apply(new RequestOptions().fitCenter()).into(binding.fragmentCardMain42.mainCard42Iv);

        alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
        alphaAnimation.setDuration(700);
        binding.fragmentCardMain1.cardMain1Iv.startAnimation(alphaAnimation);
        binding.fragmentCardMain2.cardMain2Iv.startAnimation(alphaAnimation);

        alphaAnimationShowIcon = new AlphaAnimation(0.2f, 1.0f);
        alphaAnimationShowIcon.setDuration(500);
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
        switch (v.getId()) {
            case R.id.card_main1_action1_btn:
                Snackbar.make(v, getString(R.string.main_flat_button_1_clicked), Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.card_main1_action2_btn:
                Snackbar.make(v, getString(R.string.main_flat_button_2_clicked), Snackbar.LENGTH_SHORT).show();
                break;
            case R.id.main_card2_bookmark_iv:
                if (binding.fragmentCardMain2.mainCard2BookmarkIv.getTag() == null ||
                        binding.fragmentCardMain2.mainCard2BookmarkIv.getTag().equals("TRUE")) {
                    binding.fragmentCardMain2.mainCard2BookmarkIv.setImageResource(R.drawable.ic_bookmark_black_24dp);
                    binding.fragmentCardMain2.mainCard2BookmarkIv.startAnimation(alphaAnimationShowIcon);
                    binding.fragmentCardMain2.mainCard2BookmarkIv.setTag("FALSE");
                } else {
                    binding.fragmentCardMain2.mainCard2BookmarkIv.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                    binding.fragmentCardMain2.mainCard2BookmarkIv.startAnimation(alphaAnimationShowIcon);
                    binding.fragmentCardMain2.mainCard2BookmarkIv.setTag("TRUE");
                }
                break;
            case R.id.main_card2_favorite_iv:
                if (binding.fragmentCardMain2.mainCard2FavoriteIv.getTag() == null ||
                        binding.fragmentCardMain2.mainCard2FavoriteIv.getTag().equals("TRUE")) {
                    binding.fragmentCardMain2.mainCard2FavoriteIv.setImageResource(R.drawable.ic_favorite_black_24dp);
                    binding.fragmentCardMain2.mainCard2FavoriteIv.startAnimation(alphaAnimationShowIcon);
                    binding.fragmentCardMain2.mainCard2FavoriteIv.setTag("FALSE");
                } else {
                    binding.fragmentCardMain2.mainCard2FavoriteIv.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    binding.fragmentCardMain2.mainCard2FavoriteIv.startAnimation(alphaAnimationShowIcon);
                    binding.fragmentCardMain2.mainCard2FavoriteIv.setTag("TRUE");
                }
                break;

            case R.id.main_card41_favorite_iv:
                if (binding.fragmentCardMain41.mainCard41FavoriteIv.getTag() == null ||
                        binding.fragmentCardMain41.mainCard41FavoriteIv.getTag().equals("TRUE")) {
                    binding.fragmentCardMain41.mainCard41FavoriteIv.setImageResource(R.drawable.ic_favorite_black_24dp);
                    binding.fragmentCardMain41.mainCard41FavoriteIv.startAnimation(alphaAnimationShowIcon);
                    binding.fragmentCardMain41.mainCard41FavoriteIv.setTag("FALSE");
                } else {
                    binding.fragmentCardMain41.mainCard41FavoriteIv.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    binding.fragmentCardMain41.mainCard41FavoriteIv.startAnimation(alphaAnimationShowIcon);
                    binding.fragmentCardMain41.mainCard41FavoriteIv.setTag("TRUE");
                }
                break;

            case R.id.main_card42_favorite_iv:
                if (binding.fragmentCardMain42.mainCard42FavoriteIv.getTag() == null ||
                        binding.fragmentCardMain42.mainCard42FavoriteIv.getTag().equals("TRUE")) {
                    binding.fragmentCardMain42.mainCard42FavoriteIv.setImageResource(R.drawable.ic_favorite_black_24dp);
                    binding.fragmentCardMain42.mainCard42FavoriteIv.startAnimation(alphaAnimationShowIcon);
                    binding.fragmentCardMain42.mainCard42FavoriteIv.setTag("FALSE");
                } else {
                    binding.fragmentCardMain42.mainCard42FavoriteIv.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    binding.fragmentCardMain42.mainCard42FavoriteIv.startAnimation(alphaAnimationShowIcon);
                    binding.fragmentCardMain42.mainCard42FavoriteIv.setTag("TRUE");
                }
                break;

            case R.id.main_card41_bookmark_iv:
                if (binding.fragmentCardMain41.mainCard41BookmarkIv.getTag() == null ||
                        binding.fragmentCardMain41.mainCard41BookmarkIv.getTag().equals("TRUE")) {
                    binding.fragmentCardMain41.mainCard41BookmarkIv.setImageResource(R.drawable.ic_bookmark_black_24dp);
                    binding.fragmentCardMain41.mainCard41BookmarkIv.startAnimation(alphaAnimationShowIcon);
                    binding.fragmentCardMain41.mainCard41BookmarkIv.setTag("FALSE");
                } else {
                    binding.fragmentCardMain41.mainCard41BookmarkIv.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                    binding.fragmentCardMain41.mainCard41BookmarkIv.startAnimation(alphaAnimationShowIcon);
                    binding.fragmentCardMain41.mainCard41BookmarkIv.setTag("TRUE");
                }
                break;

            case R.id.main_card42_bookmark_iv:
                if (binding.fragmentCardMain42.mainCard42BookmarkIv.getTag() == null ||
                        binding.fragmentCardMain42.mainCard42BookmarkIv.getTag().equals("TRUE")) {
                    binding.fragmentCardMain42.mainCard42BookmarkIv.setImageResource(R.drawable.ic_bookmark_black_24dp);
                    binding.fragmentCardMain42.mainCard42BookmarkIv.startAnimation(alphaAnimationShowIcon);
                    binding.fragmentCardMain42.mainCard42BookmarkIv.setTag("FALSE");
                } else {
                    binding.fragmentCardMain42.mainCard42BookmarkIv.setImageResource(R.drawable.ic_bookmark_border_black_24dp);
                    binding.fragmentCardMain42.mainCard42BookmarkIv.setTag("TRUE");
                }
                break;
            case R.id.main_card2_share_iv:

                break;
            case R.id.main_card41_share_iv:

                break;
            case R.id.main_card42_share_iv:

                break;
            case R.id.card_main_1_1:

                break;
            case R.id.card_main_1_2:

                break;
            case R.id.card_main_1_3:

                break;
            case R.id.card_main_1_4_1:

                break;
            case R.id.card_main_1_4_2:

                break;

        }

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                ObjectAnimator upAnim = ObjectAnimator.ofFloat(v, "translationZ", 16);
                upAnim.setDuration(150);
                upAnim.setInterpolator(new DecelerateInterpolator());
                upAnim.start();
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                ObjectAnimator downAnim = ObjectAnimator.ofFloat(v, "translationZ", 0);
                downAnim.setDuration(150);
                downAnim.setInterpolator(new AccelerateInterpolator());
                downAnim.start();
                break;
        }
        return false;
    }
}
