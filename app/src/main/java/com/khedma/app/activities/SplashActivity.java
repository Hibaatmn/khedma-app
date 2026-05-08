package com.khedma.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

import com.khedma.app.R;
import com.khedma.app.utils.SharedPreferencesManager;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferencesManager session = new SharedPreferencesManager(this);
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent;
            if (!session.isLoggedIn()) {
                intent = new Intent(this, AuthActivity.class);
            } else if ("provider".equals(session.getUserRole())) {
                intent = new Intent(this, ProviderMainActivity.class);
            } else {
                intent = new Intent(this, ClientMainActivity.class);
            }
            startActivity(intent);
            finish();
        }, 1200);
    }
}
