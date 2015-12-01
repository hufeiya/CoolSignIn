package com.hufeiya.SignIn.net;

import android.util.Log;

import com.google.gson.Gson;
import com.hufeiya.SignIn.jsonObject.JsonUser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by hufeiya on 15-12-1.
 */
public class AsyncHttpHelper {

    private static AsyncHttpClient client = new AsyncHttpClient();
    private static JsonUser user;
    private static String serverURL = "http://192.168.2.8:8089/Server/";

    public static void login(String phone,String pass){
        boolean isSucceed;
        pass = md5(pass);
        Log.d("login",pass);
        client.get(serverURL+"login?phone="+phone+"&pass="+pass,null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String isSucceed = response.getString(0);
                    if (isSucceed == "false"){
                        //TODO login fail.
                    }else{
                        user = new Gson().fromJson(response.get(1).toString(),JsonUser.class);
                        Log.d("login",user.getPhone());
                        Log.d("login",user.getPass());
                        Log.d("login",user.getUsername());
                        Log.d("login",user.getUserNo());
                        Log.d("login",user.toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public static String md5(String string) {
        byte[] hash;
        try {

            hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));

        } catch (NoSuchAlgorithmException e) {

            throw new RuntimeException("Huh, MD5 should be supported?", e);

        } catch (UnsupportedEncodingException e) {

            throw new RuntimeException("Huh, UTF-8 should be supported?", e);

        }
        StringBuilder hex = new StringBuilder(hash.length * 2);
        for (byte b : hash) {

            if ((b & 0xFF) < 0x10) hex.append("0");

            hex.append(Integer.toHexString(b & 0xFF));

        }
        return hex.toString();
    }
}
