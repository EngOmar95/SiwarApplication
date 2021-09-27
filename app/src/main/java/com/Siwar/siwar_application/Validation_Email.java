package com.Siwar.siwar_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.Siwar.siwar_application.R;

import java.util.Random;

public class Validation_Email extends AppCompatActivity {

    EditText Valid;
    Button enter;
    Random random;
    Intent intent;
    TextView  back;
    Dialog_Desigen dialog_desigen=new Dialog_Desigen();
  API api =new API();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaild__email);
        Valid =findViewById(R.id.Valid);
        enter =findViewById(R.id.enter);
        back=findViewById(R.id.back);


        

        random=new Random();
        int randomNumber=1000+random.nextInt(999);

        String Cod=String.valueOf(randomNumber);


        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog_desigen.show(getSupportFragmentManager(),"");
                 api.SendEmailCod(Validation_Email.this,Cod,Valid.getText().toString(),dialog_desigen);

            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent=new Intent(getBaseContext(),log_in.class);
                finish();

                startActivity(intent);
            }
        });

    }
    }
