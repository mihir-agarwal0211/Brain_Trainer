package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswers;
    TextView resultTextView;
    TextView scoreTextView;
    int score = 0;
    int numberOfQuestions=0;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView problemTextView;
    TextView finalResultTextView;
    TextView timerTextView;
    CountDownTimer timer;
    Button playAgain;
    ConstraintLayout gameLayout;
    ConstraintLayout finalLayout;

    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(view);
    }

    public void playAgain(View view){
        score = 0;
        numberOfQuestions=0;
        //playAgain.setVisibility(View.INVISIBLE);
        finalLayout.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        timerTextView.setText("30s");
        scoreTextView.setText("0/0");
        resultTextView.setText("");

        timer = new CountDownTimer(30000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText((int) (millisUntilFinished/1000)+ "s");
            }

            @Override
            public void onFinish() {
                //resultTextView.setText("Done!");
                //playAgain.setVisibility(View.VISIBLE);
                finalResultTextView.setText("Your Final Score is " + scoreTextView.getText());
                gameLayout.setVisibility(View.INVISIBLE);
                finalLayout.setVisibility(View.VISIBLE);
            }
        }.start();
        setQuestion();
    }

    @SuppressLint("SetTextI18n")
    public void chooseAnswer(View view){

        if(Integer.toString(locationOfCorrectAnswers).equals(view.getTag().toString()))
        {resultTextView.setText("Correct");
            score++;}
        else{
            resultTextView.setText("Wrong :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" + Integer.toString(numberOfQuestions));
        setQuestion();
    }

    @SuppressLint("SetTextI18n")
    public void setQuestion(){
        Random rand = new Random();
        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        problemTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswers = rand.nextInt(4);

        answers.clear();
        for (int i=0;i<4;i++){
            if(i==locationOfCorrectAnswers)
                answers.add(a+b);
            else{
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer==a+b){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        problemTextView = findViewById(R.id.problemTextView);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        finalResultTextView = findViewById(R.id.finalResultTextView);

         button0 = findViewById(R.id.button0);
         button1 = findViewById(R.id.button1);
         button2 = findViewById(R.id.button2);
         button3 = findViewById(R.id.button3);
         playAgain =findViewById(R.id.playAgainButton);

         gameLayout = findViewById(R.id.gameLayout);
         finalLayout = findViewById(R.id.finalLayout);

        goButton=findViewById(R.id.goButton);


        finalLayout.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);




    }
}
