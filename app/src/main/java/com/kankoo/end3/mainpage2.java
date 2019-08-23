package com.kankoo.end3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class mainpage2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage2);
        Toast.makeText(getApplicationContext(), "QR코드 스캔", Toast.LENGTH_SHORT).show();

        Button qrscan = (Button) findViewById(R.id.btnReadQR);
        qrscan.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QRScanActivity.class);
                startActivity(intent);
            }

        });
    }

     /*
    public void QRscan_onClick(View v){
        intent.putExtra("result", "QR코드 스캔");
        setResult(RESULT_OK, intent);
        finish();
    }
    public void Maechul_onClick(View v){
        intent.putExtra("result", "거래 목록");
        setResult(RESULT_OK, intent);
        finish();
    }
    */

}
