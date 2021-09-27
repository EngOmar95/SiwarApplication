package com.Siwar.siwar_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.Siwar.siwar_application.R;

public class codValidation extends AppCompatActivity {
           EditText CodVaild;
           Button enter;
           Intent intent;
           TextView back;

           Dialog_Desigen dialog_desigen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cod_validation);
        dialog_desigen=new Dialog_Desigen();
        CodVaild =findViewById(R.id.cod);
        enter=findViewById(R.id.enter);
        back=findViewById(R.id.back);

         intent=getIntent();
        String cod =intent.getStringExtra("cod");
        String Id =intent.getStringExtra("Id");

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog_desigen.show(getSupportFragmentManager(),"");
                if(CodVaild.getText().toString().equals(cod)){

                 Intent   intent =new Intent(codValidation.this,PassWord_Forget.class)   ;
                 intent.putExtra("Id",Id)  ;
                    dialog_desigen.dismiss();
                 dialog_desigen.dialog("تم التحقق ",codValidation.this,intent);




                }else {
                    dialog_desigen.dismiss();
                    dialog_desigen.dialog("رقم التحقق غير صحيح ",codValidation.this);
                }

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