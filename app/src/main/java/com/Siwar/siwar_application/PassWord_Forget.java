package com.Siwar.siwar_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.Siwar.siwar_application.R;

import java.util.ArrayList;

public class PassWord_Forget extends AppCompatActivity {
              EditText newPassword ;
              Button  save;
              Intent intent;
              EditText ConfPassword;
              TextView back;
              API api =new API();
              Links links=new Links();
              ArrayList<Node> data;
              Dialog_Desigen dialog_desigen =new Dialog_Desigen();
    final AwesomeValidation awesomeValidation=new AwesomeValidation(ValidationStyle.BASIC);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chang_pass);
        newPassword =findViewById(R.id.newPassword)        ;
        ConfPassword =findViewById(R.id.ConfPassword)        ;
        back=findViewById(R.id.back)   ;
        save =findViewById(R.id.save)        ;
        data=new ArrayList<>();
        awesomeValidation.addValidation(this, R.id.newPassword, "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$",R.string.Invalid_Password);
        awesomeValidation.addValidation(this, R.id.ConfPassword, R.id.newPassword,R.string.Invalid_ConfPassword);

         intent=getIntent();

        String Id =intent.getStringExtra("Id");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                         data.add(new Node("ID",Id));
                         data.add(new Node("passWord",newPassword.getText().toString()));
                         dialog_desigen.show(getSupportFragmentManager(),"");
                         if(awesomeValidation.validate()) {
                             api.inserAndupdate(PassWord_Forget.this, links.ForgetPassword, data, "0");

                         }
                dialog_desigen.dismiss();

            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(getBaseContext(),log_in.class);

                startActivity(intent);
            }
        });

    }
}