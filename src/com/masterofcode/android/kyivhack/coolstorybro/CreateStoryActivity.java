package com.masterofcode.android.kyivhack.coolstorybro;

import android.os.Bundle;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseActivity;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseFragment;
import com.masterofcode.android.kyivhack.coolstorybro.fragments.ImageFragment;

/**
 * Created with IntelliJ IDEA.
 * User: boss1088
 * Date: 7/14/12
 * Time: 4:20 PM
 * To change this template use File | Settings | File Templates.
 */
public class CreateStoryActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
    }

    @Override
    protected BaseFragment addListFragment() {
        return null;
    }

    @Override
    protected BaseFragment addBaseFragment() {
        ImageFragment fragment = new ImageFragment();
        fragment.setInstance("http://i1.kym-cdn.com/entries/icons/original/000/000/346/969638-cool_story__bro_super.jpg");
        return fragment;
    }
}
