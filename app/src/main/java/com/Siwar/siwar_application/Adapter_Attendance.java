package com.Siwar.siwar_application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.Siwar.siwar_application.R;

import java.util.ArrayList;

public class Adapter_Attendance extends BaseAdapter {


    ArrayList<child_Attendence> Array;
    Context context;

    public Adapter_Attendance(ArrayList<child_Attendence> array, Context context) {
        Array = array;
        this.context = context;
    }
    @Override
    public int getCount() {
        return Array.size();
    }

    @Override
    public child_Attendence getItem(int i) {
        return Array.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        View v =view;


        if(v==null){

            v= LayoutInflater.from(context).inflate(R.layout.display_child_attendance,null,false) ;
        }

        final TextView id_child =v.findViewById(R.id.id_child)    ;
        final TextView name_child =v.findViewById(R.id.name_child)    ;
        final TextView data =v.findViewById(R.id.data)    ;







        id_child.setText(getItem(i).id_child);
        name_child.setText(getItem(i).name_child);
        data.setText(getItem(i).data);









        return v;
    }
}
