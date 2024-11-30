package com.example.planetZe_gp5.ACF;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.planetZe_gp5.DataModel;
import com.example.planetZe_gp5.QnA;
import com.example.planetZe_gp5.R;

import java.util.HashMap;

public class ACFQuestion extends AppCompatActivity {

    private TextView questionTextView;
    private LinearLayout buttonContainer;
    private int currentQuestionIndex = 1; // question number: start with 1
    private HashMap<String, Integer> selectedAnswers = new HashMap<>(); // Saved all selected answers
    private String userid;

    private final String[] questions = QnA.question; //calling question from another document
    private final String[][] answers = QnA.answer; //calling answer from another document
    private DataModel dbModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acfquestion);

        dbModel = DataModel.getInstance();

        Intent intent = getIntent();
        userid = intent.getStringExtra("userid");
        if (userid == null) userid = "test";

        questionTextView = findViewById(R.id.question_text);
        buttonContainer = findViewById(R.id.answer_buttons_container);

        Question(); //starting another question
    }

    private void Question() {
        buttonContainer.removeAllViews(); // delete previous question

        questionTextView.setText(questions[currentQuestionIndex-1]); // show question

        int buttonNumber = 1; // counting from above as 1

        for (String answer : answers[currentQuestionIndex-1]) {
            Button button = new Button(this);
            button.setText(answer);
            button.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            button.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#E0E0E0"))); // for UI structure
            button.setTextColor(Color.BLACK); // for UI structure
            button.setPadding(16, 16, 16, 16); // for UI structure

            int currentButtonNumber = buttonNumber;
            button.setOnClickListener(v -> {
                selectedAnswers.put(Integer.toString(currentQuestionIndex), currentButtonNumber);
                // putting key as question number and value as button number count from above

                if (currentQuestionIndex == 1 && currentButtonNumber == 2) {
                    currentQuestionIndex = 4; // Jump to question 4
                } else if (currentQuestionIndex == 8 && currentButtonNumber != 4) {
                    currentQuestionIndex = 13; // Jump to question 10
                } else if (currentQuestionIndex < questions.length) {
                    currentQuestionIndex++; // avoid crash on exceeding length of question
                } else {
                    dbModel.writeData("Users/" + userid + "/annualCarbonFootprint", selectedAnswers);
                    Toast.makeText(this, "All answers saved successfully!", Toast.LENGTH_SHORT).show();
                    // continue after all questions answered
                    Calculation save = Calculation.getInstance(userid);
                    save.calculateCarbonFootprint(selectedAnswers);
                    Intent cont = new Intent(ACFQuestion.this, ACFResults.class);
                    cont.putExtra("userid", userid);
                    startActivity(cont);
                    return;
                }

                Question();
            });


            buttonContainer.addView(button);
            buttonNumber++;
        }
    }
}
