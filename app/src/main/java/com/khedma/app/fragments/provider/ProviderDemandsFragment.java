package com.khedma.app.fragments.provider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.khedma.app.adapters.DemandAdapter;
import com.khedma.app.databinding.FragmentProviderDemandsBinding;
import com.khedma.app.viewmodels.DemandViewModel;

public class ProviderDemandsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentProviderDemandsBinding binding = FragmentProviderDemandsBinding.inflate(inflater, container, false);
        DemandAdapter adapter = new DemandAdapter();
        binding.rvDemands.setAdapter(adapter);

        DemandViewModel viewModel = new ViewModelProvider(this).get(DemandViewModel.class);
        viewModel.demands().observe(getViewLifecycleOwner(), adapter::submitList);
        viewModel.loadDemands();
        return binding.getRoot();
    }
}
