package com.tujuh.tujuh_capstone_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;


import android.Manifest;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class MapActivity extends AppCompatActivity {

    private static final String URL_NAVER_MAP = "https://m.map.naver.com/";
    private static final String URL_DAUM_MAP = "https://m.map.daum.net/";
    private static final int MY_PERMISSION_REQUEST_LOCATION = 0;
    private WebView webView;

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_map);
        int permissionCheck = ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION);


        if (permissionCheck!= PackageManager.PERMISSION_GRANTED) {

            //Toast.makeText(this,"권한 승인이 필요합니다",Toast.LENGTH_LONG).show();

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                //Toast.makeText(this,"위치 정보 권한이 필요합니다.",Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSION_REQUEST_LOCATION);
                //Toast.makeText(this,"위치 정보 권한이 필요합니다.",Toast.LENGTH_LONG).show();

            }
        }


        webView = (WebView) findViewById(R.id.webview);

    }

    private void initWebView(){
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
                super.onGeolocationPermissionsShowPrompt(origin, callback);
                callback.invoke(origin, true, false);
            }
        });
        webView.loadUrl(URL_NAVER_MAP);
    }

    @Override

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == MY_PERMISSION_REQUEST_LOCATION){
            initWebView();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
