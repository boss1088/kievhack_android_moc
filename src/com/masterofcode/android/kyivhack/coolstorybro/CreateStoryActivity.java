package com.masterofcode.android.kyivhack.coolstorybro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseActivity;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseFragment;
import com.masterofcode.android.kyivhack.coolstorybro.fragments.ImageFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

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

        setContentView(R.layout.activity_view_pager);

        updateUi();
    }

    private void updateUi() {
        List<BaseFragment> fragments = new Vector();
        ArrayList<String> list = new ArrayList<String>();
        list.add("http://i1.kym-cdn.com/entries/icons/original/000/000/346/969638-cool_story__bro_super.jpg");
        list.add("http://greginmotion.com/wp-content/uploads/2012/04/03-cool-story-bro.jpg");
        list.add("http://www.ragetrolling.com/var/albums/Cool%20Story%20Bro.jpg?m=1320018084");
        list.add("http://bfolder.ru/_ph/1/2/784404742.jpg");
        list.add("http://rlv.zcache.com/cool_story_bro_hat-p148712027349463815enxqz_400.jpg");
        list.add("http://static.fjcdn.com/pictures/bro_fc6a7c_783693.jpeg");

        for (String item:list) {
            ImageFragment fragment = new ImageFragment();
            fragment.setInstance(item);
            fragments.add(fragment);
        }

        PagerAdapter pagerAdapter = new FragmentAdapter(this.getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
    }

    public class FragmentAdapter extends FragmentPagerAdapter {

        private final List<BaseFragment> fragments;

        public FragmentAdapter(FragmentManager fm, List<BaseFragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            return this.fragments.size();
        }
    }

    @Override
    protected BaseFragment addListFragment() {
        return null;
    }

    @Override
    protected BaseFragment addBaseFragment() {
        ImageFragment fragment = new ImageFragment();
        fragment.setInstance("http://i1.kym-cdn.com/entries/icons/original/000/000/346/969638-cool_story__bro_super.jpg");
        return null;
    }
}
