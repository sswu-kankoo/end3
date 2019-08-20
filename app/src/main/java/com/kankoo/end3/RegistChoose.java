package com.kankoo.end3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegistChoose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist_choose);

        //취소 누르면 메인화면으로 전환
        Button ChsRegist = (Button) findViewById(R.id.btnCancel);
        ChsRegist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivityForResult(Intent, 100);
            }
        });

        //학생 가입화면으로 전환
        Button ChsRegist1 = (Button) findViewById(R.id.btnRegist1);
        ChsRegist1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(), RegistActivity1.class);
                startActivityForResult(Intent, 100);
            }
        });

        //사업자 가입화면으로 전환
        Button ChsRegist2 = (Button) findViewById(R.id.btnRegist2);
        ChsRegist2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(), RegistActivity2.class);
                startActivityForResult(Intent, 100);
            }
        });
    }
}
