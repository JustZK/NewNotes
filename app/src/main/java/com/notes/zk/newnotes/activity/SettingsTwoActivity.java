package com.notes.zk.newnotes.activity;

import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.notes.zk.newnotes.R;
import com.notes.zk.newnotes.databinding.ActivitySettingsTwoBinding;

public class SettingsTwoActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivitySettingsTwoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_settings_two);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings_two);
        binding.setOnClickListener(this);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
            case R.id.setting_two_switch_sw:
                if (binding.settingTwoSwitchSw.getChecked()){
                    binding.settingTwoSwitchSw.setChecked(false);
                } else {
                    binding.settingTwoSwitchSw.setChecked(true);
                }
                break;
            case R.id.setting_two_tv_sb:
                break;
            case R.id.setting_two_list_sb:
                String[] singleChoiceItems = getResources().getStringArray(R.array.dialog_choice_array);
                int itemSelected = 0;
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.main_dialog_single_choice))
                        .setSingleChoiceItems(singleChoiceItems, itemSelected, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        })
                        .setNegativeButton(getString(R.string.cancel), null)
                        .show();
                break;
            case R.id.setting_two_multi_select_list_sb:
                String[] multiChoiceItems = getResources().getStringArray(R.array.dialog_choice_array);
                boolean[] checkedItems = {true, false, false, false, false};
                new AlertDialog.Builder(this)
                        .setTitle(getString(R.string.main_dialog_multi_choice))
                        .setMultiChoiceItems(multiChoiceItems, checkedItems, null)
                        .setPositiveButton(getString(R.string.ok), null)
                        .setNegativeButton(getString(R.string.cancel), null)
                        .show();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
