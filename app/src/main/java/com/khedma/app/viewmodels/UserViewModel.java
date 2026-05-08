package com.khedma.app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.khedma.app.models.User;
import com.khedma.app.repositories.UserRepository;

public class UserViewModel extends ViewModel {
    private final UserRepository repository = new UserRepository();
    private final MutableLiveData<User> userLiveData = new MutableLiveData<>();

    public LiveData<User> user() {
        return userLiveData;
    }

    public void loadUser(String userId) {
        repository.getUser(userId, new UserRepository.UserCallback() {
            @Override
            public void onSuccess(User user) {
                userLiveData.setValue(user);
            }

            @Override
            public void onError(Exception exception) {
                userLiveData.setValue(null);
            }
        });
    }
}
