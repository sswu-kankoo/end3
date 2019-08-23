package com.kankoo.end3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.util.Base64;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class QRScanActivity extends AppCompatActivity {
    private IntentIntegrator qrScan;

    String outputString;
    String AES = "AES";
    final String inputPassword = "merongmerong";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrscan);

        qrScan = new IntentIntegrator(this);
        qrScan.setOrientationLocked(false); // default가 세로모드인데 휴대폰 방향에 따라 가로, 세로로 자동 변경됩니다.
        qrScan.setBeepEnabled(false);//바코드 인식시 소리
        qrScan.setPrompt("QR코드를 사각형안에 맞춰주세요");
        qrScan.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult QRresult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(QRresult != null) {
            if(QRresult.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();

            } else {

                try {
                    outputString = decrypt(QRresult.getContents(), inputPassword);
                } catch (Exception e) {
                    Toast.makeText(QRScanActivity.this, "WrongPassword", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }

                Toast.makeText(this, "스캔되었습니다!: " + outputString, Toast.LENGTH_LONG).show();
            }

        }  else {
            super.onActivityResult(requestCode,resultCode,data);
        }


    }
    private String decrypt(String outputString, String password) throws Exception{
        SecretKeySpec key=generateKey(password);
        Cipher c=Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE,key);
        byte[]decodedValue=Base64.decode(outputString, Base64.DEFAULT);
        byte[]decValue=c.doFinal(decodedValue);
        String decryptedValue=new String(decValue);
        return decryptedValue;
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
