package com.masterofcode.android.kyivhack.coolstorybro;

import android.accounts.*;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import com.masterofcode.android.kyivhack.coolstorybro.utils.PicasaConnector;
import org.apache.http.client.HttpResponseException;

/**
 * Created with IntelliJ IDEA.
 * User: vlad
 * Date: 7/14/12
 * Time: 11:26 PM
 * To change this template use File | Settings | File Templates.
 */

public class PicasaConnectorActivity extends Activity
{
    private static final String TAG = PicasaConnectorActivity.class.toString();
    private static final int DIALOG_ACCOUNTS = 0;
    private static final String PREF = "PicasaPref";
    private static final String AUTH_TOKEN_TYPE = "lh2";
    private String authToken;

    private PicasaConnector picasaConnector;

    AccountManager accManager;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        accManager = AccountManager.get(PicasaConnectorActivity.this);

        gotAccount(false);

    }

    private void gotAccount(boolean tokenExpired)
    {
        SharedPreferences settings = getSharedPreferences(PREF, 0);
        String accountName = settings.getString("accountName", null);
        if (accountName != null) {
            Account[] accounts = accManager.getAccountsByType("com.google");
            for (Account account : accounts) {
                if (accountName.equals(account.name)) {
                    if (tokenExpired) {
                        accManager.invalidateAuthToken("com.google", this.authToken);
                    }
                    gotAccount(account);
                    return;
                }
            }
        }
        showDialog(DIALOG_ACCOUNTS);
    }

    private void gotAccount(final Account account) {
        SharedPreferences settings = getSharedPreferences(PREF, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("accountName", account.name);
        editor.commit();

        accManager.getAuthToken(account, AUTH_TOKEN_TYPE, null, PicasaConnectorActivity.this, new AccountManagerCallback<Bundle>() {
            public void run(AccountManagerFuture<Bundle> future) {
                try {
                    String token = future.getResult().getString(AccountManager.KEY_AUTHTOKEN);
                    authenticatedClientLogin(token);

                } catch (OperationCanceledException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    handleException(e);
                }
            }
        }, null);
    }

    private void authenticatedClientLogin(String authToken) {
        this.authToken = authToken;
        picasaConnector = new PicasaConnector(authToken);

        Intent intent = new Intent(PicasaConnectorActivity.this, DashboardActivity.class);
        startActivity(intent);
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_ACCOUNTS:

            final Account[] accounts = accManager.getAccountsByType("com.google");
            final int size = accounts.length;
            String[] names = new String[size];
            for (int i = 0; i < size; i++)
                names[i] = accounts[i].name;

            AlertDialog.Builder builder = new AlertDialog.Builder(PicasaConnectorActivity.this);
            builder.setTitle("Select a Google account which you want to use");
            builder.setItems(names, new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which)
                {
                    gotAccount(accounts[which]);
                }
            });
            return builder.create();
        }
        return null;
    }


    private void handleException(Exception e) {
        e.printStackTrace();
        SharedPreferences settings = getSharedPreferences(PREF, 0);
        boolean log = settings.getBoolean("logging", false);
        if (e instanceof HttpResponseException) {
            int statusCode = ((HttpResponseException) e).getStatusCode();
            if (statusCode == 401 || statusCode == 403) {
                gotAccount(true);
                return;
            }
            if (log) {
                Log.e(TAG, e.getLocalizedMessage());
            }
        }
        if (log) {
            Log.e(TAG, e.getMessage(), e);
        }
    }

}
