package com.dan.eo1;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        Toast.makeText(this, "Loading URL", Toast.LENGTH_SHORT).show();
        WebView.setWebContentsDebuggingEnabled(true);


        webView.loadUrl("https://jblitzar.github.io/electric-objects");


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            Log.d("KeyPress", "Key Code: " + keyCode);
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_C:
                    // Open settings when "C" is pressed
                    openSettings();
                    return true;

                case KeyEvent.KEYCODE_H:
                    // Go to the home screen when "H" is pressed
                    goToHomeScreen();
                    return true;

                default:
                    return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
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
