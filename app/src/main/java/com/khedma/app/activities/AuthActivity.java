package com.khedma.app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.khedma.app.R;
import com.khedma.app.databinding.ActivityAuthBinding;
import com.khedma.app.utils.Constants;
import com.khedma.app.utils.FirebaseConfig;
import com.khedma.app.utils.SharedPreferencesManager;
import com.khedma.app.viewmodels.AuthViewModel;

public class AuthActivity extends AppCompatActivity {
    private ActivityAuthBinding binding;
    private AuthViewModel viewModel;
    private boolean registerFlow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAuthBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.roles,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinnerRole.setAdapter(adapter);

        binding.btnLogin.setOnClickListener(v -> {
            registerFlow = false;
            viewModel.login(
                    binding.etEmail.getText().toString().trim(),
                    binding.etPassword.getText().toString().trim()
            );
        });

        binding.btnRegister.setOnClickListener(v -> {
            registerFlow = true;
            viewModel.register(
                    binding.etName.getText().toString().trim(),
                    binding.etEmail.getText().toString().trim(),
                    binding.etPassword.getText().toString().trim(),
                    binding.spinnerRole.getSelectedItem().toString()
            );
        });

        viewModel.user().observe(this, user -> {
            if (user == null) return;
            if (registerFlow) {
                openDashboard(user.getUid(), binding.spinnerRole.getSelectedItem().toString());
                return;
            }
            FirebaseConfig.firestore()
                    .collection(Constants.USERS_COLLECTION)
                    .document(user.getUid())
                    .get()
                    .addOnSuccessListener(snapshot -> {
                        String role = snapshot.getString("role");
                        openDashboard(user.getUid(), role == null ? "client" : role);
                    })
                    .addOnFailureListener(exception -> openDashboard(user.getUid(), "client"));
        });

        viewModel.error().observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDashboard(String userId, String role) {
        new SharedPreferencesManager(this).saveUserSession(userId, role);
        Class<?> destination = "provider".equals(role) ? ProviderMainActivity.class : ClientMainActivity.class;
        startActivity(new Intent(this, destination));
        finish();
    }
}
