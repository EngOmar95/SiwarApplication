package com.Siwar.siwar_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.Siwar.siwar_application.R;

public class StartApp extends AppCompatActivity {
        Animation animation;
        ImageView start_Img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_app);
        start_Img=findViewById(R.id.start_Img)    ;


           animation= AnimationUtils.loadAnimation(this,R.anim.anim_start)     ;


                     start_Img.setAnimation(animation);














       Thread thread =new Thread() {
           ;

           @Override
           public void run() {
               try {
                   sleep(5000);

                   Intent start = new Intent(StartApp.this, MainAPP.class);
                   startActivities(new Intent[]{start});

                   finish();
               } catch (InterruptedException e) {
                 
                   e.printStackTrace();
               }

           }
       };

       thread.start();
    }
}