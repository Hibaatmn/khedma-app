package com.khedma.app.repositories;

import androidx.annotation.NonNull;

import com.khedma.app.models.Demand;
import com.khedma.app.utils.Constants;
import com.khedma.app.utils.FirebaseConfig;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class DemandRepository {

    public interface DemandsCallback {
        void onSuccess(@NonNull List<Demand> demands);
        void onError(@NonNull Exception exception);
    }

    public void getDemands(DemandsCallback callback) {
        FirebaseConfig.firestore().collection(Constants.DEMANDS_COLLECTION)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    List<Demand> list = new ArrayList<>();
                    for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                        Demand demand = doc.toObject(Demand.class);
                        list.add(demand);
                    }
                    callback.onSuccess(list);
                })
                .addOnFailureListener(callback::onError);
    }
}
