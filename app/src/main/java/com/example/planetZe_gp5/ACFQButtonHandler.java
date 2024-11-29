package com.example.planetZe_gp5;

import android.content.Context;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ACFQButtonHandler {

    private FirebaseDatabase db;
    private DatabaseReference answersRef;
    private String userid;

    public ACFQButtonHandler(String id) {
        this.userid = id;
        db = FirebaseDatabase.getInstance("https://planetze--group-5-default-rtdb.firebaseio.com/");
        answersRef = db.getReference("Users/" + userid);
    }

    public void saveAllAnswersToFirebase(Context context, HashMap<String, Integer> selectedAnswers) {
        String key = answersRef.push().getKey();
        if (key != null) {
            answersRef.child("annualCarbonFootprint").setValue(selectedAnswers).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    Toast.makeText(context, "All answers saved successfully!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Failed to save answers.", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            Toast.makeText(context, "Failed to generate key for answers.", Toast.LENGTH_SHORT).show();
        }
    }
}

