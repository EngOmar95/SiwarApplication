package com.Siwar.siwar_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

public class Register_Childern extends AppCompatActivity {
    TextView back;
    Intent intent;
    EditText id_child;
    EditText username;
    EditText password_child;
    EditText nameChild;
    EditText Birthday;
    EditText fathername;
    EditText mothername;
    EditText phon;
    EditText phon2;
    EditText Email;
    SharedPreferences sp ;
    TextView datePicker;
    Button cancel;
    final AwesomeValidation awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
    Button registar_child;
    DatePickerDialog_Birthday dataPicker=new DatePickerDialog_Birthday();
    Links links =new Links();
    API api =new API();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_childern_admin);
        registar_child=findViewById(R.id.registar_child)  ;
        id_child=findViewById(R.id.id_child)   ;
        username =findViewById(R.id.username)   ;
        password_child=findViewById(R.id.password_child)   ;
        nameChild=findViewById(R.id.nameChild)   ;
        Birthday =findViewById(R.id.birthday)   ;
        fathername=findViewById(R.id.fathername)   ;
        mothername=findViewById(R.id.mothername)   ;
        phon=findViewById(R.id.phon)   ;
        phon2=findViewById(R.id.phon2)   ;
        Email =findViewById(R.id.Email)   ;
        cancel=findViewById(R.id.cancel)   ;
        back=findViewById(R.id.back);
        datePicker=findViewById(R.id.datePicker)      ;
        sp = PreferenceManager.getDefaultSharedPreferences(this);

        final int id_school =   Integer.parseInt(sp.getString(getBaseContext().getString(R.string.id_School_Storage),"0"))  ;



        awesomeValidation.addValidation(this, R.id.username, RegexTemplate.NOT_EMPTY,R.string.Invalid_name);
        awesomeValidation.addValidation(this, R.id.fathername, RegexTemplate.NOT_EMPTY,R.string.Invalid_name);
        awesomeValidation.addValidation(this, R.id.mothername, RegexTemplate.NOT_EMPTY,R.string.Invalid_name);
        awesomeValidation.addValidation(this, R.id.phon2, "([\\+1\\-\\(]{4})?(\\d{3}\\)?[\\.\\-]?){2}(\\d{4})",R.string.Invalid_Phon);
        awesomeValidation.addValidation(this, R.id.phon, "([\\+1\\-\\(]{4})?(\\d{3}\\)?[\\.\\-]?){2}(\\d{4})",R.string.Invalid_Phon);
        awesomeValidation.addValidation(this, R.id.password_child, "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",R.string.Invalid_Password);

        awesomeValidation.addValidation(this, R.id.nameChild,".{12,}$" ,R.string.Invalid_Fullname);
        awesomeValidation.addValidation(this, R.id.Email, Patterns.EMAIL_ADDRESS ,R.string.Invalid_Emil);
        awesomeValidation.addValidation(this, R.id.id_child,".{10,}$" ,R.string.Invalid_numberMang);


        datePicker.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        dataPicker.BirthDay(Register_Childern.this,2009,2017, Birthday);
    }
});

     //////////////////////////////////////registar_child////////////////////////////////////////////
         registar_child.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {



                 final ArrayList<Node> data=new ArrayList<>();
                 data.add(new Node("ID_child",id_child.getText().toString())) ;
                 data.add(new Node("userName", username.getText().toString())) ;
                 data.add(new Node("password",password_child.getText().toString())) ;
                 data.add(new Node("name_child",nameChild.getText().toString())) ;
                 data.add(new Node("brithDay", Birthday.getText().toString())) ;
                 data.add(new Node("fatherName",fathername.getText().toString())) ;
                 data.add(new Node("motherName",mothername.getText().toString())) ;
                 data.add(new Node("phon1",phon.getText().toString())) ;
                 data.add(new Node("phon2",phon2.getText().toString())) ;
                 data.add(new Node("Email", Email.getText().toString())) ;
                 data.add(new Node("id_school",String.valueOf(id_school))) ;

               if(awesomeValidation.validate()){
                         //insert
                         api.inserAndupdate(Register_Childern.this,links.Registar_Child,data,"1");

                       //  intent=new Intent(getBaseContext(), Create_Barcod.class)      ;
                         //intent.putExtra("ID",id_child.getText().toString())    ;
                         //startActivity(intent);

                 }
             }
         });
                  ////////////////////////////////////////////////////////////////////////////////////////
                   cancel.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View view) {
                           Intent  intent=new Intent(getBaseContext(), Main_page.class);

                           startActivity(intent);
                       }
                   });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(getBaseContext(), Main_page.class);

                startActivity(intent);
            }
        });
    }
}