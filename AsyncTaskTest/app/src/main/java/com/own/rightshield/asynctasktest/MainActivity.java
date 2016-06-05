package com.own.rightshield.asynctasktest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.onlineImageView) ImageView onlineImageView;
    private Bitmap image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        new MyAsyncTask().execute(0);
    }
    private class MyAsyncTask extends AsyncTask<Integer,Void,String> {

        @Override
        protected String doInBackground(Integer[] id) {
            String myFeed = "http://orig09.deviantart.net/9d86/f/2011/212/c/5/disturbed_render_by_stealth14-d429v44.png";

            try {
                URL url = new URL(myFeed);

                // Create a new HTTP URL connection
                URLConnection connection = url.openConnection();
                HttpURLConnection httpConnection = (HttpURLConnection) connection;

                int responseCode = httpConnection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    InputStream in = httpConnection.getInputStream();
                    image = BitmapFactory.decodeStream(in);
                    return "OK";
                }
            }
            catch (MalformedURLException e) {
                Log.d("MALFORMED_EXCEPTION", "Malformed URL Exception.");
            }
            catch (IOException e) {
                Log.d("IO_EXCEPTION", "IO Exception.");
            }

            return "Error";
        }

        @Override
        protected void onPostExecute(String result) {
            onlineImageView.setImageBitmap(image);
        }
    }

}
