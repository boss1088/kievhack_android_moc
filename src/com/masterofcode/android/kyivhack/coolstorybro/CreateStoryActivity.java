package com.masterofcode.android.kyivhack.coolstorybro;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseActivity;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseFragment;
import com.masterofcode.android.kyivhack.coolstorybro.fragments.ImageFragment;
import com.masterofcode.android.kyivhack.coolstorybro.fragments.UploadFragment;

import java.io.File;
import java.io.IOException;
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
public class CreateStoryActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    private static String OUTPUT_FILE= "/sdcard/";
    private MediaRecorder recorder;
    String timeStemp = new String();
    int sizeOfPagerList;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.activity_view_pager);

        updateUi();
    }

    //TODO Add upload actions
    public void OnUpload(View view) {

    }

    //TODO recieve arrayList of images from previous activity
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

        UploadFragment uploadFragment = new UploadFragment();
        fragments.add(uploadFragment);

        sizeOfPagerList = fragments.size();

        PagerAdapter pagerAdapter = new FragmentAdapter(this.getSupportFragmentManager(), fragments);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(pagerAdapter);
        pager.setOnPageChangeListener(this);

        beginRecording();
        timeStemp="[[0,"+System.currentTimeMillis()+"],";
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {
    }

    @Override
    public void onPageSelected(int i) {
        if (i >= sizeOfPagerList-1) {
            stopAllProcces();
            return;
        }
        timeStemp+="["+String.valueOf(i)+","+System.currentTimeMillis()+"],";
    }

    @Override
    public void onPageScrollStateChanged(int i) {
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

    private void beginRecording() {
        killMediaRecorder();

        createDirectory();

        //TODO add unique id
        File outFile = new File(OUTPUT_FILE+".coolstorybro/"+"id"+"/"+"id"+".3gp");
        if(outFile.exists()){
            outFile.delete();
        }

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.DEFAULT);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        //TODO add unique id
        recorder.setOutputFile(OUTPUT_FILE+".coolstorybro/"+"id"+"/"+"id"+".3gp");
        try {
            recorder.prepare();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        recorder.start();
    }

    private void createDirectory(){
        File outDirectory = new File(OUTPUT_FILE+".coolstorybro");
        if((!outDirectory.exists())||(outDirectory.exists()&&!outDirectory.isDirectory())){
            outDirectory.mkdir();
        }

        //TODO add unique id
        outDirectory = new File(OUTPUT_FILE+".coolstorybro"+"/"+"id");
        if((!outDirectory.exists())||(outDirectory.exists()&&!outDirectory.isDirectory())){
            outDirectory.mkdir();
        }
    }

    private void stopRecording() {
        if (recorder != null) {
            recorder.stop();
        }
    }
    private void killMediaRecorder() {
        if (recorder != null) {
            recorder.release();
        }
    }

    private void stopAllProcces() {
        timeStemp+="[0,"+System.currentTimeMillis()+"]]";
        stopRecording();
    }

    @Override
    protected BaseFragment addListFragment() {
        return null;
    }

    @Override
    protected BaseFragment addBaseFragment() {
        return null;
    }
}
