package com.Siwar.siwar_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.Siwar.siwar_application.R;

import java.util.ArrayList;

public class Registiration_Admin extends AppCompatActivity {
    TextView back;
    Button newRegistar;
    TextView datePicker;
    EditText userName;
    EditText passWord;
    EditText Conf_passWord;
    EditText Fullname;
    EditText Birdth_Day;
    EditText Email;
    EditText number_School;
    EditText number_Mang;
    Links links =new Links();
    final AwesomeValidation awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
    ArrayList<Node> data=new ArrayList<>();
    DatePickerDialog_Birthday dataPicker=new DatePickerDialog_Birthday();
    API api =new API();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registiration__admin);
        number_School =findViewById(R.id.number_School);
        back =findViewById(R.id.back);
         newRegistar =findViewById(R.id.newRegistar);
        datePicker=findViewById(R.id.datePicker);




        userName=findViewById(R.id.userName);
        passWord=findViewById(R.id.passWord);
        Conf_passWord=findViewById(R.id.Conf_passWord);
        Fullname=findViewById(R.id.Fullname);
        Birdth_Day =findViewById(R.id.Birthday);
        Email =findViewById(R.id.Email);
        number_Mang=findViewById(R.id.number_Mang);


        awesomeValidation.addValidation(this, R.id.userName, RegexTemplate.NOT_EMPTY,R.string.Invalid_name);
        awesomeValidation.addValidation(this, R.id.passWord, "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",R.string.Invalid_Password);
        awesomeValidation.addValidation(this, R.id.Conf_passWord, R.id.passWord,R.string.Invalid_ConfPassword);
        awesomeValidation.addValidation(this, R.id.Fullname,".{12,}$" ,R.string.Invalid_Fullname);
        awesomeValidation.addValidation(this, R.id.Email, Patterns.EMAIL_ADDRESS ,R.string.Invalid_Emil);
        awesomeValidation.addValidation(this, R.id.number_Mang,".{4,}$" ,R.string.Invalid_numberMang);

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataPicker.BirthDay(Registiration_Admin.this,1985,2000, Birdth_Day);
            }
        });


         newRegistar.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 data.add(new Node("userName",userName.getText().toString())) ;
                 data.add(new Node("admin_num",number_Mang.getText().toString())) ;
                 data.add(new Node("E_mail", Email.getText().toString())) ;
                 data.add(new Node("brithDay", Birdth_Day.getText().toString())) ;
                 data.add(new Node("fullName",Fullname.getText().toString())) ;
                 data.add(new Node("passWord",passWord.getText().toString())) ;
                 data.add(new Node("id_school",number_School.getText().toString())) ;

        if(awesomeValidation.validate()){

                     api.inserAndupdate(Registiration_Admin.this,links.Registar_Admin,data,"1");

                 }
             }
         });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(getBaseContext(), log_in.class);

                startActivity(intent);
            }
        });
    }
}