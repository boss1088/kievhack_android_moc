package com.masterofcode.android.kyivhack.coolstorybro.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.json.JSONObject;

import java.io.File;

public class RestClient {

    private static ProgressDialog pd;

    public static void doFileUpload(final Context context, final String url, final String filePath1, final JSONObject jsonObj) {
        final File file1 = new File(filePath1);
        if (!TextUtils.isEmpty(url)) {

            pd = ProgressDialog.show(context, "Working...", "Sending data to server",true,false);

            Thread thread = new Thread() {
                public void run() {
                    try {

                         HttpClient client = new DefaultHttpClient();
                        client.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
                        HttpPost post = new HttpPost(url);

                        post.getParams().setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);

                        FileBody bin1 = new FileBody(file1);
                        MultipartEntity reqEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);

                        reqEntity.addPart("data", new StringBody(jsonObj.toString()));
                        reqEntity.addPart("file", bin1);

                        post.setEntity(reqEntity);
                        HttpResponse response = client.execute(post);
                        HttpEntity resEntity = response.getEntity();

                        if (resEntity != null) {
                            resEntity.consumeContent();
                        }

                        handler.sendEmptyMessage(0);

                    } catch (Exception ex) {
                        if(pd != null && pd.isShowing())
                            pd.dismiss();
                        ex.printStackTrace();
                    }
                }
            };
            thread.start();
        }
    }

    final static private Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
                    if(pd != null && pd.isShowing())
                        pd.dismiss();
        }
    };
}