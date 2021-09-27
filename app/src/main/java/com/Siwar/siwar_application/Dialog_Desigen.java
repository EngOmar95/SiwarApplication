package com.Siwar.siwar_application;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.ContextCompat;

import com.Siwar.siwar_application.R;

public class Dialog_Desigen extends AppCompatDialogFragment {
        ProgressBar progressBar2;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity())     ;
        LayoutInflater layoutInflater =getActivity().getLayoutInflater();
        View view =layoutInflater.inflate(R.layout.dialog,null,false);
        builder.setView(view)  ;
          progressBar2=view.findViewById(R.id.progressBar2);
          




        return  builder.create() ;



    }

        public  void dialog(String Title, Context context  ,  Intent intent ){


            AlertDialog.Builder dialog =new AlertDialog.Builder(context)  ;
            dialog.setTitle(Title)    ;
            dialog.setPositiveButton("موافق", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    context.startActivity(intent);


                }
            })   ;


            dialog.show();
        }

    public  void dialog(String Title, Context context  ){


        AlertDialog.Builder dialog =new AlertDialog.Builder(context)  ;
        dialog.setTitle(Title)    ;
        dialog.setPositiveButton("موافق", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {




            }
        })   ;


        dialog.show();
    }

}
