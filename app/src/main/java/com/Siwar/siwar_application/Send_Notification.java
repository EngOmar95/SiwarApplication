package com.Siwar.siwar_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.Siwar.siwar_application.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Send_Notification extends AppCompatActivity {
    TextView back;
    Intent intent;
    EditText id_Child;
    EditText name_child;
    EditText time;
    Button send_notif;
    Spinner spinner;
    SharedPreferences sp;
    ArrayList<Node> data;
    Links links=new Links();
    API api =new API();
    Dialog_Desigen dialog =new Dialog_Desigen();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);
        id_Child = findViewById(R.id.id_Child);
        name_child = findViewById(R.id.name_child);
        data=new ArrayList<>();
        time = findViewById(R.id.time);
        send_notif = findViewById(R.id.send_notif);
        spinner = findViewById(R.id.spinner);
        final String[] v = {""};

                       spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                           @Override
                           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                             if(i==0){
                                             v[0] =getString(R.string.child_login);
                                             }
                               if(i==1){
                                   v[0] =getString(R.string.child_Logout);
                               }
                           }

                           @Override
                           public void onNothingSelected(AdapterView<?> adapterView) {

                           }
                       });
        intent = getIntent();

        String  id_child  =  intent.getStringExtra("id")   ;



        FirebaseDatabase database = FirebaseDatabase.getInstance();



        send_notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference myRef = database.getReference("childern").child(id_Child.getText().toString());

                arrival arrival=new arrival(v[0] + time.getText().toString(),"1");

              //  myRef.setValue(v[0] + time.getText().toString());
                myRef.setValue(arrival);
                          Intent intent=new Intent(getBaseContext(),Main_page.class)  ;
                dialog.dialog("تم ارسال الاشعار", Send_Notification.this,intent);
                sp = PreferenceManager.getDefaultSharedPreferences(Send_Notification.this);


                final String admin_num =sp.getString(getBaseContext().getString(R.string.admin_num_Storage),"0")  ;
                data.clear();
                data=new ArrayList<>();
                         if(v[0].equals(getString(R.string.child_login))) {
                             Calendar calendar = Calendar.getInstance();
                             SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d/M/yyyy");
                             String dateTime = simpleDateFormat.format(calendar.getTime());
                             data.add(new Node("ID_Child", id_Child.getText().toString()));
                             data.add(new Node("name_child", name_child.getText().toString()));
                             data.add(new Node("date", dateTime));
                             data.add(new Node("admin_num_id", admin_num));

                             api.inserAndupdate(Send_Notification.this, links.Attendance_Registaration, data, "1");
                         }else {

                             data=new ArrayList<>();
                             data.add(new Node("ID_Child", id_Child.getText().toString()));
                             api.inserAndupdate(Send_Notification.this, links.Attendance_Registaration, data, "9");
                         }

            }
        });

        id_Child.setEnabled(false);
        name_child.setEnabled(false);
        time.setEnabled(false);




        data.add(new Node("ID_child",id_Child))  ;
        data.add(new Node("name_child",name_child))  ;
        

        api.sendNotification(Send_Notification.this,data,id_child,time);






        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(getBaseContext(), Main_page.class);
              
                startActivity(intent);
            }
        });
    }

}