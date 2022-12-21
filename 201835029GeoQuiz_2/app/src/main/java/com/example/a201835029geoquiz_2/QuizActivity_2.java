package com.example.a201835029geoquiz_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class QuizActivity_2 extends AppCompatActivity {

    private TextView tvQuestion, tvScore, tvQuestionNo, tvTimer;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3;
    private Button btnNext;

    int totalQuestions;
    int qCounter = 0;
    int score;

    ColorStateList dfRbColor;
    boolean answered;

    private QuestionModel currentQuestion;


    private List<QuestionModel> questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);

        questionsList = new ArrayList<>();
        tvQuestion = findViewById(R.id.textQuestion);
        tvScore= findViewById(R.id.textScore);
        tvQuestionNo = findViewById(R.id.textQuestionNo);
        tvTimer= findViewById(R.id.textTimer);

        radioGroup = findViewById(R.id.radioGroup);
        rb1 = findViewById(R.id.rb1);
        rb2 = findViewById(R.id.rb2);
        rb3 = findViewById(R.id.rb3);
        btnNext = findViewById(R.id.btnNext);

        dfRbColor = rb1.getTextColors();


        addQuestions();
        totalQuestions = questionsList.size();
        showNextQuestion();

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (answered == false) {
                    if (rb1.isChecked() || rb2.isChecked() || rb3.isChecked()){
                        checkAnswer();
                    } else {
                        Toast.makeText(QuizActivity_2.this, "Please select an option", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }

            }
        });
    }

    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int answerNo = radioGroup.indexOfChild(rbSelected) +1;
        if (answerNo == currentQuestion.getCorrectAnsNo()){
            score ++;
            tvScore.setText("Score: "+score);

        }
        rb1.setTextColor(Color.RED);
        rb2.setTextColor(Color.RED);
        rb3.setTextColor(Color.RED);
        switch (currentQuestion.getCorrectAnsNo()){
            case 1:
                rb1.setTextColor(Color.GREEN);
                break;
            case 2:
                rb2.setTextColor(Color.GREEN);
                break;
            case 3:
                rb3.setTextColor(Color.GREEN);
        }
        if (qCounter < totalQuestions){
            btnNext.setText("Next");
        }else {
            btnNext.setText("Finish" + "");
        }
    }

    private void showNextQuestion() {
        radioGroup.clearCheck();
        rb1.setTextColor(dfRbColor);
        rb2.setTextColor(dfRbColor);
        rb3.setTextColor(dfRbColor);

        if(qCounter < totalQuestions) {
            if(qCounter < totalQuestions)
                currentQuestion = questionsList.get(qCounter);
            tvQuestion.setText(currentQuestion.getQuestion());
            rb1.setText(currentQuestion.getOption1());
            rb2.setText(currentQuestion.getOption2());
            rb3.setText(currentQuestion.getOption3());

            qCounter++;
            btnNext.setText("Submit");
            tvQuestionNo.setText("Question: "+qCounter+"/"+totalQuestions );
            answered = false;

        }else {
            finish();
        }

    }



    private void addQuestions() {
        questionsList.add(new QuestionModel("Which U.S. state has the most active volcanoes?", "Washington", "Hawaii", "Alaska",3));
        questionsList.add(new QuestionModel("What razor-thin country accounts for more than half of the western coastline of South America?", "Chile", "Bolivia", "Peru", 1));
        questionsList.add(new QuestionModel("What river runs through Baghdad?", "Jordan", "Euphrates", "Tigris", 3));
        questionsList.add(new QuestionModel("What country has the most natural lakes?", "Australia", "Canada", "India", 2));
        questionsList.add(new QuestionModel("What is the only sea without any coasts?", "Sargasso Sea", "Adriatic Sea", "Celebes Sea", 1));
        questionsList.add(new QuestionModel("What percentage of the River Nile is located in Egypt?", "Sahara Desert", "Kufra,Libya", "Mcmurdo, Anctarctica", 3));
        questionsList.add(new QuestionModel("In what country can you visit Machu Picchu?", "Bolivia", "Columbia", "Peru", 3));
        questionsList.add(new QuestionModel("Which African nation has the most pyramids?", "Algeria", "Libya", "Sudan", 3));
        questionsList.add(new QuestionModel(" What African country served as the setting for Tatooine in Star Wars?", "Tunisia", "Ethiopia", "Gabon", 1));
        questionsList.add(new QuestionModel("Which is the tallest mountain in the world", "K2", "Lhotse", "Everest", 3));

    }
}