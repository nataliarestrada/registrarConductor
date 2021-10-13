package com.example.registrarconductor.ui.inspector;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.registrarconductor.R;

public class InpesctorFragment extends Fragment {

    private InspectorViewModel galleryViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(InspectorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_inspector, container, false);
        //final TextView textView = root.findViewById(R.id.text_gallery);

        return root;
    }
}