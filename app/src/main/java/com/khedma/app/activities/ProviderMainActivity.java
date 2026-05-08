package com.khedma.app.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.khedma.app.R;
import com.khedma.app.databinding.ActivityProviderMainBinding;
import com.khedma.app.fragments.provider.ProviderDemandsFragment;
import com.khedma.app.fragments.provider.ProviderHomeFragment;
import com.khedma.app.fragments.provider.ProviderProfileFragment;
import com.khedma.app.utils.SharedPreferencesManager;

public class ProviderMainActivity extends AppCompatActivity {
    private ActivityProviderMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProviderMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        open(new ProviderHomeFragment());
        binding.bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                open(new ProviderHomeFragment());
                return true;
            } else if (id == R.id.nav_demands) {
                open(new ProviderDemandsFragment());
                return true;
            } else if (id == R.id.nav_profile) {
                open(new ProviderProfileFragment());
                return true;
            }
            return false;
        });

        binding.fabLogout.setOnClickListener(v -> {
            new SharedPreferencesManager(this).clearSession();
            startActivity(new Intent(this, AuthActivity.class));
            finish();
        });
    }

    private void open(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.provider_fragment_container, fragment)
                .commit();
    }
}
