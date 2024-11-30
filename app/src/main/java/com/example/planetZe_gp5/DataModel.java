package com.example.planetZe_gp5;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class DataModel {
    private final FirebaseDatabase db;

    private DatabaseReference itemsRef;

    private String values = " ";

    public DataModel() {
        db = FirebaseDatabase.getInstance("https://planetze--group-5-default-rtdb.firebaseio.com/");
    }

    public void readValue(String path, Observer observer) {
        itemsRef = db.getReference(path);
        itemsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Object obj = dataSnapshot.getValue();
                String value = "";
                if (obj != null) {
                    value = obj.toString();
                }
                observer.updateAfterRead(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }

    public void readValueOnChange(String path, Observer observer) {
        itemsRef = db.getReference(path);
        itemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object obj = snapshot.getValue();
                String value = "";
                if (obj != null) {
                    value = obj.toString();
                }
                observer.updateAfterRead(value);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    /**
     * store values for all keys in list for a given path.
     * @param path path to key value pairs.
     * @param list string array list to store the values.
     */
    public void readData(String path, List<String> list) {
        itemsRef = db.getReference(path);
        itemsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String value = snapshot.getValue().toString();
                    list.add(value);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors
                list.add("0");
            }
        });
    }

    /**
     * store values for key "target" in list that has the same value of "object" for key "key.
     * @param path path to key value pairs.
     * @param observer observer to notify when reading is done.
     */
    public void searchCorrData(String path, Observer observer, String key, String object,
                               String target){
        itemsRef = db.getReference(path);
        itemsRef.orderByChild(key).equalTo(object).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot branchSnapshot : snapshot.getChildren()) {
                            double value = (double) branchSnapshot.child(target).getValue();
                            observer.updateAfterRead(value);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );
    }

    public void writeData(String path, Object value) {
        DatabaseReference myRef = db.getReference(path);
        myRef.setValue(value);
    }
}
