package com.masterofcode.android.kyivhack.coolstorybro;

import android.os.Bundle;
import android.view.View;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseActivity;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseFragment;
import com.masterofcode.android.kyivhack.coolstorybro.fragments.DashboardFragment;
import com.masterofcode.android.kyivhack.coolstorybro.task.LoadAlbumsAsync;

public class DashboardActivity extends BaseActivity {

    @Override
    protected BaseFragment addListFragment() {
        return null;
    }

    @Override
    protected BaseFragment addBaseFragment() {
        DashboardFragment fragment = new DashboardFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void OnCreateStoryClick(View view) {
        new LoadAlbumsAsync(this).execute();

    }

    public void OnShowStoryClick(View view) {

    }
}
