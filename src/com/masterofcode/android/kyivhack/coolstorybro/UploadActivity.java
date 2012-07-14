package com.masterofcode.android.kyivhack.coolstorybro;

import android.os.Bundle;
import android.view.View;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseActivity;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseFragment;
import com.masterofcode.android.kyivhack.coolstorybro.fragments.UploadFragment;

/**
 * Created with IntelliJ IDEA.
 * User: boss1088
 * Date: 7/14/12
 * Time: 7:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class UploadActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    //TODO Add upload actions
    public void OnUpload(View view) {

    }

    @Override
    protected BaseFragment addListFragment() {
        return null;
    }

    @Override
    protected BaseFragment addBaseFragment() {
        UploadFragment fragment = new UploadFragment();
        return fragment;
    }
}
