package com.masterofcode.android.kyivhack.coolstorybro.task;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import com.masterofcode.android.kyivhack.coolstorybro.ListAlbumsActivity;
import com.masterofcode.android.kyivhack.coolstorybro.utils.PicasaConnector;

/**
 * Created with IntelliJ IDEA.
 * User: vlad
 * Date: 7/14/12
 * Time: 11:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class LoadAlbumsAsync extends AsyncTask<Void,Void, Void>{

    Context context;

    public LoadAlbumsAsync(Context context)
    {
        this.context = context;
    }
    @Override
    protected Void doInBackground(Void... voids) {
        PicasaConnector.getInstance().getAlbums();
        return null;
    }

    protected void onPostExecute(Void result)
    {
        super.onPostExecute(result);
        context.startActivity(new Intent(context, ListAlbumsActivity.class));
    }
}
