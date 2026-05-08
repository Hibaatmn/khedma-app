package com.khedma.app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.khedma.app.repositories.AuthRepository;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends ViewModel {
    private final AuthRepository repository = new AuthRepository();
    private final MutableLiveData<FirebaseUser> userLiveData = new MutableLiveData<>();
    private final MutableLiveData<String> errorLiveData = new MutableLiveData<>();

    public LiveData<FirebaseUser> user() {
        return userLiveData;
    }

    public LiveData<String> error() {
        return errorLiveData;
    }

    public void login(String email, String password) {
        repository.login(email, password, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                userLiveData.setValue(user);
            }

            @Override
            public void onError(Exception exception) {
                errorLiveData.setValue(exception.getMessage());
            }
        });
    }

    public void register(String fullName, String email, String password, String role) {
        repository.register(fullName, email, password, role, new AuthRepository.AuthCallback() {
            @Override
            public void onSuccess(FirebaseUser user) {
                userLiveData.setValue(user);
            }

            @Override
            public void onError(Exception exception) {
                errorLiveData.setValue(exception.getMessage());
            }
        });
    }

    public void logout() {
        repository.logout();
    }
}
