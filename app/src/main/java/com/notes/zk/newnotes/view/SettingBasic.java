package com.notes.zk.newnotes.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.notes.zk.newnotes.R;

/**
 * Created by ZK on 2017/11/15.
 */

public class SettingBasic extends FrameLayout {
    private String iconString;
    private int iconColorString;
    private int iconBackground;
    @StringRes
    private int titleRes;
    @StringRes
    private int captionRes;
    ImageView icon = findViewById(R.id.icon);
    TextView title = findViewById(R.id.title);
    TextView caption = findViewById(R.id.caption);

    public SettingBasic(Context context) {
        this(context, null);
    }

    public SettingBasic(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SettingBasic(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        setBackgroundResource(R.drawable.ripple);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        inflater.inflate(R.layout.view_setting_basic, this);

        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SettingBasic);
        iconString = a.getString(R.styleable.SettingBasic_settingIcon);
        iconColorString = a.getInt(R.styleable.SettingBasic_settingIconColor, 0);
        iconBackground = a.getResourceId(R.styleable.SettingBasic_settingIconBackground, 0);
        titleRes = a.getResourceId(R.styleable.SettingBasic_settingTitle, 0);
        captionRes = a.getResourceId(R.styleable.SettingBasic_settingCaption, 0);
        int minimumApi = a.getInteger(R.styleable.SettingBasic_settingMinApi, 0);
        a.recycle();

        if (Build.VERSION.SDK_INT < minimumApi) setVisibility(GONE);
    }

    @Override
    protected void onFinishInflate() {
        icon = findViewById(R.id.icon);
        title = findViewById(R.id.title);
        caption = findViewById(R.id.caption);

        icon.setBackgroundResource(iconBackground);
        title.setText(titleRes);
        caption.setText(captionRes);

        super.onFinishInflate();
    }

    public void setTitleText(String str){
        title.setText(str);
    }

    public void setCaptionText(String str){
        caption.setText(str);
    }
}

