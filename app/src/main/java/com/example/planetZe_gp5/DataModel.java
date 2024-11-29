package com.example.planetZe_gp5;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class DataModel {
    private final FirebaseDatabase db;

    private DatabaseReference itemsRef;

    private String values = " ";

    public DataModel() {
        db = FirebaseDatabase.getInstance("https://planetze--group-5-default-rtdb.firebaseio.com/");
    }

    /**
     * store the value of the given path in a list.
     * @param path the path to a key.
     * @param list string array list to store the value.
     */
    public void readValue(String path, List<String> list) {
        itemsRef = db.getReference(path);
        itemsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue().toString();
                list.add(value);
                if (value != null) Log.d("FirebaseData", "data get");
                else Log.d("FirebaseData", "data failed");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }

    public void readValue2(String path, List<String> data) {
        itemsRef = db.getReference(path);
        final CountDownLatch latch = new CountDownLatch(1);

        itemsRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot snapshot = task.getResult();
                if (snapshot.exists()){
                    String value = snapshot.getValue().toString();
                    data.add(value);
                    Log.d("getData", "Value" + value);
                }
                else{
                    Log.d("getData", "Data unfounded");
                }
                latch.countDown();
            }
            else {
                Log.e("getData", "Error" + task.getException().getMessage());
            }
        });


        try {
            latch.await();
        } catch (InterruptedException e){
            Log.e("Data", "Latch interrupted", e);
        }

    }

    public String getValues(){
        return values;
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

    public void readItemData(String path, List<Item> itemList, ItemAdapter itemAdapter) {
        itemsRef = db.getReference(path);
        itemsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                itemList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Item item = snapshot.getValue(Item.class);
                    itemList.add(item);
                }
                itemAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle possible errors
            }
        });
    }

    /**
     * store values for key "target" in list that has the same value of "object" for key "key.
     * @param path path to key value pairs.
     * @param list string array list to store the values.
     */
    public void searchCorrData(String path, List<Object> list, String key, String object,
                               String target){
        itemsRef = db.getReference(path);
        itemsRef.orderByChild(key).equalTo(object).addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot branchSnapshot : snapshot.getChildren()) {
                            double value = (double) branchSnapshot.child(target).getValue();
                            list.add(value);
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

    /**
     * Add an item to the FireBase database.
     * @param path the path where the item will be added example "users/countries/".
     * @param title
     * @param author
     * @return true if and only if item is added.
     */
    public boolean writeData(String path, String title, String author) {
        itemsRef = db.getReference(path);
        String id = itemsRef.push().getKey();
        if (id == null) {
            return false;
        }
        Item item = new Item(id, title, author);
        itemsRef.child(id).setValue(item);
        return true;
    }

    /**
     * Deletes an item. Currently returns true every time.
     * @param path the path in the database.
     * @param title title of the item to delete.
     * @return true if and only if item is deleted.
     */
    public boolean deleteData(String path, String title) {
        itemsRef = db.getReference(path);
        itemsRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean itemFound = false;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Item item = snapshot.getValue(Item.class);
                    if (item != null && item.getUser().equalsIgnoreCase(title)) {
                        snapshot.getRef().removeValue();
                        itemFound = true;
                        break;
                    }
                }
                if (!itemFound) {
//                   Toast.makeText(getContext(), "Item not found", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getContext(), "Database error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return true;
    }
}
