package com.example.quizme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.util.ULocale;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_QUIZ_CODE = 1;
    public static final String EXTRA_CATEG_ID = "extracategoryid";
    public static final String EXTRA_CATEG_NAME = "extracategoryname";
    public static final String EXTRA_DIFF = "extradifficulty";

    public static final String S_PREF = "shared_pref";
    public static final String HSCORE_KEY = "highcore_key";

    private TextView tvHighScore;
    private int highscore;
    private Spinner spinner_diffi;
    private Spinner spinner_categ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvHighScore = findViewById(R.id.scoreCount);
        spinner_categ = findViewById(R.id.spinner_categ);
        spinner_diffi = findViewById(R.id.spinner_diffi);

        categories();
        difficultyLevel();
        getHighScore();

        Button startQuiz = findViewById(R.id.quizButton);

        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTest();
            }
        });
    }

    private void startTest(){
        Categories getCateg = (Categories) spinner_categ.getSelectedItem();
        int categId = getCateg.getId();
        String category = getCateg.getName();
        String difficulty = spinner_diffi.getSelectedItem().toString();

        Intent intent = new Intent(MainActivity.this, Quiz.class);
        intent.putExtra(EXTRA_CATEG_ID, categId);
        intent.putExtra(EXTRA_CATEG_NAME, category);
        intent.putExtra(EXTRA_DIFF, difficulty);
        startActivityForResult(intent, REQ_QUIZ_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQ_QUIZ_CODE){
            if(resultCode == RESULT_OK){
                int score = data.getIntExtra(Quiz.EXTRA_SCORE, 0);
                if(score > highscore){
                    updateHighScore(score);
                }
            }
        }

    }

    private void categories(){
        QuizDb quizDb = QuizDb.getInstance(this);
        List<Categories> categoriesList = quizDb.getAllCateg();
        ArrayAdapter<Categories> adapterCateg = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categoriesList);
        adapterCateg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_categ.setAdapter(adapterCateg);

    }

    private void difficultyLevel(){

        String[] difficultyLevel = Questions.getDifficultyLevel();

        ArrayAdapter<String> adapterDiffi = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, difficultyLevel);
        adapterDiffi.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_diffi.setAdapter(adapterDiffi);
    }

    private void getHighScore(){

        SharedPreferences sPreferences = getSharedPreferences(S_PREF, MODE_PRIVATE);
        highscore = sPreferences.getInt(HSCORE_KEY, 0);
        tvHighScore.setText("HighScore : " + highscore);
    }

    private void updateHighScore(int newHighScore) {
        highscore = newHighScore;
        tvHighScore.setText("HighScore : " + highscore);

        SharedPreferences preferences = getSharedPreferences(S_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(HSCORE_KEY, highscore);
        editor.apply();
    }

}
