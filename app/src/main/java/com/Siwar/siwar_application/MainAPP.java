package com.Siwar.siwar_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

import com.Siwar.siwar_application.R;

public class MainAPP extends AppCompatActivity {
    Button Guar_student;
    Button Admin;
    Intent in;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);

        Guar_student=findViewById(R.id.Guar_student);
        Admin=findViewById(R.id.Admin);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editt=sp.edit();



        Guar_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                in=new Intent(getBaseContext(),log_in.class);
                editt.putString("AdminOrNO","Guar_student");
                editt.apply();
                startActivity(in);
            }
        });


        Admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                in=new Intent(getBaseContext(),log_in.class);

                editt.putString("AdminOrNO","Admin");
                editt.apply();
                startActivity(in);
            }
        });



    }
}