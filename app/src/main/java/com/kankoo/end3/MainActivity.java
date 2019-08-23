package com.kankoo.end3;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import static androidx.core.os.LocaleListCompat.create;

public class MainActivity extends AppCompatActivity {

    private AlertDialog dialog;
    private Button btnLogin;
    private Button btnRegist;
    private EditText etEmail;
    private EditText etPassword;
    private String user1_id;
    private String user1_pw;


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

        //로그인 검증화면으로 전환
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                user1_id = etEmail.getText().toString();
                user1_pw = etPassword.getText().toString();
                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if(success){
                                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                                user1_id = jsonResponse.getString("user1_id");
                                user1_pw = jsonResponse.getString("user1_pw");
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
                LoginRequest LoginRequest = new LoginRequest(user1_id, user1_pw, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
                queue.add(LoginRequest);


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

    }
    /*@Override
    protected void onStop(){
        super.onStop();
        if(dialog!=null){
            dialog.dismiss();
            dialog=null;
        }
    } */
}
