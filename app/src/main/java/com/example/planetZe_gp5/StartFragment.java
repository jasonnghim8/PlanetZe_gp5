package com.example.planetZe_gp5;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.planetZe_gp5.acf.ACFQuestion;
import com.example.planetZe_gp5.acf.ACFResults;
import com.example.planetZe_gp5.acf.CountrySelection;
import com.example.planetZe_gp5.acf.Welcome;
import com.example.planetZe_gp5.ecotracker.EcoTrackerMainFragment;

public class StartFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_start_fragment, container, false);

        Button buttonEcoTracker = view.findViewById(R.id.buttonEcotracker);
        Button buttonACF = view.findViewById(R.id.buttonACF);
        Button buttonLogin = view.findViewById(R.id.buttonLogin);
        Button buttonEcoGauge = view.findViewById(R.id.buttonEcoGauge);

        buttonEcoTracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadFragment(new EcoTrackerMainFragment());
            }
        });

        buttonACF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Welcome.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
                startActivity(intent);
            }
        });

        return view;
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
