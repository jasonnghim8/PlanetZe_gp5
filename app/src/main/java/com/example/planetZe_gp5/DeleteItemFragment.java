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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DeleteItemFragment extends Fragment {
    private EditText editTextTitle;
    private Spinner spinnerCategory;
    private Button buttonDelete;

    private FirebaseDatabase db;
    private DatabaseReference itemsRef;
    private DataModel dbModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_delete_item, container, false);

        editTextTitle = view.findViewById(R.id.editTextTitle);
        spinnerCategory = view.findViewById(R.id.spinnerCategory);
        buttonDelete = view.findViewById(R.id.buttonDelete);

        dbModel = new DataModel();
        db = FirebaseDatabase.getInstance("https://planetze--group-5-default-rtdb.firebaseio.com/");

        // Set up the spinner with categories
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.categories_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(adapter);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItemByTitle();
            }
        });

        return view;
    }

    private void deleteItemByTitle() {
        String title = editTextTitle.getText().toString().trim();
        String category = spinnerCategory.getSelectedItem().toString().toLowerCase();

        if (title.isEmpty()) {
            Toast.makeText(getContext(), "Please enter item title", Toast.LENGTH_SHORT).show();
            return;
        }

        if (dbModel.deleteData("categories/" + category, title)) {
            Toast.makeText(getContext(), "Item deleted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Failed to delete item", Toast.LENGTH_SHORT).show();
        }
    }
}
