package com.Siwar.siwar_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.Siwar.siwar_application.R;

import java.util.ArrayList;

public class SuggestionAndcomplaints extends AppCompatActivity {

    EditText EmailTo;
    EditText subject;
    EditText Body;
    Button send_Email;
    TextView back;
    SharedPreferences sp ;
    ArrayList<Node>data=new ArrayList<>();
    API api =new API();
    Links links =new Links();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_andcomplaints);

        EmailTo =findViewById(R.id.EmailTo) ;
        subject=findViewById(R.id.subject) ;
        Body=findViewById(R.id.Body) ;
        send_Email =findViewById(R.id.send_Email) ;
        back=findViewById(R.id.back) ;

        sp = PreferenceManager.getDefaultSharedPreferences(this);

        final int id_school =   Integer.parseInt(sp.getString(getBaseContext().getString(R.string.id_School_Storage),"0"))  ;

        ArrayList<Node> Search=new ArrayList<>();
        Search.add(new Node("id_school",String.valueOf(id_school))) ;



        data.add(new Node("E_mail", EmailTo));


        api.Display(getBaseContext(), links.Display_Database, data, Search,"3");



        send_Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send =new Intent(Intent.ACTION_SEND)  ;
                send.setData(Uri.parse("mailto: "))       ;
                send.setType("message/rfc822");    ;

                send.putExtra(android.content.Intent.EXTRA_EMAIL,new String[] { EmailTo.getText().toString() });

                send.putExtra(Intent.EXTRA_SUBJECT,subject.getText().toString())  ;
                send.putExtra(Intent.EXTRA_TEXT,Body.getText().toString())  ;
                startActivity(send);

            }
        });

         back.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent=new Intent(getBaseContext(),Main_page.class)   ;
                 startActivity(intent);
             }
         });

    }

    @Override
    protected void onRestart() {
        super.onRestart();
            Toast.makeText(this, "تم الرسال بنجاح", Toast.LENGTH_SHORT).show();
        finish();
    }
}