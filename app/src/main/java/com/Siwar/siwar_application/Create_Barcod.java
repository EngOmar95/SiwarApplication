package com.Siwar.siwar_application;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.Siwar.siwar_application.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.HashMap;
import java.util.Map;


public class Create_Barcod extends AppCompatActivity {
              Intent intent;
              Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creat_q_r);

        ImageView imQR=findViewById(R.id.imgQR);

        intent=getIntent();

        String  ID =intent.getStringExtra("ID")      ;

        Create_QR(ID,imQR);
        animation= AnimationUtils.loadAnimation(this,R.anim.anim_start)     ;
        imQR.setAnimation(animation);


    }

    public void Create_QR(String Value ,ImageView Desplay){

        MultiFormatWriter writerBarcod =new MultiFormatWriter();

      try {


           BitMatrix bitMatrix =writerBarcod.encode(Value, BarcodeFormat.QR_CODE, 500,500);
          BarcodeEncoder barcodeEncoder =new BarcodeEncoder();
           Bitmap bitmap=barcodeEncoder.createBitmap(bitMatrix);
            Desplay.setImageBitmap(bitmap);



       } catch (WriterException e) {

        e.printStackTrace();
      }
    }



   /*private Bitmap generateQRBitMap(final String content) {

        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();



        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, 500, 500);

            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();

            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
                int e =0;
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {

                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            return bmp;
        } catch (WriterException e) {
            e.printStackTrace();
        }

        return null;
    }   */
}