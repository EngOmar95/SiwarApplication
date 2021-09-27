package com.Siwar.siwar_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.Siwar.siwar_application.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Main_page extends AppCompatActivity {

    TextView back;
    Intent intent;
    Button edit_info;
    Button register_childern;
    Button info_school;
    Button chats;
    Button scannerQR;
    TextView suggestions;
    TextView logOut;
    TextView not;
    String check = "t";
    ImageView imageView;
    Animation animation;
      Button Attendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);


        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        String id_child = sp.getString(getBaseContext().getString(R.string.id_child_Storage), "0");

        String checkAdminOrparent = sp.getString(getBaseContext().getString(R.string.enterParentOrAdmin), "0");

        not = findViewById(R.id.not);
        Attendance = findViewById(R.id.Attendance);
        back = findViewById(R.id.back);
        suggestions = findViewById(R.id.suggestions);
        logOut = findViewById(R.id.logOut);
        edit_info = findViewById(R.id.edit_info);
        register_childern = findViewById(R.id.register_childern);
        info_school = findViewById(R.id.info_school);
        chats = findViewById(R.id.chats);
        scannerQR = findViewById(R.id.scannerQR);
        imageView = findViewById(R.id.imageView);
        animation = AnimationUtils.loadAnimation(this, R.anim.anim2);
        imageView.setAnimation(animation);
        Attendance.setVisibility(View.VISIBLE)  ;
        not.setVisibility(View.GONE);


        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(Main_page.this);
                
                builder.setTitle(getString(R.string.Confirm_exit));
                builder.setPositiveButton("نعم",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                intent = new Intent(getBaseContext(), log_in.class);
                                finish();
                                check = "f";
                                startActivity(intent);
                            }
                        });

                builder.setNegativeButton("لا",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                builder.show();
            }
        });


        if (checkAdminOrparent.equals("parent")) {
            Attendance.setVisibility(View.GONE);
            scannerQR.setVisibility(View.GONE);
            not.setVisibility(View.VISIBLE);
            edit_info.setText(R.string.Display_Info);
            info_school.setText(R.string.Info_School);

            register_childern.setVisibility(View.GONE);
            chats.setText(R.string.Suggestion);
            chats.setVisibility(View.VISIBLE);
            suggestions.setVisibility(View.GONE);


            FirebaseDatabase database = FirebaseDatabase.getInstance();

            DatabaseReference myRef = database.getReference("childern").child(id_child);



            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    //String Content= snapshot.getValue(String.class);

                    arrival Content = snapshot.getValue(arrival.class);

                    not.setText(Content.time);
                    if (snapshot.getValue() != null && check.equals("t") && !Content.chick_display.equals("2")) {
                        //   not.setText(Content.time);
                        notification(Content.time);
                        arrival arrival = new arrival(Content.time, "2");

                        //  myRef.setValue(v[0] + time.getText().toString());
                        myRef.setValue(arrival);
                        //   myRef.removeValue();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getApplication(), error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }


        chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkAdminOrparent.equals("parent")) {

                    intent = new Intent(getBaseContext(), SuggestionAndcomplaints.class);
                    startActivity(intent);
                }
            }
        });


        Attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    intent = new Intent(getBaseContext(), display_all_child_Attendance.class);
                    startActivity(intent);

            }
        });


        scannerQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getBaseContext(), Read_Barcod.class);

                startActivity(intent);
            }
        });


        info_school.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Intent in=getIntent();


                intent = new Intent(getBaseContext(), Info_School.class);

                startActivity(intent);
            }
        });

        edit_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (checkAdminOrparent.equals("Admin")) {
                    intent = new Intent(getApplicationContext(), Edit_info_Admin.class);

                    startActivity(intent);
                } else {
                    intent = new Intent(getApplicationContext(), Info_Child.class);


                    startActivity(intent);


                }
            }
        });

        register_childern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getBaseContext(), Register_Childern.class);

                startActivity(intent);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(getBaseContext(), log_in.class);
                finish();
                check = "f";
                startActivity(intent);
            }
        });

    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        intent = new Intent(getBaseContext(), log_in.class);
        finish();
        check = "f";
        startActivity(intent);


    }

    public void notification(String Content) {

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        String NOTIFICATION_CHANNEL_ID = "my_channel_id_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
           @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_MAX);


            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);
            notificationManager.createNotificationChannel(notificationChannel);
        }


        NotificationCompat.Builder Builder = new NotificationCompat.Builder(getApplicationContext(), NOTIFICATION_CHANNEL_ID)
                //Notification.Builder notification = new Notification.Builder(Main_page.this)

                .setSmallIcon(R.drawable.logo_siwar)

                .setContentText(Content)
                .setAutoCancel(true)

                .setNumber(1);


        Builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);
        //Builder.setVibrate(new long[]{500, 1000, 500, 1000, 500});


        notificationManager.notify(1, Builder.build());
    }


}