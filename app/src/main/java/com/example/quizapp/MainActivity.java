package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

    private Button nxt;
    private static EditText nm;
    LottieAnimationView lottie;
    public static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        nxt=findViewById(R.id.nxt);
        nm=findViewById(R.id.et);
        lottie=findViewById(R.id.lottie);
        lottie.playAnimation();
        name=nm.getText().toString();
        nxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name=nm.getText().toString();
                if(name.length()==0){
                    Toast.makeText(MainActivity.this,"Please enter your name & try again.. ",Toast.LENGTH_LONG).show();
                }
                else{
                    Intent i=new Intent(MainActivity.this,QuizPage1.class);
                    startActivity(i);
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }
            }
        });
    }
    public static String getName(){
        name= nm.getText().toString();
        return name;
    }
}
