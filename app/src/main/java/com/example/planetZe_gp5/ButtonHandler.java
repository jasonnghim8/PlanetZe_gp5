package com.example.planetZe_gp5;

public class ButtonHandler {

    /**private FirebaseDatabase db;
    private DatabaseReference answersRef;

    public ButtonHandler() {
        // Initialize Firebase reference
        db = FirebaseDatabase.getInstance("https://planetze--group-5-default-rtdb.firebaseio.com/");
        answersRef = db.getReference("answers");
    }

    // Method to handle the button click
    public void handleButtonClick(View view, final List<String> answers, final int position) {
        // Assuming there's a button in the layout to save the selected answer
        Button saveButton = view.findViewById(R.id.saveButton);

        saveButton.setOnClickListener(v -> {
            // Get the selected answer from the list based on the position
            String selectedAnswer = answers.get(position);

            // Save the selected answer to Firebase
            saveAnswerToFirebase(selectedAnswer);

            // Show a toast for user feedback
            Toast.makeText(view.getContext(), "Answer saved: " + selectedAnswer, Toast.LENGTH_SHORT).show();
        });
    }

    // Method to save the answer to Firebase
    private void saveAnswerToFirebase(String answer) {
        // Push the selected answer to Firebase
        String key = answersRef.push().getKey();
        if (key != null) {
            answersRef.child(key).setValue(answer);
        }
    }**/
}

