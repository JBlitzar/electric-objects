package com.dan.eo1;
import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.http.SslError;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.os.AsyncTask;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webView);
        if (webView == null) {
            Log.e("WebView", "WebView is null! Check activity_main.xml");
            return;
        }
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        Toast.makeText(this, "Loading URL", Toast.LENGTH_SHORT).show();
        WebView.setWebContentsDebuggingEnabled(true);
//        webView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                handler.proceed(); // Ignore SSL errors
//            }
//        });
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 1);
        }






        webView.loadUrl("https://jblitzar.github.io/electric-objects");


    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.d("KeyPress", "Key Code: " + event.getKeyCode());
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_C:
                    openSettings();
                    return true;

                case KeyEvent.KEYCODE_H:
                    goToHomeScreen();
                    return true;

                default:
                    break;
            }
        }
        return super.dispatchKeyEvent(event); // Pass remaining events to WebView
    }




    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(intent);
    }

    private void goToHomeScreen() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
