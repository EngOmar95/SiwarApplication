package com.Siwar.siwar_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Patterns;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.Siwar.siwar_application.R;

import java.util.ArrayList;

public class Info_Child extends AppCompatActivity {
    EditText nameChild;
    EditText birthday;
    EditText fatherName;
    EditText MotherName;
    EditText Email;
    EditText phon;
    EditText phon2;
    TextView edit;
    TextView info;
     TextView back        ;
    Button savechang       ;
    Animation animation;
    Links links =new Links();
    ImageView img ;
    final AwesomeValidation awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
    ArrayList<Node>data=new ArrayList<>();
    ArrayList<Node>Search=new ArrayList<>();
    TextView Title ;
    API api =new API();
    Button cancel;
    Create_Barcod CreateQR =new Create_Barcod();
    SharedPreferences sp ;
    DatePickerDialog_Birthday dataPicker=new DatePickerDialog_Birthday();
    TextView datePicker                  ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_child);
            back=findViewById(R.id.back)     ;

        nameChild =findViewById(R.id.nameChild)   ;
        birthday =findViewById(R.id.birthday)   ;
        fatherName =findViewById(R.id.fatherName)   ;
        MotherName =findViewById(R.id.MotherName)   ;
        Email =findViewById(R.id.Email)   ;
        phon =findViewById(R.id.phon)   ;
        phon2 =findViewById(R.id.phon2)   ;
        edit =findViewById(R.id.edit)   ;
        info =findViewById(R.id.info)   ;
        cancel    =findViewById(R.id.cancel)   ;
        Title =findViewById(R.id.Title)   ;
        savechang =findViewById(R.id.savechang)   ;
        datePicker=findViewById(R.id.datePicker)       ;
        img=findViewById(R.id.imgBarcod)       ;


       animation= AnimationUtils.loadAnimation(this,R.anim.anim_start)     ;
        img.setAnimation(animation);


        awesomeValidation.addValidation(this, R.id.nameChild, RegexTemplate.NOT_EMPTY,R.string.Invalid_name);
        awesomeValidation.addValidation(this, R.id.fatherName, RegexTemplate.NOT_EMPTY,R.string.Invalid_name);
        awesomeValidation.addValidation(this, R.id.MotherName, RegexTemplate.NOT_EMPTY,R.string.Invalid_name);
        awesomeValidation.addValidation(this, R.id.phon, "([\\+1\\-\\(]{4})?(\\d{3}\\)?[\\.\\-]?){2}(\\d{4})",R.string.Invalid_Phon);
        awesomeValidation.addValidation(this, R.id.Email, Patterns.EMAIL_ADDRESS ,R.string.Invalid_Emil);
        awesomeValidation.addValidation(this, R.id.phon2, "([\\+1\\-\\(]{4})?(\\d{3}\\)?[\\.\\-]?){2}(\\d{4})",R.string.Invalid_Phon);



        sp = PreferenceManager.getDefaultSharedPreferences(this);
       

        final String id_child =sp.getString(getBaseContext().getString(R.string.id_child_Storage),"0")  ;

        CreateQR.Create_QR(id_child,img);

        Search.add(new Node("ID_child", id_child));

        data.add(new Node("name_child", nameChild));
        data.add(new Node("brithDay", birthday));
        data.add(new Node("fatherName", fatherName));
        data.add(new Node("motherName", MotherName));
        data.add(new Node("E_mail", Email));
        data.add(new Node("phon", phon));
        data.add(new Node("phon2", phon2));

        api.Display(getBaseContext(),links.Display_Database,data,Search,"2")     ;

        nameChild.setEnabled(false);
        birthday.setEnabled(false);
        fatherName.setEnabled(false);
        MotherName.setEnabled(false);
        Email.setEnabled(false);
        phon.setEnabled(false);
        phon2.setEnabled(false);
        savechang.setEnabled(false);
        info.setVisibility(View.GONE);






        datePicker .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataPicker.BirthDay(Info_Child.this,2009,2017, birthday);
            }
        });



        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent=new Intent(Info_Child.this, Main_page.class);

                startActivity(intent);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent  intent=new Intent(Info_Child.this, Main_page.class);

                startActivity(intent);
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nameChild.setEnabled(true);
                info.setVisibility(View.VISIBLE);
                birthday.setEnabled(true);
                fatherName.setEnabled(true);
                MotherName.setEnabled(true);
                Email.setEnabled(true);
                phon.setEnabled(true);
                phon2.setEnabled(true);
                savechang.setEnabled(true);
                edit.setVisibility(View.GONE);
                Title.setText("تعديل المعلومات");

            }
        });


        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                info.setVisibility(View.GONE);
                nameChild.setEnabled(false);
                birthday.setEnabled(false);
                fatherName.setEnabled(false);
                MotherName.setEnabled(false);
                Email.setEnabled(false);
                phon.setEnabled(false);
                phon2.setEnabled(false);
                savechang.setEnabled(false);
                edit.setVisibility(View.VISIBLE);
                Title.setText("عرض المعلومات");
              
            }
        });
        savechang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.clear();



                data.add(new Node("name_child", nameChild.getText().toString()));
                data.add(new Node("brithDay", birthday.getText().toString()));
                data.add(new Node("fatherName", fatherName.getText().toString()));
                data.add(new Node("motherName", MotherName.getText().toString()));
                data.add(new Node("Email", Email.getText().toString()));
                data.add(new Node("phon1", phon.getText().toString()));
                data.add(new Node("phon2", phon2.getText().toString()));
                data.add(new Node("ID_child", id_child));




                      if(awesomeValidation.validate()){
                        //insert
                        api.inserAndupdate(Info_Child.this, links.Registar_Child, data, "2");
                    }

                    }
        });


        }














    }
