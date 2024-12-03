package com.example.planetZe_gp5;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.planetZe_gp5.ecotracker.AverageFootprint;
import com.example.planetZe_gp5.ecotracker.Calculation;
import com.example.planetZe_gp5.ecotracker.Item;
import com.example.planetZe_gp5.ecotracker.ItemAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.List;

final public class DataModel {
    private static DataModel dbModel;
    private final FirebaseDatabase db;
    private DatabaseReference ref;
    private String userPath;
    private String ecoTrackerPath;

    private DataModel() {
        db = FirebaseDatabase.getInstance("https://planetze--group-5-default-rtdb.firebaseio.com/");
        ecoTrackerPath = "ecotracker/";
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

    public void readUserValue(String path, Observer observer) {
        readValue(userPath + path, observer);
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

    public void readUserValueOnChange(String path, Observer observer) {
        readValueOnChange(userPath + path, observer);
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
        ref = db.getReference(userPath + ecoTrackerPath);
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

    public void readAllItemFootprints(HashMap<String, AverageFootprint> map, Observer observer) {
        ref = db.getReference(userPath + "ecotracker/");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot item : snapshot.getChildren()) {
                        double itemFootprint = Calculation.calculateFootprint(item.getKey(), (String)item.getValue());
                        AverageFootprint value;

                        if (map.containsKey(item.getKey())) {
                            value = map.get(item.getKey());
                        } else {
                            value = new AverageFootprint();
                            map.put(item.getKey(), value);
                        }
                        value.footprint += itemFootprint;
                        value.count++;
                    }
                }
                observer.updateAfterRead(map);
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
        writeData(userPath + path, value);
    }

    public void writeEcoTrackerData(String path, Object value) {
        writeUserData(ecoTrackerPath + "/" + path, value);
    }

    public void deleteEntry(String path, String key) {
        db.getReference(path + "/" + key).removeValue();
    }

    public void deleteUserEntry(String path, String key) {
        deleteEntry(userPath + path, key);
    }

    public void setUserPath(String userid) {
        userPath = "users/" + userid + "/";
    }

    public String getEcoTrackerPath() {
        return ecoTrackerPath;
    }

    public void setEcoTrackerPath(String path) {
        ecoTrackerPath = "ecotracker/" + path;
    }
}
