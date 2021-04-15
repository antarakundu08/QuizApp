package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;


public class ShowScoreActivity extends AppCompatActivity {

    ImageView imv;
    TextView TxtScores,TxtStatus;
    LottieAnimationView vw;
    MediaPlayer audio;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_score);

        imv=findViewById(R.id.imageView3);
        TxtScores=findViewById(R.id.display);
        TxtStatus=findViewById(R.id.status);
        vw=findViewById(R.id.view);
        this.user=MainActivity.getName();
        Intent intent = getIntent();
        String scores = String.valueOf(intent.getIntExtra("score", 0));

        TxtScores.setText("Your score: "+scores);
        String st=setStatus(scores,user);
        vw.playAnimation();
        if(st==("Great "+user+", Excellent Score!")){
            //vw.setImageResource(R.drawable.excellent);
            TxtStatus.setText(st);
            TxtStatus.setTextColor(Color.rgb(94,168,8));
        }
        else if(st==("Good Work "+user+"!")){
           // vw.setImageResource(R.drawable.good);
            TxtStatus.setText(st);
            TxtStatus.setTextColor(Color.rgb(15,134,228));
        }
        else{
           // vw.setImageResource(R.drawable.bad);
            TxtStatus.setText(st);
            TxtStatus.setTextColor(Color.rgb(248,38,15));
        }

        audio.start();
    }

    private String setStatus(String scores, String user) {
        int score = Integer.parseInt(scores);

        if(score == 5){
            audio = MediaPlayer.create(this, R.raw.high_score);
            String st="Great "+user+", Excellent Score!";
            return st;
        }

        if (score >= 2){
            audio = MediaPlayer.create(this,  R.raw.medium_score);
            String st="Good Work "+user+"!";
            return st;
        }

        audio = MediaPlayer.create(this,  R.raw.low_score);
        String st="Oopss...You need to work hard  "+ user;
        return st;
    }
    public void goToHome(View v){
        Intent home = new Intent(this, MainActivity.class);
        startActivity(home);
        finish();
    }
}
