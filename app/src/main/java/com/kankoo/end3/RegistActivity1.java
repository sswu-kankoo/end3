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

public class RegistActivity1 extends Activity {

    private EditText etID;
    private EditText etPassword;
    private EditText etPasswordConfirm;

    private EditText etPersonName;
    private EditText etStudentID;
    private Button btnEmailAuth;
    private Button btnCancel;

    private List<String> list; //데이터를 넣은 리스트 변수

    private MultiAutoCompleteTextView textProgrammingLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist1);

        etID = (EditText) findViewById(R.id.etEmail);
        etPassword = (EditText) findViewById(R.id.etPassword);
        etPasswordConfirm = (EditText) findViewById(R.id.etPasswordConfirm);
        etPersonName = (EditText) findViewById(R.id.etPersonName);
        etStudentID = (EditText) findViewById(R.id.etStudentID);

        btnEmailAuth = (Button) findViewById(R.id.btnEmailAuth);
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

        btnEmailAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 아이디 입력 확인
                if (etID.getText().toString().length() == 0) {
                    Toast.makeText(RegistActivity1.this, "아이디를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etID.requestFocus();
                    return;
                }

                // 비밀번호 입력 확인
                if (etPassword.getText().toString().length() == 0) {
                    Toast.makeText(RegistActivity1.this, "비밀번호를 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPassword.requestFocus();
                    return;
                }

                // 비밀번호 확인 입력 확인
                if (etPasswordConfirm.getText().toString().length() == 0) {
                    Toast.makeText(RegistActivity1.this, "비밀번호를 재입력하세요!", Toast.LENGTH_SHORT).show();
                    etPasswordConfirm.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                if (!etPassword.getText().toString().equals(etPasswordConfirm.getText().toString())) {
                    Toast.makeText(RegistActivity1.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    etPassword.setText("");
                    etPasswordConfirm.setText("");
                    etPassword.requestFocus();
                    return;
                }

                /* 전공 입력 확인
                if( etMajor.getText().toString().length() == 0 ) {
                    Toast.makeText(RegistActivity1.this, "전공을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etMajor.requestFocus();
                    return;
                } */

                // 이름 입력 확인
                if (etPersonName.getText().toString().length() == 0) {
                    Toast.makeText(RegistActivity1.this, "이름을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etPersonName.requestFocus();
                    return;
                }

                // 학번 입력 확인
                if (etStudentID.getText().toString().length() == 0) {
                    Toast.makeText(RegistActivity1.this, "학번을 입력하세요!", Toast.LENGTH_SHORT).show();
                    etStudentID.requestFocus();
                    return;
                }

                /*
                Intent result = new Intent();
                result.putExtra("이메일", etID.getText().toString());

                // 자신을 호출한 엑티비티로 데이터를 보낸다.
                setResult(RESULT_OK, result);
                finish();

                */

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //리스트 생성
        list = new ArrayList<String>();

        //리스트에 검색될 데이터 추가
        settingList();

        final AutoCompleteTextView autoCompleteTextView = findViewById(R.id.etMajor);

        //AutoCompleteTextView에 어답터 연결
        autoCompleteTextView.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, list));

        // 디비 저장하고, 메인엑티비티 화면으로 전환
        Button btnEmailAuth = (Button) findViewById(R.id.btnEmailAuth);
        btnEmailAuth.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String user1_id = etID.getText().toString();
                String user1_pw = etPassword.getText().toString();
                String user1_mj = autoCompleteTextView.getText().toString();
                String user1_name = etPersonName.getText().toString();
                String user1_stID = etStudentID.getText().toString();

                if (user1_id.equals("") || user1_pw.equals("") || user1_mj.equals("") || user1_name.equals("") || user1_stID.equals("")) {
                    Toast.makeText(RegistActivity1.this, "빈칸 없이 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }


                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast.makeText(RegistActivity1.this, "회원등록에 성공했습니다.", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(RegistActivity1.this, "회원등록에 실패했습니다.", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                Regist1Request regist1Request = new Regist1Request(user1_id, user1_pw, user1_mj, user1_name, user1_stID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegistActivity1.this);
                queue.add(regist1Request);


                //Intent Intent = new Intent(getApplicationContext(),MainActivity.class);
                //startActivityForResult(Intent, 100);
            }
        });
    }




    //리스트 추가
    private void settingList(){
        list.add("국어국문학과");
        list.add("영어영문학과");
        list.add("독어독문학과");
        list.add("독일어·문화학과");
        list.add("불어불문학과");
        list.add("프랑스어문·문화학과");
        list.add("일어일문학과");
        list.add("일본어문·문화학과");
        list.add("중어중문학과");
        list.add("중국어문·문화학과");
        list.add("사학과");
        list.add("정치외교학과");
        list.add("심리학과");
        list.add("지리학과");
        list.add("경제학과");
        list.add("경영학과");
        list.add("미디어커뮤니케이션학과");
        list.add("융합보안학과");
        list.add("법학과");
        list.add("지식산업법학과");
        list.add("수학과");
        list.add("통계학과");
        list.add("생명과학·화학부");
        list.add("화학과");
        list.add("IT학부");
        list.add("청정융합과학과");
        list.add("서비스·디자인공학과");
        list.add("융합보안공학과");
        list.add("컴퓨터공학과");
        list.add("정보시스템공학과");
        list.add("청정융합에너지공학과");
        list.add("바이오식품공학과");
        list.add("바이오생명공학과");
        list.add("간호학과");
        list.add("글로벌의과학과");
        list.add("식품영양학과");
        list.add("사회복지학과");
        list.add("스포츠레저학과");
        list.add("운동재활복지학과");
        list.add("글로벌비즈니스학과");
        list.add("의류산업학과");
        list.add("뷰티산업학과");
        list.add("소비자생활문화산업학과");
        list.add("교육학과");
        list.add("사회교육과");
        list.add("윤리교육과");
        list.add("한문교육과");
        list.add("유아교육과");
        list.add("동양화과");
        list.add("서양화과");
        list.add("조소과");
        list.add("공예과");
        list.add("산업디자인과");
        list.add("성악과");
        list.add("기악과");
        list.add("작곡과");
        list.add("문화예술경영학과");
        list.add("미디어영상연기학과");
        list.add("현대실용음악학과");
        list.add("무용예술학과");
        list.add("메이크업디자인학과");
        list.add("의류학과");
        list.add("생활문화소비자학과");
    }





/*    private void submitForm() {
        String text="안녕하세요? "+ this.etPersonName.getText().toString();
        Toast.makeText(this, text,Toast.LENGTH_LONG).show();
        } */
}
