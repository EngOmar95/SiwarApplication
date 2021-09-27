package com.Siwar.siwar_application;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.Siwar.siwar_application.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class API {

    Dialog_Desigen dialog =new Dialog_Desigen();
    Intent intent ;
    Links links =new Links();
    JSONObject jsonObject;
    JSONArray jsonArray;
    StringRequest stringRequest;
    SharedPreferences sp;


   
    public void inserAndupdate( Context context , String url,  ArrayList<Node>date , String flag){

                       intent =new Intent(context,log_in.class) ;
         stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {

                        jsonObject = new JSONObject(response);
                        if(jsonObject.getString( context.getString(R.string.Result_API)).equals(context.getString(R.string.ExstIdChild))){

                            dialog. dialog(context.getString(R.string.message_exist_child),context);



                        }
                    else if(jsonObject.getString( context.getString(R.string.Result_API)).equals(context.getString(R.string.ExstNumberMang))){
                        dialog. dialog(context.getString(R.string.message_exist_admin),context);


                    }
                    else if(jsonObject.getString( context.getString(R.string.Result_API)).equals(context.getString(R.string.noShcool))){

                        dialog. dialog(context.getString(R.string.Message_Number_shcool_error),context);

                    }
                    if (url.equals(links.Registar_Child)&& jsonObject.getString( context.getString(R.string.Result_API)).equals("false")){
                        dialog.dialog("رقم الهويه غير صحيح",context);

                    }
                    if(jsonObject.getString( context.getString(R.string.Result_API)).equals("true")){

                        if(flag.equals("0"))     {
                       intent =new Intent(context,log_in.class)  ;
                            dialog.dialog("تم تغير رقم السري بنجاح",context ,intent);
                        }
                      else   if(flag.equals("2"))     {

                            intent =new Intent(context,Main_page.class)  ;

                            dialog.dialog("تم التعديل بنجاح",context ,intent);

                        } else if (url.equals(links.Registar_Child) || url.equals(links.Add_School)){
                            intent =new Intent(context,Main_page.class)  ;
                            if (url.equals(links.Registar_Child)){
                            intent=new Intent(context, Create_Barcod.class)      ;
                            intent.putExtra("ID",date.get(0).Value)    ;
                              context.startActivity(intent);
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                DatabaseReference myRef = database.getReference("childern").child(date.get(0).Value);

                                arrival arrival=new arrival("لايوجد اشعارات","2");


                                myRef.setValue(arrival);}
                            dialog. dialog("تم التسجيل بنجاح",context,intent);

                        } else if(!url.equals(links.Attendance_Registaration) )
                            dialog. dialog("تم التسجيل بنجاح",context,intent);
                      }  
                } catch (JSONException e) {

                    dialog.dialog("حدث خطإ !!!",context);
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dialog("حدث خطأ اثناء الاتصال في الانترنت ",context);
            }
        }) {



            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                for(int i =0;i<date.size();i++) {
  params.put(date.get(i).nameColumnDatabase, date.get(i).Value);
                }


                params.put("Flag", flag);
                ;
                return params;
            }

        };
        Volley.newRequestQueue(context).add(stringRequest);




    }


    public void Display(Context context,String url,ArrayList<Node>data,ArrayList<Node>Serch,String flag) {


         stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {

                     jsonObject = new JSONObject(response);
                     jsonArray = jsonObject.getJSONArray( context.getString(R.string.Result_API));


                    for (int i = 0; i < jsonArray.length(); i++) {
                         jsonObject = jsonArray.getJSONObject(i);

                        for (int j = 0; j < data.size(); j++) {

                            data.get(j).editText.setText(jsonObject.getString(data.get(j).nameColumnDatabase));
                        }

                    }


                } catch (JSONException e) {


                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dialog("حدث خطأ اثناء الاتصال في الانترنت ",context);
            }
        }) {


            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                for (int j = 0; j < Serch.size(); j++) {
                    params.put(Serch.get(j).nameColumnDatabase, Serch.get(j).Value);

                }
                if(flag.equals("1")){
                    params.put("flag", "1");

                }   if(flag.equals("2")) {

                    params.put("flag", "2");

                }               if(flag.equals("3")) {

                    params.put("flag", "3");
                }
                //    params.put("userName", user);


                return params;
            }

        };
        Volley.newRequestQueue(context).add(stringRequest);

}


    public void login (Context context , String phpUsername, String phPpassword,EditText username ,EditText password,int check ,Dialog_Desigen dialog_desigen) {





       intent =new Intent(context,context.getClass()) ;

     stringRequest = new StringRequest(Request.Method.POST, links.Display_Database, new Response.Listener<String>() {

        @Override
        public void onResponse(String response) {

            try {

                 jsonObject = new JSONObject(response);
                 jsonArray = jsonObject.getJSONArray( context.getString(R.string.Result_API));


                 sp = PreferenceManager.getDefaultSharedPreferences(context);
                SharedPreferences.Editor edit=sp.edit();


                if (jsonArray.length() > 0) {
                     API.this.jsonObject =jsonArray.getJSONObject(0)     ;
                    Intent intent;
                    intent = new Intent(context, Main_page.class);


                    if (check == 4) {    ///////Admin
                        edit.putString(context.getString(R.string.admin_num_Storage), API.this.jsonObject.getString("admin_num"));
                        edit.putString(context.getString(R.string.enterParentOrAdmin),"Admin");

                        edit.putString(context.getString(R.string.id_School_Storage), String.valueOf(API.this.jsonObject.getInt("id_school")));
                        edit.apply();
                        context.startActivity(intent);
                    }  if (check == 5) {  ////Parent

                        edit.putString(context.getString(R.string.enterParentOrAdmin),"parent");

                        edit.putString(context.getString(R.string.id_child_Storage), String.valueOf(API.this.jsonObject.getString("ID_child")));
                        edit.putString(context.getString(R.string.id_School_Storage), String.valueOf(API.this.jsonObject.getInt("id_school")));
                        edit.apply();
                        context.startActivity(intent);
                    }


                } else {



                   dialog_desigen.dialog("اسم المستخدم او الرقم السري غير صحيح",context);
                    dialog_desigen.dismiss();


                }
            } catch (JSONException e) {
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            dialog_desigen.dialog("تأكد من الاتصال بلأنترنت",context);
            dialog_desigen.dismiss();

        }
    }) {


        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<String, String>();

            params.put(phpUsername, username.getText().toString());
            params.put(phPpassword, password.getText().toString());
            if (check == 4) {

                params.put("flag", "4");
            }  else {
                params.put("flag", "5");
            }
            return params;
        }

    };
    Volley.newRequestQueue(context).add(stringRequest);

}


   public  void SendEmailCod (Context context ,String Cod ,String ID ,Dialog_Desigen dialog_desigen){



        stringRequest = new StringRequest(Request.Method.POST, links.SendEmailCod, new Response.Listener<String>() {

           @Override
           public void onResponse(String response) {

               try {

                    jsonObject = new JSONObject(response);


                   if(jsonObject.getString( context.getString(R.string.Result_API)).equals("true")){


                       Intent intent =new Intent(context,codValidation.class)   ;
                       intent.putExtra("cod",Cod)       ;
                       intent.putExtra("Id",ID)       ;

                       dialog.dialog("تم ارسال الرمز الى الايميل ",context,intent);
                       dialog_desigen.dismiss();


                   }
                   if(jsonObject.getString("Result").equals("false")){
                       dialog.dialog("رقم الاداري او رقم الهويه غير صحيح ",context);
                       dialog_desigen.dismiss();
                   }
               } catch (JSONException e) {


                   e.printStackTrace();
               }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {

               dialog_desigen.dialog("حدث خطأ اثناء الاتصال في الانترنت ",context);
               dialog_desigen.dismiss();


           }
       }) {



           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String, String> params = new HashMap<String, String>();

               params.put("Check",ID);
               params.put("ConfNumber", Cod);




               return params;
           }

       };
       Volley.newRequestQueue(context).add(stringRequest);


   }

   public  void  sendNotification(Context context ,ArrayList<Node> data,String id_child ,EditText time){


       Calendar calendar= Calendar.getInstance();
       SimpleDateFormat simpleDateFormat =new SimpleDateFormat("hh:mm:ss")   ;
       String dateTime=simpleDateFormat.format(calendar.getTime());


       StringRequest sr = new StringRequest(Request.Method.POST, links.Search_Child, new Response.Listener<String>() {

           @Override
           public void onResponse(String response) {

               try {

                    jsonObject = new JSONObject(response);
                    jsonArray = jsonObject.getJSONArray( context.getString(R.string.Result_API));



                    jsonObject = jsonArray.getJSONObject(0);


                       for(int i =0;i<data.size();i++){

                           data.get(i).editText.setText(jsonObject.getString(data.get(i).nameColumnDatabase));

                       }
                       time.setText(dateTime);




               } catch (JSONException e) {
                   //     Toast.makeText(Send_Notification.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                   e.printStackTrace();

               }

           }
       }, new Response.ErrorListener() {
           @Override
           public void onErrorResponse(VolleyError error) {
               Toast.makeText(context, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
           }
       }) {


           @Override
           protected Map<String, String> getParams() throws AuthFailureError {
               Map<String, String> params = new HashMap<String, String>();

               params.put("ID_child", id_child);


               return params;
           }

       };
       Volley.newRequestQueue(context).add(sr);

   }

    public  void  Atteding_Students(Context context , ListView listView, String admin_num)        {
    stringRequest = new StringRequest(Request.Method.POST, links.Attendance_display, new Response.Listener<String>() {
          ArrayList <child_Attendence>list  =new ArrayList<>();
        @Override
        public void onResponse(String response) {

            try {

                jsonObject = new JSONObject(response);
                jsonArray = jsonObject.getJSONArray(context.getString(R.string.Result_API));


                for (int i = 0; i < jsonArray.length(); i++) {
                    jsonObject = jsonArray.getJSONObject(i);



                    list.add(new child_Attendence(jsonObject.getString("ID_Child"),jsonObject.getString("name_child"),jsonObject.getString("date")));
                    Adapter_Attendance adp =new Adapter_Attendance(list,context);

                    listView.setAdapter(adp);
                }




            } catch (JSONException e) {


                e.printStackTrace();
            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            dialog.dialog("حدث خطأ اثناء الاتصال في الانترنت ",context);
        }
    }) {


        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params = new HashMap<String, String>();


            params.put("admin_num_id", admin_num);




            return params;
        }

    };
        Volley.newRequestQueue(context).add(stringRequest);


}     }


