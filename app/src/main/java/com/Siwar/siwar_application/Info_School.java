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

public class Info_School extends AppCompatActivity {

    TextView back;

    EditText nameSchool;
    EditText numberSchool;
    EditText Address;
    EditText EmailSchool;
    API api =new API();
    Links links=new Links();
    Button add_school;
    SharedPreferences sp ;
    final AwesomeValidation awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
   TextView titleAc     ;
    ArrayList<Node>data=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_school);
       back=findViewById(R.id.back);

        nameSchool=findViewById(R.id.nameSchool)      ;
        numberSchool=findViewById(R.id.numberSchool)      ;
        Address =findViewById(R.id.Address)      ;
        EmailSchool =findViewById(R.id.EmailSchool)      ;
        titleAc=findViewById(R.id.titleAc)      ;
        add_school=findViewById(R.id.add_school)      ;

        awesomeValidation.addValidation(this, R.id.nameSchool, RegexTemplate.NOT_EMPTY,R.string.Invalid_name);
        awesomeValidation.addValidation(this, R.id.Address, RegexTemplate.NOT_EMPTY,R.string.Invalid_name);
        awesomeValidation.addValidation(this, R.id.numberSchool, ".{10,}$",R.string.Invalid_Phon);

        awesomeValidation.addValidation(this, R.id.EmailSchool, Patterns.EMAIL_ADDRESS ,R.string.Invalid_Emil);



        add_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                data.add(new Node("school_name",nameSchool.getText().toString()))    ;
                data.add(new Node("tel_number",numberSchool.getText().toString()))    ;
                data.add(new Node("Email", EmailSchool.getText().toString()))    ;
                data.add(new Node("adress", Address.getText().toString()))    ;
                                       if(awesomeValidation.validate()) {
                                           api.inserAndupdate(Info_School.this, links.Add_School, data, "1");
                                       }
            }
        });
        sp = PreferenceManager.getDefaultSharedPreferences(this);
        final String checkAdminOrparent =   sp.getString(getBaseContext().getString(R.string.enterParentOrAdmin),"0")  ;
        final int id_school =   Integer.parseInt(sp.getString(getBaseContext().getString(R.string.id_School_Storage),"0"))  ;





        if(checkAdminOrparent.equals("parent")) {
            titleAc.setText("معلومات المدرسه");

            data.clear();


            nameSchool.setEnabled(false); ;
            numberSchool.setEnabled(false); ;
            Address.setEnabled(false); ;
            EmailSchool.setEnabled(false); ;
           add_school.setVisibility(View.GONE);
               ArrayList<Node>Search=new ArrayList<>();
               Search.add(new Node("id_school",String.valueOf(id_school))) ;


            data.add(new Node("school_name", nameSchool));
            data.add(new Node("tel_number", numberSchool));
            data.add(new Node("E_mail", EmailSchool));
            data.add(new Node("adress", Address));

            api.Display(Info_School.this, links.Display_Database, data, Search,"3");


        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(Info_School.this, Main_page.class);
             

                startActivity(intent);
            }
        });
    }
}