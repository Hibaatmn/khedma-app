package com.khedma.app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.khedma.app.models.Demand;
import com.khedma.app.repositories.DemandRepository;

import java.util.ArrayList;
import java.util.List;

public class DemandViewModel extends ViewModel {
    private final DemandRepository repository = new DemandRepository();
    private final MutableLiveData<List<Demand>> demandsLiveData = new MutableLiveData<>(new ArrayList<>());

    public LiveData<List<Demand>> demands() {
        return demandsLiveData;
    }

    public void loadDemands() {
        repository.getDemands(new DemandRepository.DemandsCallback() {
            @Override
            public void onSuccess(List<Demand> demands) {
                demandsLiveData.setValue(demands);
            }

            @Override
            public void onError(Exception exception) {
                demandsLiveData.setValue(new ArrayList<>());
            }
        });
    }
}
