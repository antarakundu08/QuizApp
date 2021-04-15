package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuizPage1 extends AppCompatActivity {

    RadioGroup radioGroup;
    TextView ques,counter;
    RadioButton optionA;
    RadioButton optionB;
    RadioButton optionC;
    RadioButton optionD;
    Button confirm,skip;
    String rightAnswer;
    String Answer;
    List<Questions> questions;
    int score;
    static int qc=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_page1);

        confirm=findViewById(R.id.cnfm);
        //skip=findViewById(R.id.skip);
        ques=findViewById(R.id.ques);
        counter=findViewById(R.id.tv1);
        radioGroup=findViewById(R.id.radioGroup);
        optionA=findViewById(R.id.radioButton);
        optionB=findViewById(R.id.radioButton2);
        optionC=findViewById(R.id.radioButton3);
        optionD=findViewById(R.id.radioButton4);
        score=0;

        questions=new ArrayList<Questions>(){
            {
                add(new Questions("Which of the following is a Prime number?","B","27","31","105","75"));
                add(new Questions("Scalars are quantities that are described by__","A","Magnitude","Direction","Magnitude & Direction","Motion"));
                add(new Questions("Which one is NOT a harvest festival in India?","D","Onam","Lohri","Ugadi","Holi"));
                add(new Questions("The National Game of India is","C","Cricket","Tennis","Hockey","Football"));
                add(new Questions("One kilobyte (KB) is equal to","B","1,000 bits","1,024 bytes","1,024 megabytes","1,024 gigabytes"));
            }
        };
        loadQuestion();

    }
    @Override
    protected void onRestart(){
        super.onRestart();
        loadQuestion();
    }
    private void loadQuestion(){
        qc=6-questions.size();
        counter.setText("Question No.: "+qc+" out of 5");
        if(questions.size() > 0) {
            Questions q = questions.remove(0);
            ques.setText(q.getQuestion());
            List<String> answers = q.getAnswers();

            optionA.setText(answers.get(0));
            optionB.setText(answers.get(1));
            optionC.setText(answers.get(2));
            optionD.setText(answers.get(3));

            rightAnswer = q.getRightAnswer();
            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadAnswer(v);
                }
            });
        } else {
            Intent intent = new Intent(this, ShowScoreActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
            finish();
        }
    }

    public void loadAnswer(View view) {
        int op = radioGroup.getCheckedRadioButtonId();

        switch (op){
            case R.id.radioButton:
                Answer="A";
                break;

            case R.id.radioButton2:
                Answer="B";
                break;

            case R.id.radioButton3:
                Answer="C";
                break;

            case R.id.radioButton4:
                Answer="D";
                break;

            default:
                return;

        }

        radioGroup.clearCheck();

        isRightOrWrong(Answer);

    }

    private Intent isRightOrWrong(String Answer){
        Intent screen;
        if(Answer.equals(rightAnswer)) {
            this.score += 1;
            screen = new Intent(this, CorrectAnswer.class);
            startActivity(screen);

        }else {
            screen = new Intent(this, WrongAnswer.class);
            startActivity(screen);
        }

        return screen;
    }


}
