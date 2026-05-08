package com.khedma.app.repositories;

import androidx.annotation.NonNull;

import com.khedma.app.models.User;
import com.khedma.app.utils.Constants;
import com.khedma.app.utils.FirebaseConfig;

public class UserRepository {

    public interface UserCallback {
        void onSuccess(@NonNull User user);
        void onError(@NonNull Exception exception);
    }

    public void getUser(String userId, UserCallback callback) {
        FirebaseConfig.firestore().collection(Constants.USERS_COLLECTION)
                .document(userId)
                .get()
                .addOnSuccessListener(documentSnapshot -> {
                    User user = documentSnapshot.toObject(User.class);
                    if (user == null) {
                        callback.onError(new IllegalStateException("User not found"));
                    } else {
                        callback.onSuccess(user);
                    }
                })
                .addOnFailureListener(callback::onError);
    }
}
