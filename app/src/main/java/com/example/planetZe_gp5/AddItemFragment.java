package com.example.planetZe_gp5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class AddItemFragment extends Fragment {
    private EditText editTextUser, editTextFootprint;
    private Spinner spinnerCategory;
    private Button buttonAdd;

    private DataModel dbModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item, container, false);

        editTextUser = view.findViewById(R.id.editTextUser);
        editTextFootprint = view.findViewById(R.id.editTextFootprint);
        spinnerCategory = view.findViewById(R.id.spinnerCategory);
        buttonAdd = view.findViewById(R.id.buttonAdd);

        dbModel = new DataModel();

        // Set up the spinner with categories
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        return view;
    }

    private void addItem() {
        String user = editTextUser.getText().toString().trim();
        String footprint = editTextFootprint.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString().toLowerCase();

        if (user.isEmpty() || footprint.isEmpty()) {
            Toast.makeText(getContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }


        if (dbModel.writeData("categories/" + category, user, footprint)) {
            Toast.makeText(getContext(), "Item added", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Failed to add item", Toast.LENGTH_SHORT).show();
        }
    }
}
