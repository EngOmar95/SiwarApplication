package com.Siwar.siwar_application;

import android.content.Context;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.Siwar.siwar_application.R;

public class DatePickerDialog_Birthday {
     public  void BirthDay(Context context , int FromYear , int ToYear, EditText Birthday){
    android.app.DatePickerDialog Data =new android.app.DatePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog_MinWidth, new android.app.DatePickerDialog.OnDateSetListener() {

               
        @Override
        public void onDateSet(DatePicker datePicker, int Year, int Month, int Day) {


            String date =String.valueOf(Day)+"/"+String.valueOf(Month+1)+"/"+String.valueOf(Year);
            if(Year>ToYear || Year<FromYear){

                Toast.makeText(context, context.getString(R.string.error_date_Birthday), Toast.LENGTH_SHORT).show();

            }       else {
                Birthday.setText(date);

            }

        }
    },1985,10,4)     ;

                Data.show();      }
}
