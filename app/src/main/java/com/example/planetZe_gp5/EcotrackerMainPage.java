package com.example.planetZe_gp5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EcotrackerMainPage extends Fragment {

    private TextView textViewemmisionDisplay;
    private FirebaseDatabase db;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_eco_tracker_main_page, container, false);

        textViewemmisionDisplay = view.findViewById(R.id.textViewemmisionDisplay);

        db = FirebaseDatabase.getInstance("https://planetze--group-5-default-rtdb.firebaseio.com/");

        return view;
    }
}


