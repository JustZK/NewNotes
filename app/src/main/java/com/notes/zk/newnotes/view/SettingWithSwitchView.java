package com.notes.zk.newnotes.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.notes.zk.newnotes.R;

/**
 * Created by ZK on 2017/8/29.
 */

public class SettingWithSwitchView extends FrameLayout{
    private int iconBackground;
    @StringRes
    private final int titleRes;
    @StringRes
    private final int captionRes;
    private final boolean defaultValue;
    ImageView icon;
    TextView title;
    TextView caption;
    SwitchCompat toggle;

    public SettingWithSwitchView(Context context) {
        this(context, null);
    }

    public SettingWithSwitchView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingWithSwitchView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setBackgroundResource(R.drawable.ripple);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.view_setting_switch, this);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SettingWithSwitchView);
        iconBackground = a.getResourceId(R.styleable.SettingWithSwitchView_settingIconBackground, 0);
        titleRes = a.getResourceId(R.styleable.SettingWithSwitchView_settingTitle, 0);
        captionRes = a.getResourceId(R.styleable.SettingWithSwitchView_settingCaption, 0);
        defaultValue = a.getBoolean(R.styleable.SettingWithSwitchView_settingDefaultValue, false);
        a.recycle();
    }

    @Override
    protected void onFinishInflate() {
//        ButterKnife.bind(this);
        icon = findViewById(R.id.icon);
        title = findViewById(R.id.title);
        caption = findViewById(R.id.caption);
        toggle = findViewById(R.id.toggle);

        icon.setBackgroundResource(iconBackground);
        title.setText(titleRes);
        caption.setText(captionRes);

        super.onFinishInflate();
    }


    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public void setChecked(boolean checked){
        toggle.setChecked(checked);
    }

    public boolean getChecked(){
        return toggle.isChecked();
    }

    public void setTitleText(String str){
        title.setText(str);
    }

    public void setCaptionText(String str){
        caption.setText(str);
    }

}
