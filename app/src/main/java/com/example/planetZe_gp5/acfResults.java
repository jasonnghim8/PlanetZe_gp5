package com.example.planetZe_gp5;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class acfResults extends AppCompatActivity {
    private TextView totalAcf;
    private Button cont;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        DataModel dbModel = new DataModel();
        List<String> acf = new ArrayList<String>();
        dbModel.readValue("Users/"+userId+"/annualCarbonFootprint",
                acf);
        setContentView(R.layout.activity_acftotal);

        totalAcf = this.findViewById(R.id.totalAcf);
        String line = R.string.acfResult + acf.get(0);
        totalAcf.setText(line);

        cont = findViewById(R.id.acfCont);
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(acfResults.this, acfResult2.class);
                startActivity(intent);
            }
        });
    }

}
