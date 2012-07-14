package com.masterofcode.android.kyivhack.coolstorybro.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.masterofcode.android.kyivhack.coolstorybro.R;

public abstract class BaseActivity extends FragmentActivity {
	
	BaseFragment _fragment = null;
	
	protected abstract BaseFragment addListFragment();
    protected abstract BaseFragment addBaseFragment();
	
	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		
		setContentView(R.layout.base_activity);
		
		if ((_fragment = addListFragment()) != null) {
			putFragment(_fragment);
		}

        if ((_fragment = addBaseFragment()) != null) {
            putFragment(_fragment);
        }
	}

	private void putFragment(BaseFragment fragment) {
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_place, fragment);
		transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
		transaction.commit();
	}
	
}
