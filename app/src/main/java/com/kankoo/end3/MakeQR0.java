package com.kankoo.end3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MakeQR0 extends AppCompatActivity {

    EditText year, semester, major, quantity;
    Button btnGenQR;

    String outputString;
    String AES = "AES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_qr0);

        year = findViewById(R.id.year);
        semester = findViewById(R.id.semester);
        major = findViewById(R.id.major);
        quantity = findViewById(R.id.quantity);

        btnGenQR = findViewById(R.id.btnGenQR);

        //암호화
        final String inputPassword = "merongmerong";

        btnGenQR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 입력된 값이 하나라도 없다면
                if (year.getText().toString().equals("") ||
                        semester.getText().toString().equals("") ||
                        major.getText().toString().equals("") ||
                        quantity.getText().toString().equals("")) {
                    Toast.makeText(MakeQR0.this,
                            "입력된 값이 없습니다.",
                            Toast.LENGTH_LONG).show();
                } else { // 공백이 아닐 때
                    String s1 = year.getText().toString(); // year 값 가져오기 (xml -> java)
                    String s2 = semester.getText().toString();
                    String s3 = major.getText().toString();
                    String s4 = quantity.getText().toString();

                    int num = Integer.parseInt(s4);

                    String result = s1 + s2 + s3;
                    String result_original = s1 + s2 + s3 + s4; //나중에 바꿔야해 s4값

                    // 배열 선언, 개수만큼 생성 (암호화 전, 암호화 후)
                    int [] array1 = new int[num];
                    String [] array2 = new String[num];

                    //암호화
                    try {
                        // 반복문 이용
                        for (int i = 1; i < num+1; i++) {
                            array1[i-1] = i;
                            String before = s1 + s2 + s3 + array1[i-1];

                            String after = encrypt(Editable.Factory.getInstance().newEditable(before), inputPassword);
                            array2[i-1] = after;
                        }

                        //result = encrypt(Editable.Factory.getInstance().newEditable(result), inputPassword);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    Intent Intent = new Intent(getApplicationContext(), MakeQR.class);

                    Intent.putExtra("num", s4);
                    Intent.putExtra("result", array2);
                    //Intent.putExtra("result", result);
                    Intent.putExtra("result_original",result_original);
                    startActivityForResult(Intent, 100);
                    finish();
                }
            }
        });
    }

    private String encrypt(Editable Data, String password) throws Exception {
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.toString().getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptedValue;
    }

    private SecretKeySpec generateKey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes(StandardCharsets.UTF_8);
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }
}
