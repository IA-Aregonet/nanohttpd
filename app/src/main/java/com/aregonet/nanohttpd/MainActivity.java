package com.aregonet.nanohttpd;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
private WebView myWebView;
private LocalWebServer webServer;

    @SuppressLint("SetJavaScriptEnabled")

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myWebView = new WebView(this);
        setContentView(myWebView);

        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAllowFileAccess(true);
        webSettings.setAllowContentAccess(true);
        webSettings.setAllowUniversalAccessFromFileURLs(true);
        myWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);

        webServer = new LocalWebServer(this, 8080);
        try {
            webServer.start();
        }catch (IOException e){
            e.printStackTrace();
        }
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("http://localhost:8080");

    }
    @Override
    public void onBackPressed(){
        if (myWebView!= null && myWebView.canGoBack()) {
        }else {
            super.onBackPressed();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (webServer != null) {
            webServer.stop();
        }
    }
}