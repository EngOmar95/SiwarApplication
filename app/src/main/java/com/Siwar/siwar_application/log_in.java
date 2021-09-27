package com.Siwar.siwar_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.Siwar.siwar_application.R;

import java.util.ArrayList;

public class log_in extends AppCompatActivity {

    TextView back;
    TextView Register_New;
    TextView forgetPass;
    Intent intent;
    Button logIn;
    API abi =new API();
    EditText userName;
    EditText passWord;

    Dialog_Desigen dialog_desigen;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
         forgetPass =findViewById(R.id.forgetPass);
         Register_New =findViewById(R.id.register);
         back =findViewById(R.id.back);
        logIn=findViewById(R.id.LogIn);

        userName=findViewById(R.id.userName);
        passWord =findViewById(R.id.passWord)    ;




        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

        String AdminOrNO=sp.getString("AdminOrNO","0");
        
        if(AdminOrNO.equals("Guar_student")){

            Register_New.setVisibility(View.GONE);


        }

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 dialog_desigen =new Dialog_Desigen();

                dialog_desigen.show(getSupportFragmentManager(),"");



                ArrayList<Node>arr=new ArrayList<>();

                arr.add(new Node("userName",userName.getText().toString()))    ;
                arr.add(new Node("passWord", passWord.getText().toString()))  ;


                if(AdminOrNO.equals("Admin")) {
                  //  Toast.makeText(log_in.this, "user"+userName.getText().toString(), Toast.LENGTH_SHORT).show();
                                  abi.login(log_in.this,"userName","passWord",userName, passWord,4,dialog_desigen);

                }
                else {
                  //  Toast.makeText(log_in.this, "password"+passWord.getText().toString(), Toast.LENGTH_SHORT).show();
                    abi.login(log_in.this,"userName","passWord",userName, passWord,5,dialog_desigen);

                }

                }
        });

        Register_New.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               intent=new Intent(log_in.this, Registiration_Admin.class);

                startActivity(intent);
            }

        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              intent=new Intent(log_in.this, MainAPP.class);

                startActivity(intent);
            }
        });
        forgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(log_in.this, Validation_Email.class);

                startActivity(intent);
            }
        });



    }

   



}