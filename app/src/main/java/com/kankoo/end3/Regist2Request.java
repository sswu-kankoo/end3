package com.kankoo.end3;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Regist2Request extends StringRequest {

    final static private String URL = "http://10.0.2.2:80/user2register.php";
    private Map<String, String> parameters;
    //private Map<String,Integer> parameters1;

    public Regist2Request(String user2_id, String user2_pw, String user2_ShopName, String user2_BRN, Response.Listener<String> listener) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("user2_id", user2_id);
        parameters.put("user2_pw", user2_pw);
        parameters.put("user2_ShopName", user2_ShopName);
        parameters.put("user2_BRN", user2_BRN + "");

    }

    @Override
    public Map<String, String> getParams() {
        return parameters;
    }
}
