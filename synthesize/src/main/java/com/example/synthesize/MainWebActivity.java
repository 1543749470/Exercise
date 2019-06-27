package com.example.synthesize;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainWebActivity extends AppCompatActivity {

    private WebView mMyWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_web);
        initView();
    }

    private void initView() {
        mMyWeb = (WebView) findViewById(R.id.myWeb);
        WebSettings settings = mMyWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setBuiltInZoomControls(true);
        mMyWeb.setWebViewClient(new WebViewClient());
        mMyWeb.loadUrl("https://www.baidu.com");
    }
}
