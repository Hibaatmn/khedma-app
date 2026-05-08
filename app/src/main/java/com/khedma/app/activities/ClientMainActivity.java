package com.khedma.app.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.khedma.app.R;
import com.khedma.app.databinding.ActivityClientMainBinding;
import com.khedma.app.fragments.client.ClientDemandsFragment;
import com.khedma.app.fragments.client.ClientHomeFragment;
import com.khedma.app.fragments.client.ClientProfileFragment;
import com.khedma.app.utils.SharedPreferencesManager;

public class ClientMainActivity extends AppCompatActivity {
    private ActivityClientMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityClientMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        open(new ClientHomeFragment());
        binding.bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                open(new ClientHomeFragment());
                return true;
            } else if (id == R.id.nav_demands) {
                open(new ClientDemandsFragment());
                return true;
            } else if (id == R.id.nav_profile) {
                open(new ClientProfileFragment());
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
                .replace(R.id.client_fragment_container, fragment)
                .commit();
    }
}
