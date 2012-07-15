package com.masterofcode.android.kyivhack.coolstorybro;

import android.os.Bundle;
import android.view.View;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseActivity;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseFragment;
import com.masterofcode.android.kyivhack.coolstorybro.fragments.UploadFragment;
import com.masterofcode.android.kyivhack.coolstorybro.utils.PicasaConnector;
import com.masterofcode.android.kyivhack.coolstorybro.utils.RestClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: boss1088
 * Date: 7/14/12
 * Time: 7:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class UploadActivity extends BaseActivity {

    private String timeStemp;
    private String fileName;

    private static String FULL_URL_STR = "http://172.27.40.20:9292/api/stories";

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        timeStemp = getIntent().getStringExtra("timestemp");
        fileName = getIntent().getStringExtra("filename");
    }

    //TODO Add upload actions
    public void OnUpload(View view) {
        JSONObject jsonObj = new JSONObject();
        try {

            jsonObj.put("album_id", PicasaConnector.getInstance().getCurrentAlbumId());
            jsonObj.put("album_name",PicasaConnector.getInstance().getCurrentAlbumName());

            JSONArray jsonArray = new JSONArray(PicasaConnector.getInstance().getURLForBigPhotosFromAlbum());
            jsonObj.put("photos_data",jsonArray);

            jsonObj.put("switches_data",timeStemp);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        RestClient.doFileUpload(this,FULL_URL_STR, fileName, jsonObj);
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
