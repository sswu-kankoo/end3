package com.kankoo.end3;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;
//import android.support.annotation.Nullable;
import androidx.annotation.NonNull;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RegistActivity2 extends Activity {

    private EditText etID;
    private EditText etPassword;
    private EditText etPasswordConfirm;

    private EditText etShopName;
    private EditText etBRN;
    private Button btnDone;
    private Button btnCancel;

    private String user2_id;
    private String user2_pw;
    private String user2_ShopName;
    private String user2_BRN;

    private List<String> list; //데이터를 넣은 리스트 변수

    private MultiAutoCompleteTextView textProgrammingLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist2);

        etID = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
        etShopName = (EditText) findViewById(R.id.etShopName);
        etBRN = (EditText) findViewById(R.id.etBRN);

        btnDone = (Button) findViewById(R.id.btnDone);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        // 비밀번호 일치 검사
        etPasswordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = etPassword.getText().toString();
                String confirm = etPasswordConfirm.getText().toString();

                if (password.equals(confirm)) {
                    etPassword.setBackgroundColor(Color.GREEN);
                    etPasswordConfirm.setBackgroundColor(Color.GREEN);
                } else {
                    etPassword.setBackgroundColor(Color.RED);
                    etPasswordConfirm.setBackgroundColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }

        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                user2_id = etID.getText().toString();
                user2_pw = etPassword.getText().toString();
                user2_ShopName = etShopName.getText().toString();
                user2_BRN = etBRN.getText().toString();

                // 아이디 입력 확인
                if (user2_id.length() == 0) {
                    Toast.makeText(RegistActivity2.this, "아이디를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etID.requestFocus();
                    return;
                }

                // 비밀번호 입력 확인
                if (user2_pw.length() == 0) {
                    Toast.makeText(RegistActivity2.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }

                // 비밀번호 확인 입력 확인
                if (etPasswordConfirm.getText().toString().length() == 0) {
                    Toast.makeText(RegistActivity2.this, "비밀번호를 재입력하세요!", Toast.LENGTH_SHORT).show();
                    etPasswordConfirm.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                if (!etPassword.getText().toString().equals(etPasswordConfirm.getText().toString())) {
                    Toast.makeText(RegistActivity2.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    etPassword.setText("");
                    etPasswordConfirm.setText("");
                    etPassword.requestFocus();
                    return;
                }

                //가게명 입력 확인
                if (user2_ShopName.length() == 0) {
                    Toast.makeText(RegistActivity2.this, "가게 명을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etShopName.requestFocus();
                    return;
                }

                // 사업자등록번호 입력 확인
                if (user2_BRN.length() == 0) {
                    Toast.makeText(RegistActivity2.this, "사업자등록번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etBRN.requestFocus();
                    return;
                }


                if (user2_id.equals("") || user2_pw.equals("") || user2_ShopName.equals("") || user2_BRN.equals("")) {
                    Toast.makeText(RegistActivity2.this, "빈칸 없이 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(RegistActivity2.this, "회원등록에 성공했습니다.", Toast.LENGTH_SHORT).show();
                                Intent Intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivityForResult(Intent, 100);
                            } else {
                                Toast.makeText(RegistActivity2.this, "회원등록에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                Intent Intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivityForResult(Intent, 100);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Regist2Request regist2Request = new Regist2Request(user2_id, user2_pw, user2_ShopName, user2_BRN, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegistActivity2.this);
                queue.add(regist2Request);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}



