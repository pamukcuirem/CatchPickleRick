package com.irempamukcu.runnableriick;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    //definitions
    TextView textView;
    TextView textView2;
    ImageView imageView;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    ImageView imageView7;
    ImageView imageView8;
    ImageView[] imageArray;
    Handler handler;
    Runnable runnable;
    int score;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        imageView = findViewById(R.id.imageView);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        imageView4 = findViewById(R.id.imageView4);
        imageView5 = findViewById(R.id.imageView5);
        imageView6 = findViewById(R.id.imageView6);
        imageView7 = findViewById(R.id.imageView7);
        imageView8 = findViewById(R.id.imageView8);
        imageArray = new ImageView[] {imageView,imageView1,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8};


        score = 0;


        new CountDownTimer(10000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("Left: " + millisUntilFinished/1000);
            }

            //it asks do you want to finish the game or continue
            @Override
            public void onFinish() {
               textView.setText("Time's Out");
               handler.removeCallbacks(runnable);

                for(ImageView image : imageArray){
                    image.setVisibility(View.INVISIBLE);
                }

               AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
               alert.setTitle("Again?");
               alert.setMessage("Wanna restart?");

               alert.setPositiveButton("Pickle Rick!", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       Intent intent = getIntent();
                       finish();
                       startActivity(intent);

                   }
               });

               alert.setNegativeButton("Morty?!", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       textView.setText("Game Over");
                       textView2.setText("Loser");
                       for(ImageView image : imageArray){
                           image.setVisibility(View.INVISIBLE);
                       }
                   }
               });
               alert.show();
            }
        }.start();

        hideImages();


    }

    //score is increasing on click(catch)
    public void increaseScore(View view){
        score++;
        textView2.setText("Score: " + score);
    }

    //it shows an image on every half second
    public void hideImages(){
        handler = new Handler();

       runnable = new Runnable() {
           @Override
           public void run() {
               for(ImageView image : imageArray){
                   image.setVisibility(View.INVISIBLE);
               }

               Random random = new Random();
               int r = random.nextInt(9);
              imageArray[r].setVisibility(View.VISIBLE);
              handler.postDelayed(this,500);
           }
       };
       handler.post(runnable);

    }
}