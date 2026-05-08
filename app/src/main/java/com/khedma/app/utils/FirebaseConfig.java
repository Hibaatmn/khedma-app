package com.khedma.app.utils;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public final class FirebaseConfig {
    private static final FirebaseAuth AUTH = FirebaseAuth.getInstance();
    private static final FirebaseFirestore DB = FirebaseFirestore.getInstance();

    private FirebaseConfig() {
    }

    public static FirebaseAuth auth() {
        return AUTH;
    }

    public static FirebaseFirestore firestore() {
        return DB;
    }
}
