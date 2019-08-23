package com.kankoo.end3;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.kankoo.end3.LoginRequest;
import com.kankoo.end3.LoginRequest2;
import com.kankoo.end3.R;
import com.kankoo.end3.RegistChoose;
import com.kankoo.end3.mainpage1;
import com.kankoo.end3.mainpage2;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private AlertDialog dialog;
    private AlertDialog dialog2;
    private Button btnLogin;
    private Button btnRegist;
    private EditText etEmail;
    private EditText etPassword;
    private String user_id;
    private String user_pw;
    private Button btnLogin2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .permitDiskReads()
                .permitDiskWrites()
                .permitNetwork().build());

        btnRegist = (Button) findViewById(R.id.btnRegist);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin2 = (Button) findViewById(R.id.btnLogin2);

        //학생 로그인 검증화면으로 전환
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                user_id = etEmail.getText().toString();
                user_pw = etPassword.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                                user_id = jsonResponse.getString("user1_id");
                                user_pw = jsonResponse.getString("user1_pw");
                                Intent intent = new Intent(MainActivity.this, mainpage1.class);
                                MainActivity.this.startActivity(intent);
                                finish();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                dialog = builder.setMessage("계정을 다시 확인하세요.")
                                        .setPositiveButton("다시 확인", null)
                                        .create();
                                dialog.show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest LoginRequest = new LoginRequest(user_id, user_pw, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(LoginRequest);
                return;


            }
        });

        //회원가입 선택화면으로 전환
        btnRegist.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent Intent = new Intent(getApplicationContext(), RegistChoose.class);
                startActivityForResult(Intent, 100);
            }
        });

        //사업자 로그인 검증화면으로 전환
        btnLogin2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                user_id = etEmail.getText().toString();
                user_pw = etPassword.getText().toString();
                Response.Listener<String> responseListener2 = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                            if(success){
                                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                                user_id = jsonResponse.getString("user2_id");
                                user_pw = jsonResponse.getString("user2_pw");
                                Intent intent2 = new Intent(MainActivity.this, mainpage2.class);
                                MainActivity.this.startActivity(intent2);
                                finish();
                            } else {
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(MainActivity.this);
                                dialog2 = builder2.setMessage("계정을 다시 확인하세요.")
                                        .setPositiveButton("다시 확인", null)
                                        .create();
                                dialog2.show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                LoginRequest2 LoginRequest2 = new LoginRequest2(user_id, user_pw, responseListener2);
                RequestQueue queue2 = Volley.newRequestQueue(MainActivity.this);
                queue2.add(LoginRequest2);


            }

        });
    }
}