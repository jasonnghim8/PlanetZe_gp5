package com.example.planetZe_gp5.ecotracker;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.planetZe_gp5.DataModel;
import com.example.planetZe_gp5.LocalData;
import com.example.planetZe_gp5.R;

public class EcoTrackerAddItemFragment extends Fragment {
    private Spinner spinner1, spinner2, spinner3;
    private EditText input;
    private TextView questionText;
    private Button buttonAdd;
    private DataModel dbModel;
    private String questionPath;
    private final int[] subCategories = {R.array.categoryTransportation, R.array.categoryFood, R.array.categoryExpenses};
    private final int[][] subSubCategories = {
            {R.array.categoryPersonalVehicle, R.array.categoryPublicTransportation, R.array.emptyArray, R.array.categoryFlight},
            {},
            {R.array.categoryElectronics, R.array.categoryEnergyBills, R.array.categoryOther}};

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item_ecotracker, container, false);

        questionText = view.findViewById(R.id.questionText);
        input = view.findViewById(R.id.editTextNumber);
        spinner1 = view.findViewById(R.id.spinnerCategory);
        spinner2 = view.findViewById(R.id.subSpinnerCategory);
        spinner3 = view.findViewById(R.id.subsubSpinnerCategory);
        buttonAdd = view.findViewById(R.id.buttonAdd);

        dbModel = DataModel.getInstance();
        questionPath = "";

        spinner3.setVisibility(View.GONE);
        setupSpinner(spinner1, R.array.categories);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateQuestionText();

                setupSpinner(spinner2, subCategories[position]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateQuestionText();

                int spinner1Pos = spinner1.getSelectedItemPosition();
                if (position == 3 && spinner1Pos == 2) {
                    spinner3.setVisibility(View.VISIBLE);
                    setupSpinner(spinner3, R.array.categoryOther);
                } else if (subSubCategories[spinner1Pos].length == 0
                        || subSubCategories[spinner1Pos][position] == R.array.emptyArray || (position == 0 && spinner1Pos == 2)) {
                    spinner3.setVisibility(View.GONE);
                } else if ((position == 1 || position == 2 || position == 3) && spinner1Pos == 2) {
                    spinner3.setVisibility(View.VISIBLE);
                    setupSpinner(spinner3, subSubCategories[spinner1Pos][position - 1]);
                } else {
                    spinner3.setVisibility(View.VISIBLE);
                    setupSpinner(spinner3, subSubCategories[spinner1Pos][position]);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateQuestionText();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return view;
     }

     private void updateQuestionText() {
        int i = Math.max(spinner1.getSelectedItemPosition(), 0);
        int j = Math.max(spinner2.getSelectedItemPosition(), 0);
        int k = Math.max(spinner3.getSelectedItemPosition(), 0);

        if(i == 0 && j == 0) {
            questionPath = (("" + i) + j) + k;
            questionText.setText(LocalData.ETQuestions[i][j][0]);
        }else {
            questionPath = (("" + i) + j) + k;
            questionText.setText(LocalData.ETQuestions[i][j][k]);
        }
     }

    private void setupSpinner(Spinner spinner, int arrayID) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                arrayID, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void addItem() {
        String text = input.getText().toString().trim();
        if (text.isEmpty()) {
            Toast.makeText(getContext(), "Please enter a value", Toast.LENGTH_SHORT).show();
            return;
        }
        dbModel.writeEcoTrackerData(questionPath, text);
        Toast.makeText(getContext(), "Item added", Toast.LENGTH_SHORT).show();
    }
}
