package com.example.planetZe_gp5;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;

public class ACFQuestion extends AppCompatActivity {

    private TextView questionTextView;
    private LinearLayout buttonContainer;
    private int currentQuestionIndex = 1; // question number: start with 1
    private HashMap<Integer, Integer> selectedAnswers = new HashMap<>(); // Saved all selected answers
    private ACFQButtonHandler buttonHandler; // Instance of ACFQButtonHandler

    private final String[] questions = QnA.question; //calling question from another document
    private final String[][] answers = QnA.answer; //calling answer from another document

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acfquestion);

        questionTextView = findViewById(R.id.question_text);
        buttonContainer = findViewById(R.id.answer_buttons_container);

        buttonHandler = new ACFQButtonHandler(); // specified handler only for this document

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
                selectedAnswers.put(currentQuestionIndex, currentButtonNumber); // puting key as question number and value as button number count from above

                if (currentQuestionIndex == 1 && currentButtonNumber == 2) {
                    currentQuestionIndex = 4; // Jump to question 4
                } else if (currentQuestionIndex == 8 && currentButtonNumber != 4) {
                    currentQuestionIndex = 13; // Jump to question 10
                } else if (currentQuestionIndex < questions.length) {
                    currentQuestionIndex++; // avoid crash on exceeding length of question
                } else {
                    buttonHandler.saveAllAnswersToFirebase(this, selectedAnswers);
                    finish(); // stop quiz, will be changed due to merging document
                    return;
                }

                Question();
            });


            buttonContainer.addView(button);
            buttonNumber++;
        }
    }
}

