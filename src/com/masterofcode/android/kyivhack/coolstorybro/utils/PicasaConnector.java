package com.masterofcode.android.kyivhack.coolstorybro.utils;

import android.util.Log;
import com.google.gdata.client.photos.PicasawebService;
import com.google.gdata.data.*;
import com.google.gdata.data.media.MediaFileSource;
import com.google.gdata.data.media.MediaSource;
import com.google.gdata.data.media.mediarss.*;
import com.google.gdata.data.media.mediarss.MediaContent;
import com.google.gdata.data.photos.*;
import com.google.gdata.util.ServiceException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: vlad
 * Date: 7/14/12
 * Time: 11:26 PM
 * To change this template use File | Settings | File Templates.
 */
public class PicasaConnector {

    private static PicasaConnector instance = null;
    private static String PART_PATH = "https://picasaweb.google.com/data/feed/api/user/";

    PicasawebService picasaServ;
    List<GphotoEntry> albumsList;
    ArrayList<String> urlList;

    private String selectedAlbum;
    private String selectedAlbumId;

    public PicasaConnector(String authToken)
    {
        picasaServ = new PicasawebService("picasa connector service");
        picasaServ.setUserToken(authToken);
        picasaServ.setConnectTimeout(10000);

        instance = this;
    }

    public void getAlbums() {

        if(albumsList != null)
            albumsList.clear();

        URL feedUrl = null;
        try {
            feedUrl = new URL(PART_PATH + "default?kind=album");
            UserFeed myUserFeed = picasaServ.getFeed(feedUrl, UserFeed.class);

            albumsList = myUserFeed.getEntries();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getAlbumNames()
    {
        ArrayList<String> albumNames = new ArrayList<String>();

        for (GphotoEntry entry : albumsList) {
            albumNames.add(entry.getTitle().getPlainText());
        }

        return albumNames;
    }

    public void getPhotosInAlbum(int index)
    {
        if(urlList != null)
            urlList.clear();

        URL feedUrl = null;
        try {
            GphotoEntry album = albumsList.get(index);

            selectedAlbum = album.getTitle().getPlainText();
            selectedAlbumId = album.getGphotoId();

            feedUrl = new URL(PART_PATH + "default/albumid/" + album.getGphotoId());
            AlbumFeed myUserFeed = picasaServ.getFeed(feedUrl, AlbumFeed.class);

            urlList = new ArrayList<String>();
            for (GphotoEntry entry : myUserFeed.getEntries()) {
                MediaGroup extension = entry.getExtension(MediaGroup.class);
                List<MediaContent> repeatingExtension = extension
                        .getRepeatingExtension(MediaContent.class);
                urlList.add(repeatingExtension.get(0).getUrl());
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ServiceException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getURLPhotosFromAlbum()
    {
        return urlList;
    }

    public String getCurrentAlbumName()
    {
        return selectedAlbum;
    }

    public String getCurrentAlbumId()
    {
        return selectedAlbumId;
    }

    public static PicasaConnector getInstance()
    {
        return instance;
    }

}
