package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MapActivity extends AppCompatActivity {

    private static final String URL_NAVER_MAP = "https://m.map.naver.com/search2/search.nhn?query=백반&siteSort=1&sm=clk";
    private static final String URL_DAUM_MAP = "https://m.map.daum.net/";
    private static final int MY_PERMISSION_REQUEST_LOCATION = 0;
    private WebView webView;
    String final_menu;
    class MyChromeClient extends WebChromeClient
    {
        @Override
        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
            // Should implement this function.
            final String myOrigin = origin;
            final GeolocationPermissions.Callback myCallback = callback;
            AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);

            builder.setTitle("GPS");
            builder.setMessage("Allow Current Location?");
            builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    myCallback.invoke(myOrigin, true, false);
                }
            });

            builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    myCallback.invoke(myOrigin, false, false);
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }
    }

    @Override
    protected void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        final_menu = (String) intent.getExtras().get("final_menu");
        webView = (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new MyChromeClient());
        webView.setWebViewClient(new WebViewClientClass());
        if(final_menu != null){
            webView.loadUrl("https://m.map.naver.com/search2/search.nhn?query="+final_menu+"&style=v5&sm=clk");
        }
        else {
            webView.loadUrl("https://m.map.naver.com");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//뒤로가기 버튼 이벤트
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {//웹뷰에서 뒤로가기 버튼을 누르면 뒤로가짐
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {//페이지 이동
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Log.d("check URL",url);
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
