package org.techtown.example.expandablelistview;



import android.app.ActionBar;
import android.app.Activity;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toolbar;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class BeforeAfter extends Activity {

    //layout
    private ImageView beforeImg;
    private ImageView afterImg;
    private Spinner select_pose;


    //bring response image and text
    private String responseData;
    private OKHttpConnection okHttpConnection;
    private Gson gson;
    private List<Bitmap> imageList;
    private ImagesDTO images;

    //object
    private BackgroundThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.before_after);

        beforeImg = findViewById(R.id.beforeImg);
        afterImg = findViewById(R.id.afterImg);
        select_pose = findViewById(R.id.select_pose);


        String pose_name = select_pose.getSelectedItem().toString();

        okHttpConnection = new OKHttpConnection();
        thread = new BackgroundThread();
        thread.start();

        okHttpConnection.sendToServer_BandA(getOKHttpCallback(),pose_name);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.pose_array,android.R.layout.simple_spinner_dropdown_item);
        select_pose.setAdapter(adapter);


    }



    //background에서 thread로 handler가 계속 돌면서 imageList를 받아옴
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
/*
            if(requestDate != null){
                okHttpConnection.sendToServer_Top3(getOKHttpCallback(),requestDate);
            }*/

            if(imageList != null){
                beforeImg.setImageBitmap(imageList.get(0));
                afterImg.setImageBitmap(imageList.get(1));


                thread.stopThread(false);
                Log.d("seul","thread check");
            }
        }
    };

    //response 객체 받아오는 callback 함수
    private Callback getOKHttpCallback() {
        Callback OKHttpCallback = new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("seul", "Error Message : " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                
                responseData = response.body().string();
                Log.d("seul","response 성공");

                //response가 json형식이기 떄문에 gson을 사용해서 imagesDTO에 받아옴
                gson = new Gson();
                images = gson.fromJson(responseData,ImagesDTO.class);
                imageList = new ArrayList<>();
                
                //image 변환
                for(int i=0; i<images.getImages().size(); i++){
                    String top3ImgStr = images.getImages().get(i);
                    byte[] bytes = Base64Util.decode(top3ImgStr);
                    Bitmap imagesInTop3 = Base64Util.byteArrayToBitmap(bytes);
                    imageList.add(imagesInTop3);
                    Log.d("seul",imageList+"!!!");
                }
            }
        };
        return OKHttpCallback;
    }//getOKHttpCallback


    class BackgroundThread extends Thread {
        private boolean running = false;

        BackgroundThread(){
            running = true;
        }
        public void stopThread(boolean running){
            this.running = running;
        }

        @Override
        public void run() {
            while(running){

                Bundle bundle = new Bundle();
                bundle.putInt("value", 1);
                Message msg = handler.obtainMessage();
                msg.setData(bundle);
                handler.sendMessage(msg);

                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }//background Thread
}