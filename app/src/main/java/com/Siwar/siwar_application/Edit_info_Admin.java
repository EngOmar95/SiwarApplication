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

public class Edit_info_Admin extends AppCompatActivity {

    TextView back;
    Intent intent;
    Button cancel  ;
    EditText userName;
    EditText passWord;
    EditText Conf_passWord;
    EditText Fullname;
    EditText Birthday;
    EditText Email;
    EditText number_Mang;
    TextView datePicker      ;
    Button saveChang;
    SharedPreferences sp ;
    Links links=new Links();
    final AwesomeValidation awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
    DatePickerDialog_Birthday birthDay =new DatePickerDialog_Birthday();

    ArrayList<Node> data = new ArrayList<>();
    API api = new API();
    ArrayList<Node> Search = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info__admin);
        saveChang = findViewById(R.id.saveChang);
        userName = findViewById(R.id.userName);
        passWord = findViewById(R.id.passWord);
        Conf_passWord = findViewById(R.id.Conf_passWord);
        Fullname = findViewById(R.id.Fullname);
        Birthday = findViewById(R.id.Birthday);
        Email = findViewById(R.id.Email);
        number_Mang = findViewById(R.id.number_Mang);
        back = findViewById(R.id.back);
        datePicker=findViewById(R.id.datePicker)       ;
        cancel  =findViewById(R.id.cancel)     ;


        //////////////////////////////////////////

        awesomeValidation.addValidation(this, R.id.userName, RegexTemplate.NOT_EMPTY,R.string.Invalid_name);
        awesomeValidation.addValidation(this, R.id.passWord, "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",R.string.Invalid_Password);
        awesomeValidation.addValidation(this, R.id.Conf_passWord, R.id.passWord,R.string.Invalid_ConfPassword);
        awesomeValidation.addValidation(this, R.id.Fullname,".{12,}$" ,R.string.Invalid_Fullname);
        awesomeValidation.addValidation(this, R.id.Email,Patterns.EMAIL_ADDRESS ,R.string.Invalid_Emil);
        awesomeValidation.addValidation(this, R.id.number_Mang,".{4,}$" ,R.string.Invalid_numberMang);




       sp = PreferenceManager.getDefaultSharedPreferences(this);


        final String admin_num =sp.getString(getBaseContext().getString(R.string.admin_num_Storage),"0")  ;



        Search.add(new Node("admin_num", admin_num));

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(getBaseContext(), Main_page.class);

                startActivity(intent);
            }
        });




        data.add(new Node("userName", userName));
        data.add(new Node("admin_num", number_Mang));
        data.add(new Node("E_mail", Email));
        data.add(new Node("brithDay", Birthday));
        data.add(new Node("fullName", Fullname));
        data.add(new Node("passWord", passWord));
        data.add(new Node("passWord", Conf_passWord));


    api.Display(Edit_info_Admin.this,links.Display_Database,data,Search,"1");




        
        saveChang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<Node> data = new ArrayList<>();


                data.add(new Node("userName", userName.getText().toString()));
                data.add(new Node("admin_num", number_Mang.getText().toString()));
                data.add(new Node("E_mail", Email.getText().toString()));
                data.add(new Node("brithDay", Birthday.getText().toString()));
                data.add(new Node("fullName", Fullname.getText().toString()));
                data.add(new Node("passWord", passWord.getText().toString()));


                /////fun Update
                if (awesomeValidation.validate()) {
                    api.inserAndupdate(Edit_info_Admin.this, links.Registar_Admin, data, "2");

                }

            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(Edit_info_Admin.this, Main_page.class);

                startActivity(intent);
            }
        });

        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                      birthDay.BirthDay(Edit_info_Admin.this,1985,1999, Birthday);

             
            }
            
        });
    }
}