package com.masterofcode.android.kyivhack.coolstorybro.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.masterofcode.android.kyivhack.coolstorybro.R;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseFragment;

/**
 * Created with IntelliJ IDEA.
 * User: boss1088
 * Date: 7/14/12
 * Time: 3:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class DashboardFragment extends BaseFragment {

    private View returnView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setReturnView(inflater.inflate(R.layout.dashboard_fragment, container, false));

        return getReturnView();
    }

    public View getReturnView() {
        return returnView;
    }

    public void setReturnView(View returnView) {
        this.returnView = returnView;
    }
}
