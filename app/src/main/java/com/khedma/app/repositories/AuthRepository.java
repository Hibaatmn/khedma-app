package com.khedma.app.repositories;

import androidx.annotation.NonNull;

import com.khedma.app.models.User;
import com.khedma.app.utils.Constants;
import com.khedma.app.utils.FirebaseConfig;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;

public class AuthRepository {

    public interface AuthCallback {
        void onSuccess(@NonNull FirebaseUser user);
        void onError(@NonNull Exception exception);
    }

    public void login(String email, String password, AuthCallback callback) {
        FirebaseConfig.auth()
                .signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(result -> callback.onSuccess(result.getUser()))
                .addOnFailureListener(callback::onError);
    }

    public void register(String fullName, String email, String password, String role, AuthCallback callback) {
        FirebaseConfig.auth()
                .createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener((AuthResult result) -> {
                    FirebaseUser firebaseUser = result.getUser();
                    if (firebaseUser == null) {
                        callback.onError(new IllegalStateException("User is null"));
                        return;
                    }
                    User user = new User(firebaseUser.getUid(), fullName, email, role);
                    FirebaseConfig.firestore()
                            .collection(Constants.USERS_COLLECTION)
                            .document(firebaseUser.getUid())
                            .set(user)
                            .addOnSuccessListener(unused -> callback.onSuccess(firebaseUser))
                            .addOnFailureListener(callback::onError);
                })
                .addOnFailureListener(callback::onError);
    }

    public void logout() {
        FirebaseConfig.auth().signOut();
    }
}
