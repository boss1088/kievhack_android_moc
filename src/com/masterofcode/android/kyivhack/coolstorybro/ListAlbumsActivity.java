package com.masterofcode.android.kyivhack.coolstorybro;

import android.os.Bundle;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseActivity;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseFragment;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseListFragment;
import com.masterofcode.android.kyivhack.coolstorybro.utils.PicasaConnector;

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
        final BaseListFragment fragment = new BaseListFragment() {};

        fragment.setElements(PicasaConnector.getInstance().getAlbumNames());

        return fragment;
    }

    @Override
    protected BaseFragment addBaseFragment() {
        return null;
    }

}
