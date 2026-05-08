package com.khedma.app.fragments.client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.khedma.app.adapters.DemandAdapter;
import com.khedma.app.databinding.FragmentClientDemandsBinding;
import com.khedma.app.viewmodels.DemandViewModel;

public class ClientDemandsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentClientDemandsBinding binding = FragmentClientDemandsBinding.inflate(inflater, container, false);
        DemandAdapter adapter = new DemandAdapter();
        binding.rvDemands.setAdapter(adapter);

        DemandViewModel viewModel = new ViewModelProvider(this).get(DemandViewModel.class);
        viewModel.demands().observe(getViewLifecycleOwner(), adapter::submitList);
        viewModel.loadDemands();
        return binding.getRoot();
    }
}
