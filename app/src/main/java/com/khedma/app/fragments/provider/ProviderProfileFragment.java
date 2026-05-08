package com.khedma.app.fragments.provider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.khedma.app.databinding.FragmentProviderProfileBinding;

public class ProviderProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentProviderProfileBinding binding = FragmentProviderProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
