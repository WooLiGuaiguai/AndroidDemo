package com.example.webviewtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WebView webView=(WebView)findViewById(R.id.web_view);
        //getSettings()用来设置浏览器的属性  这里调用setJavaScriptEnabled()支持JavaScript脚本
        webView.getSettings().setJavaScriptEnabled(true);
        //从一个网页传入另一个网页时，在当前webview中显示
        webView.setWebViewClient(new WebViewClient());
        //传入网址
        webView.loadUrl("http://www.baidu.com");
    }
}
