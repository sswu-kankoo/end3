package com.kankoo.end3;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class mainpage1 extends AppCompatActivity {

    Intent intent = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage1);
    }

    public void ViewQR_onClick(View v) {
        intent.putExtra("result", "내 간쿠 보기-> 서버 연결?");
        setResult(RESULT_OK, intent);
        finish();
    }

    public void ExchangeQR_onClick(View v) {
        intent.putExtra("result", "간쿠 교환?");
        setResult(RESULT_OK, intent);
        finish();
    }

    public void MakeQR_onClick(View v) {
        Intent Intent = new Intent(getApplicationContext(), MakeQR0.class);
        startActivityForResult(Intent, 100);
        finish();
    }
}
