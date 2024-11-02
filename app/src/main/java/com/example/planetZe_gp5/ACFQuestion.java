package com.example.planetZe_gp5;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ACFQuestion extends AppCompatActivity {
    private TextView question;
    private RecyclerView recyclerViewAnswers;
    private AnswerAdapter answerAdapter;
    private ArrayList<String> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acfquestion);

        question = findViewById(R.id.questionTextView);
        recyclerViewAnswers = findViewById(R.id.answerRecyclerView);
    }

    private void initializeQuestions(){

    }
}
