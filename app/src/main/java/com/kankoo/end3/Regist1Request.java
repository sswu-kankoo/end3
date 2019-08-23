package com.kankoo.end3;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Regist1Request extends StringRequest {

    final static private String URL = "http://10.0.2.2:80/user1register.php";
    private Map<String,String> parameters;
    //private Map<String,Integer> parameters1;

    public Regist1Request(String user1_id, String user1_pw, String user1_mj, String user1_name, String user1_stID, Response.Listener<String> listener ) {
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("user1_id", user1_id);
        parameters.put("user1_pw", user1_pw);
        parameters.put("user1_mj", user1_mj);
        parameters.put("user1_name", user1_name);
        parameters.put("user1_stID", user1_stID );

        }

        @Override
        public Map<String,String> getParams(){
            return parameters;
        }

        /*
        public Map<String,Integer> getparame(){
            return parameters1;
        } */
}
