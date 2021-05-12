package com.crazypuds.in;

import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.onesignal.OneSignal;

public class Error extends AppCompatActivity {

    private WebView ww;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        ww = findViewById(R.id.ww);
        if (savedInstanceState == null) {
            ww.setWebViewClient(new WebViewClient() {
                                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                                    @Override
                                    public boolean shouldOverrideUrlLoading(WebView view, String url) {

                                        CookieManager.getInstance().setAcceptThirdPartyCookies(view, true);
                                        view.loadUrl(url);
                                        return true;
                                    }

                                    @Override
                                    public void onPageFinished(WebView view, String url) {
                                        super.onPageFinished(view, url);

                                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                            CookieManager.getInstance().flush();
                                        }
                                    }
                                }
            );


            Bundle arguments = getIntent().getExtras();
            OneSignal.startInit(this)
                    .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                    .unsubscribeWhenNotificationsAreDisabled(true)
                    .init();

            ww.loadUrl(arguments.get("crazypuds").toString());
            WebSettings webSettings = ww.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDomStorageEnabled(true);

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (ww.canGoBack()) {
                        ww.goBack();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) getWindow().getDecorView().setSystemUiVisibility(5894);
    }
}