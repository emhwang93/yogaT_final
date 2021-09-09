package org.techtown.example.expandablelistview;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Handler;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class OKHttpConnection {
    OkHttpClient client = new OkHttpClient();
    RequestBody body = null;
    Request request;
    static String responseData;
    static byte[] responseBytes;


    //top3 통신
    public void sendToServer_Top3(Callback callback,String date){
        body = new FormBody.Builder()
                .add("date", date).build();
        request = new Request.Builder()
                .url("http://192.168.0.6:8000/yogat/sendImageToAndroid_top3")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }


    //course 통신
    public void sendToServer_main(String imageString){
        Callback OKHttpCallback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("seul", "Error Message : " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseData = response.body().string();
                Log.d("seul", "responseData in OkhttpConnection : " + responseData);
            }
        };

        body = new FormBody.Builder()
                .add("image", imageString).build();
        request = new Request.Builder()
                .url("http://192.168.0.6:8000/yogat/userImage_save")
                .post(body)
                .build();
        client.newCall(request).enqueue(OKHttpCallback);
    }


    //course 통신
    public void sendToServer_singlePose(String imageString){
        Callback OKHttpCallback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("seul", "Error Message : " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseData = response.body().string();
                Log.d("seul", "responseData in OkhttpConnection : " + responseData);
            }
        };

        body = new FormBody.Builder()
                .add("image", imageString).build();
        request = new Request.Builder()
                .url("http://192.168.0.6:8000/yogat/singlePose")
                .post(body)
                .build();
        client.newCall(request).enqueue(OKHttpCallback);
    }




    //freemode 통신
    public void sendToServer_freemode(String imageString){
        Callback OKHttpCallback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("seul", "Error Message : " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseData = response.body().string();
                Log.d("seul", "responseData in OkhttpConnection : " + responseData);
            }
        };
        body = new FormBody.Builder()
                .add("image", imageString).build();
        request = new Request.Builder()
                .url("http://192.168.0.6:8000/yogat/freemode")
                .post(body)
                .build();
        client.newCall(request).enqueue(OKHttpCallback);
    }



    public void sendToServer_BandA(Callback callback,String pose_name){
        body = new FormBody.Builder()
                .add("pose_name", pose_name).build();
        request = new Request.Builder()
                .url("http://192.168.0.6:8000/yogat/sendImageToAndroid_BandA")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }



    public void sendToServer_Login(Callback callback,JSONObject jObject){


        body = new FormBody.Builder()
                .add("loginInfo", jObject.toString()).build();
        request = new Request.Builder()
                .url("http://192.168.0.6:8000/yogat/user_login")
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);

    }



    public void sendToServer_Register(JSONObject jObject){

        Callback OKHttpCallback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("seul", "Error Message : " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                responseData = response.body().string();
                Log.d("seul", "responseData in OkhttpConnection : " + responseData);
            }
        };

        body = new FormBody.Builder()
                    .add("userInfo",jObject.toString()).build();
        request = new Request.Builder()
                .url("http://192.168.0.6:8000/yogat/user_signup")
                .post(body)
                .build();
        client.newCall(request).enqueue(OKHttpCallback);

    }

}