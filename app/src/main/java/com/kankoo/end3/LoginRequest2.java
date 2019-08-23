package com.kankoo.end3;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest2 extends StringRequest {

    final static private String URL = "http://10.0.2.2:80/UserLogin2.php";
    final private Map<String,String> parameters2;

    public LoginRequest2(String user2_id, String user2_pw, Response.Listener<String> listener ) {
        super(Method.POST, URL, listener, null);
        parameters2 = new HashMap<>();
        parameters2.put("user2_id", user2_id);
        parameters2.put("user2_pw", user2_pw);

    }

    @Override
    public Map<String,String> getParams() {
        return parameters2;
    }
}