package com.khedma.app.fragments.client;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.khedma.app.databinding.FragmentClientProfileBinding;

public class ClientProfileFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FragmentClientProfileBinding binding = FragmentClientProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
}
