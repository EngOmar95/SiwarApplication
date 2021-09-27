package com.Siwar.siwar_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.Siwar.siwar_application.R;

public class display_all_child_Attendance extends AppCompatActivity {
           ListView listVeiw;


   TextView back;
    SharedPreferences sp;
    API api=new API();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_child_attendance);
        listVeiw=findViewById(R.id.listVeiw)        ;
        back=findViewById(R.id.back)   ;


        sp = PreferenceManager.getDefaultSharedPreferences(display_all_child_Attendance.this);


        final String admin_num =sp.getString(getBaseContext().getString(R.string.admin_num_Storage),"0")  ;

        api.Atteding_Students(display_all_child_Attendance.this,listVeiw,admin_num);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }


}