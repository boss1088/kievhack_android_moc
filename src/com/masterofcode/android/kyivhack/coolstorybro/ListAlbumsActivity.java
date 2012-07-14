package com.masterofcode.android.kyivhack.coolstorybro;

import android.os.Bundle;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseActivity;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseFragment;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseListFragment;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: boss1088
 * Date: 7/14/12
 * Time: 3:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class ListAlbumsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    protected BaseFragment addListFragment() {
        BaseListFragment fragment = new BaseListFragment() {};
        ArrayList<String> elements = new ArrayList<String>();
        elements.add("Test Album");
        fragment.setInstance(elements);
        return fragment;
    }

    @Override
    protected BaseFragment addBaseFragment() {
        return null;
    }
}
