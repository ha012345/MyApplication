package com.tujuh.tujuh_capstone_project;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


public class MapActivity extends AppCompatActivity {

    private static final String URL_NAVER_MAP = "https://m.map.naver.com/search2/search.nhn?query=백반&siteSort=1&sm=clk";
    private static final String URL_DAUM_MAP = "https://m.map.daum.net/";
    private static final int MY_PERMISSION_REQUEST_LOCATION = 0;
    private WebView webView;
    String final_menu;
    private final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION=1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        int permssionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permssionCheck != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "권한 승인이 필요합니다", Toast.LENGTH_LONG).show();
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "지도 사용을 위해 위치정보 권한이 필요합니다.", Toast.LENGTH_LONG).show();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                Toast.makeText(this, "지도 사용을 위해 위치정보 권한이 필요합니다.", Toast.LENGTH_LONG).show();

            }
        }

        final LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MapActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
        } else {
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            String provider = location.getProvider();
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
            double altitude = location.getAltitude();

            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    1000,
                    1,
                    gpsLocationListener);
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    1000,
                    1,
                    gpsLocationListener);


            Intent intent = getIntent();
            final_menu = (String) intent.getExtras().get("final_menu");
            webView = (WebView) findViewById(R.id.webview);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClientClass());
            if (final_menu != null) {
                String Url = "daummaps://search?q=" + final_menu + "&p=" + latitude + "," + longitude;
                Intent intent_map = new Intent(Intent.ACTION_VIEW, Uri.parse(Url));
                startActivity(intent_map);
            } else {
                String Url = "daummaps://look?p=" + latitude + "," + longitude;
                Intent intent_map = new Intent(Intent.ACTION_VIEW, Uri.parse(Url));
                startActivity(intent_map);
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this,"승인이 허가되어 있습니다.",Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(this,"아직 승인받지 않았습니다.",Toast.LENGTH_LONG).show();
                }
                return;
            }

        }
    }

    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };




//    private static final String URL_NAVER_MAP = "https://m.map.naver.com/search2/search.nhn?query=백반&siteSort=1&sm=clk";
//    private static final String URL_DAUM_MAP = "https://m.map.daum.net/";
//    private static final int MY_PERMISSION_REQUEST_LOCATION = 0;
//    private WebView webView;
//    String final_menu;
//    class MyChromeClient extends WebChromeClient
//    {
//        @Override
//        public void onGeolocationPermissionsShowPrompt(String origin, GeolocationPermissions.Callback callback) {
//            // Should implement this function.
//            final String myOrigin = origin;
//            final GeolocationPermissions.Callback myCallback = callback;
//            AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
//
//            builder.setTitle("GPS");
//            builder.setMessage("Allow Current Location?");
//            builder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//                    myCallback.invoke(myOrigin, true, false);
//                }
//            });
//
//            builder.setNegativeButton("Decline", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//                    myCallback.invoke(myOrigin, false, false);
//                }
//            });
//
//            AlertDialog alert = builder.create();
//            alert.show();
//        }
//    }
//
//    @Override
//    protected void onCreate(Bundle saveInstanceState) {
//        super.onCreate(saveInstanceState);
//        setContentView(R.layout.activity_map);
//        Intent intent = getIntent();
//        final_menu = (String) intent.getExtras().get("final_menu");
//        webView = (WebView) findViewById(R.id.webview);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebChromeClient(new MyChromeClient());
//        webView.setWebViewClient(new WebViewClientClass());
//        if(final_menu != null){
//            webView.loadUrl("https://m.map.naver.com/search2/search.nhn?query="+final_menu+"&style=v5&sm=clk");
//        }
//        else {
//            webView.loadUrl("https://m.map.naver.com");
//        }
//    }
//
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {//�로가�버튼 �벤
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {//�뷰�서 �로가�버튼�르멤로가�
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private class WebViewClientClass extends WebViewClient {//�이지 �동
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
