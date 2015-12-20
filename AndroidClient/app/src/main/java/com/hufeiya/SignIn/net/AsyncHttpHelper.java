package com.hufeiya.SignIn.net;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.hufeiya.SignIn.activity.CategorySelectionActivity;
import com.hufeiya.SignIn.activity.QuizActivity;
import com.hufeiya.SignIn.application.MyApplication;
import com.hufeiya.SignIn.fragment.CategorySelectionFragment;
import com.hufeiya.SignIn.fragment.SignInFragment;
import com.hufeiya.SignIn.jsonObject.JsonSignInfo;
import com.hufeiya.SignIn.jsonObject.JsonUser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

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
    public static JsonUser user;
    private static String serverURL = "http://192.168.2.8:8089/Server/";
    private static PersistentCookieStore myCookieStore = new PersistentCookieStore(MyApplication.getContext());
    private static UploadManager uploadManager = new UploadManager();//Qi Niu
    static {
        client.setCookieStore(myCookieStore);
    }


    public static void login(String phone, String pass, final SignInFragment fragment) {
        boolean isSucceed;
        final String md5pass = md5(pass);
        Log.d("login", md5pass);
        client.get(serverURL + "login?phone=" + phone + "&pass=" + md5pass, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    fragment.progressBar.setVisibility(View.GONE);
                    String isSucceed = response.getString(0);
                    if (isSucceed.equals("false")) {
                        fragment.toastLoginFail("account");

                    } else {
                        user = new Gson().fromJson(response.get(1).toString(), JsonUser.class);
                        fragment.savePlayer(md5pass);
                        fragment.enterTheCategorySelectionActivity();
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    fragment.toastLoginFail("json");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                fragment.toastLoginFail("unknown");
            }
        });

    }

    public static void refrash(String phone, String pass, final CategorySelectionFragment fragment) {
        String httpParameters = "login?phone=" + phone + "&pass=" + pass;
        client.get(serverURL + httpParameters, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String isSucceed = response.getString(0);
                    if (isSucceed.equals("false")) {
                        //TODO relogin
                    } else {
                        user = new Gson().fromJson(response.get(1).toString(), JsonUser.class);
                        fragment.swipeRefreshLayout.setRefreshing(false);
                        fragment.getmAdapter().updateCategories(fragment.getActivity());
                        fragment.getmAdapter().notifyDataSetChanged();
                        ((CategorySelectionActivity) fragment.getActivity()).setUpToolbar
                                (((CategorySelectionActivity) fragment.getActivity()).getUser());
                        fragment.setDrawer();

                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    fragment.toastLoginFail("json");
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                fragment.toastLoginFail("unknown");
            }
        });

    }

    public static void uploadLocation(final Context context,String cid, double latitude,double longitude){
        String httpParameters="location?cid=" + cid + "&latitude=" + latitude +"&longitude=" + longitude;
        client.get(serverURL + httpParameters, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                if (response.equals("true")){
                    ((QuizActivity)context).startSign();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                ((QuizActivity)context).toastNetUnavalible();
            }
        });
    }

    //get uptoken from server to have the access to Qi Niu,then Upload photo.
    public static void getUptokenAndUploadPhoto(final Context context, final byte[] photoBytes,final String signId){
        String httpParameters="uptoken?signid=" + signId;
        client.get(serverURL + httpParameters, null, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String token = new String(responseBody);
                if (token.equals("false")){
                    ((QuizActivity)context).uploadPhotoFail();
                }else{//succeed
                    uploadManager.put(photoBytes, signId, token, new UpCompletionHandler() {
                        @Override
                        public void complete(String s, ResponseInfo responseInfo, JSONObject jsonObject) {
                            ((QuizActivity) context).uploadPhotoSuccess();
                        }
                    }, null);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                ((QuizActivity)context).uploadPhotoFail();
            }
        });
    }

    public static void uploadSignInfo(final Context context,JsonSignInfo jsonSignInfo){
        String httpParameters="signin";
        RequestParams requestParams = new RequestParams();
        String json = new Gson().toJson(jsonSignInfo);
        requestParams.add("signInfo",json);
        client.post(serverURL + httpParameters, requestParams, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                String response = new String(responseBody);
                if (response.equals("true")) {
                    ((QuizActivity) context).onSignSuccess();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //TODO can also return {banned,null}
                ((QuizActivity) context).toastNetUnavalible();
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

    public static void clearAllCookies(){
        myCookieStore.clear();
    }
}
