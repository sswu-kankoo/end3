package com.kankoo.end3;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {

    final static private String URL = "http://10.0.2.2:80/UserLogin.php";
    private Map<String,String> parameters;

    public LoginRequest(String user1_id, String user1_pw, Response.Listener<String> listener ) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("user1_id", user1_id);
        parameters.put("user1_pw", user1_pw);

    }

    @Override
    public Map<String,String> getParams() {
        return parameters;
    }
}
