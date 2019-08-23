package com.kankoo.end3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class MakeQR extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_qr);

        final Intent Intent = getIntent();
        String[] after = Intent.getStringArrayExtra("result");
        //final String result = Intent.getStringExtra("result");
        final String result_original = Intent.getStringExtra("result_original");
        String num = Intent.getStringExtra("num");
        int num2 = Integer.parseInt(num);

        imageView = findViewById(R.id.imageView);
        Button saveimage = findViewById(R.id.saveimage);


        //String text = etInput.getText().toString().trim();
        //String text = result;

        TextView textView1 = (TextView) findViewById(R.id.text1);
        String nxtline = "\n";

        for (int i = 1; i < num2 + 1; i++) {
            textView1.setText(Arrays.toString(after));
            System.out.println(nxtline);



        }

        for (int i = 1; i < num2 + 1; i++) {
            String text = after[i - 1];
            MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

            try {
                BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE, 500, 500);
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                imageView.setImageBitmap(bitmap);
            } catch (WriterException e) {
                e.printStackTrace();
            }
        }

        //Save button
        saveimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //saveImage();

                Bitmap bitmap2 =getBitmapFromView(imageView);
                try {
                    File file = new File(getApplication().getExternalCacheDir(),result_original+".png");
                    FileOutputStream fOut = new FileOutputStream(file);
                    bitmap2.compress(Bitmap.CompressFormat.PNG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                    file.setReadable(true, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Bitmap getBitmapFromView(View view) {

        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(),Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(returnedBitmap);
        Drawable bgDrawable =view.getBackground();
        if (bgDrawable!=null) {
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        }   else{
            //does not have background drawable, then draw white background on the canvas
            canvas.drawColor(Color.WHITE);
        }
        view.draw(canvas);
        return returnedBitmap;
    }
}

