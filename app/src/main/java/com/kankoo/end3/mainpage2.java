package com.kankoo.end3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class mainpage2 extends AppCompatActivity {

    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage2);
    }

    public void ReadQR_onClick(View v){
            Intent Intent = new Intent(getApplicationContext(), QRScanActivity.class);
            startActivityForResult(Intent, 100);
            finish();
    }

    public void ManageQR_onClick(View v) {
            Intent Intent = new Intent(getApplicationContext(), ManageQR.class);
            startActivityForResult(Intent, 100);
            finish();
        }
}

