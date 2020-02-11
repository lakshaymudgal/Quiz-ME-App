package com.example.quizme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Quiz extends AppCompatActivity {

    public static final String EXTRA_SCORE = "extra score";
    private static final long COUNTDOWN_TIMER = 20000;

    private static final String KEY_SCORE = "keyscore";
    private static final String KEY_QUES = "keyques";
    private static final String KEY_QUESCOUNT = "quescount";
    private static final String KEY_TIMER = "timer";
    private static final String KEY_ANS = "keyans";

    private TextView tvQues;
    private TextView tvscore;
    private TextView tvcountQues;
    private TextView tvCountDown;
    private TextView tvCategory;
    private RadioGroup radioGroup;
    private RadioButton radioButton1;
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private Button btSubmit;
    private TextView tvdifficulty;

    private ColorStateList rGDefaultColor;
    private ColorStateList CDDefaultColor;

    private CountDownTimer countDownTimer;
    private long timeLeft;

    private ArrayList<Questions> questionsList;
    private int quesCounter;
    private int totalQuesCounter;
    private Questions currentQues;

    private int score;
    private boolean ans;
    private long backPressTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        tvQues = findViewById(R.id.actualQues);
        tvscore = findViewById(R.id.score);
        tvcountQues = findViewById(R.id.questionsCounter);
        tvCountDown = findViewById(R.id.timer);
        radioGroup = findViewById(R.id.rgroup);
        radioButton1 = findViewById(R.id.optn_1);
        radioButton2 = findViewById(R.id.optn_2);
        radioButton3 = findViewById(R.id.optn_3);
        btSubmit = findViewById(R.id.submit);
        tvCategory = findViewById(R.id.category);
        tvdifficulty = findViewById(R.id.difficulty);

        rGDefaultColor = radioButton1.getTextColors();
        CDDefaultColor = tvCountDown.getTextColors();

        Intent intent = getIntent();
        int CategId = intent.getIntExtra(MainActivity.EXTRA_CATEG_ID, 0);
        String category = intent.getStringExtra(MainActivity.EXTRA_CATEG_NAME);
        String difficulty = intent.getStringExtra(MainActivity.EXTRA_DIFF);

        tvCategory.setText("Category : " + category);
        tvdifficulty.setText("Difficulty : " + difficulty);

        if(savedInstanceState == null) {
            QuizDb quizDb = QuizDb.getInstance(this);
            questionsList = quizDb.getQues(CategId, difficulty);
            totalQuesCounter = questionsList.size();
            Collections.shuffle(questionsList);
            nextQues();
        }else {

            questionsList = savedInstanceState.getParcelableArrayList(KEY_QUES);
            if(questionsList == null){
                finish();
            }
            totalQuesCounter = questionsList.size();
            quesCounter = savedInstanceState.getInt(KEY_QUESCOUNT);
            currentQues = questionsList.get(quesCounter - 1);
            timeLeft = savedInstanceState.getLong(KEY_TIMER);
            score = savedInstanceState.getInt(KEY_SCORE);
            ans = savedInstanceState.getBoolean(KEY_ANS);

            if(!ans){
                countDown();
            }else {
                updateCountDownTimer();
                solution();
            }
        }

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!ans){
                    if(radioButton1.isChecked() || radioButton2.isChecked() || radioButton3.isChecked()){
                        checkAns();
                    }else {
                        Toast.makeText(Quiz.this, "Please Select an Answer", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    nextQues();
                }
            }
        });

    }

    private void nextQues() {

        radioButton1.setTextColor(rGDefaultColor);
        radioButton2.setTextColor(rGDefaultColor);
        radioButton3.setTextColor(rGDefaultColor);
        radioGroup.clearCheck();

        if(quesCounter < totalQuesCounter){

            currentQues = questionsList.get(quesCounter);
            tvQues.setText(currentQues.getQUESTION());
            radioButton1.setText(currentQues.getOPTION_1());
            radioButton2.setText(currentQues.getOPTION_2());
            radioButton3.setText(currentQues.getOPTION_3());

            quesCounter++;
            tvcountQues.setText("Question " + quesCounter + "/" + totalQuesCounter);
            ans = false;
            btSubmit.setText("Submit");

            timeLeft = COUNTDOWN_TIMER;
            countDown();
        }else {
            finishQuiz();
        }
    }

    private void countDown(){

        countDownTimer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateCountDownTimer();
            }

            @Override
            public void onFinish() {
                timeLeft = 0;
                updateCountDownTimer();
                checkAns();
            }
        }.start();
    }

    private void updateCountDownTimer(){

        int mint = (int) (timeLeft / 1000) / 60;
        int sec = (int) (timeLeft / 1000) % 60;

        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", mint ,sec);
        tvCountDown.setText(timeFormatted);

        if(timeLeft < 7000){
            tvCountDown.setTextColor(Color.RED);
        }else {
            tvCountDown.setTextColor(CDDefaultColor);
        }
    }

    private void checkAns(){
        ans = true;
        countDownTimer.cancel();

        RadioButton rbSelected = findViewById(radioGroup.getCheckedRadioButtonId());
        int ansnr = radioGroup.indexOfChild(rbSelected) + 1;
        if(ansnr == currentQues.getANSWER_NO()){
            score++;
            tvscore.setText("Score " + score);
        }
        solution();
    }

    private void solution(){

        radioButton1.setTextColor(Color.RED);
        radioButton2.setTextColor(Color.RED);
        radioButton3.setTextColor(Color.RED);

        switch (currentQues.getANSWER_NO()){
            case 1 :
                radioButton1.setTextColor(Color.GREEN);
                tvQues.setText("A is correct");
                break;

            case 2 :
                radioButton2.setTextColor(Color.GREEN);
                tvQues.setText("B 2 is correct");
                break;

            case 3 :
                radioButton3.setTextColor(Color.GREEN);
                tvQues.setText("C 3 is correct");
                break;
        }

        if(quesCounter < totalQuesCounter){
            btSubmit.setText("Next");
        }else {
            btSubmit.setText("Finish");
        }
    }

    private void finishQuiz() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra(EXTRA_SCORE, score);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    @Override
    public void onBackPressed() {
        if(backPressTime + 2000 > System.currentTimeMillis()){
            finishQuiz();
        }else {
            Toast.makeText(this, "Press back again to exit", Toast.LENGTH_SHORT).show();
        }
        backPressTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer != null){
            countDownTimer.cancel();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_SCORE, score);
        outState.putInt(KEY_QUESCOUNT, quesCounter);
        outState.putLong(KEY_TIMER, timeLeft);
        outState.putParcelableArrayList(KEY_QUES, questionsList);
        outState.putBoolean(KEY_ANS, ans);
    }
}
