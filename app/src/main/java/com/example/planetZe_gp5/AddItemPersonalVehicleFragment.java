package com.example.planetZe_gp5;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddItemPersonalVehicleFragment extends Fragment{
    private String category = "Personal Vehicle";
    private Button buttonAdd;
    private EditText editTextDistanceDriven, editTextVehicleType;
    private FirebaseDatabase db;
    private DatabaseReference itemsRef;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_item_personal_vehicle, container, false);

        editTextDistanceDriven = view.findViewById(R.id.editTextDistanceDriven);
        editTextVehicleType = view.findViewById(R.id.editTextVehicleType);
        buttonAdd = view.findViewById(R.id.buttonAdd);

        db = FirebaseDatabase.getInstance("https://planetze--group-5-default-rtdb.firebaseio.com/");

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });

        return view;
    }

    private void addItem() {
        String DistanceDriven = editTextDistanceDriven.getText().toString().trim();
        String VehicleType = editTextVehicleType.getText().toString().trim();

        if (DistanceDriven.isEmpty() || VehicleType.isEmpty()) {
            Toast.makeText(getContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        itemsRef = db.getReference("categories/" + category);
        String id = itemsRef.push().getKey();
        PersonalVehicleItem item = new PersonalVehicleItem(id, DistanceDriven, VehicleType);

        itemsRef.child(id).setValue(item).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                Toast.makeText(getContext(), "Item added", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Failed to add item", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
