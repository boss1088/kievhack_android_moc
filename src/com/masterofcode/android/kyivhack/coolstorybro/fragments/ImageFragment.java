package com.masterofcode.android.kyivhack.coolstorybro.fragments;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.masterofcode.android.kyivhack.coolstorybro.R;
import com.masterofcode.android.kyivhack.coolstorybro.base.BaseFragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: boss1088
 * Date: 7/14/12
 * Time: 4:08 PM
 * To change this template use File | Settings | File Templates.
 */
public class ImageFragment extends BaseFragment {

    private View returnView;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

    public void setInstance(String url) {
        this.setUrl(url);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setReturnView(inflater.inflate(R.layout.fragment_image, container, false));

        return getReturnView();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final ImageView imageView = (ImageView) getReturnView().findViewById(R.id.image_place);
        new Thread(new Runnable() {
            @Override
            public void run() {
                final Drawable image = ImageOperations(getUrl());

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageDrawable(image);
                    }
                });
            }
        }).start();
    }

    private Drawable ImageOperations(String url) {
        try {
            InputStream is = (InputStream) this.fetch(url);
            Drawable d = Drawable.createFromStream(is, "src");
            return d;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object fetch(String address) throws MalformedURLException,IOException {
        URL url = new URL(address);
        Object content = url.getContent();
        return content;
    }

    public View getReturnView() {
        return returnView;
    }

    public void setReturnView(View returnView) {
        this.returnView = returnView;
    }
}
