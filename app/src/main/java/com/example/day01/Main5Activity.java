package com.example.day01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Main5Activity extends AppCompatActivity {

    private WebView mWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        initView();
    }

    private void initView() {
        mWeb = (WebView) findViewById(R.id.web);

        mWeb.loadUrl("https://www.baidu.com");
        mWeb.setWebViewClient(new WebViewClient());
    }
}
