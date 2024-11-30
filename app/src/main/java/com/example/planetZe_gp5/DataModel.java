package com.example.planetZe_gp5;

import androidx.annotation.NonNull;

import com.example.planetZe_gp5.ecotracker.Item;
import com.example.planetZe_gp5.ecotracker.ItemAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

final public class DataModel {
    private static DataModel dbModel;
    private final FirebaseDatabase db;
    private DatabaseReference ref;
    public String ecoTrackerPath;

    private DataModel() {
        db = FirebaseDatabase.getInstance("https://planetze--group-5-default-rtdb.firebaseio.com/");
        ecoTrackerPath = "";
    }

    public static DataModel getInstance() {
        if (dbModel == null) {
            dbModel = new DataModel();
        }
        return dbModel;
    }

    public void readValue(String path, Observer observer) {
        ref = db.getReference(path);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
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
        ref = db.getReference(path);
        ref.addValueEventListener(new ValueEventListener() {
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
        ref = db.getReference(path);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
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
        ref = db.getReference(path);
        ref.orderByChild(key).equalTo(object).addListenerForSingleValueEvent(
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

    public void listTrackerValues(List<Item> list, ItemAdapter itemAdapter) {
        ref = db.getReference(ecoTrackerPath);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Item item = new Item(snapshot.getKey(), (String) snapshot.getValue());
                    list.add(item);
                }
                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }

    public void writeData(String path, Object value) {
        DatabaseReference myRef = db.getReference(path);
        myRef.setValue(value);
    }

    public void writeUserData(String path, Object value) {
        writeData("users/" + LocalData.userid + "/" + path, value);
    }

    public void writeEcoTrackerData(String path, Object value) {
        writeUserData(dbModel.ecoTrackerPath + "/" + path, value);
    }

    public void deleteEntry(String path, String key) {
        db.getReference(path + "/" + key).removeValue();
    }
}
