package org.techtown.example.expandablelistview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import okio.GzipSink;

public class Top3 extends AppCompatActivity{

    //bring response image and text
    private String responseData;
    private OKHttpConnection okHttpConnection;
    private Gson gson;
    private List<Bitmap> imageList;
    private ImagesDTO images;

    //layout
    private ImageView top1;
    private ImageView top2;
    private ImageView top3;
    private ImageView loading;


    private TextView top1_name;
    private TextView top2_name;
    private TextView top3_name;

    //calendar
    EditText dateTXT;
    ImageView cal;
    private int mDate, mMonth, mYear;
    private String requestDate;
    ProgressDialog progressDialog;
    //object
    BackgroundThread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.top_3);

        //layout init
        top1 = findViewById(R.id.top1);
        top2 = findViewById(R.id.top2);
        top3 = findViewById(R.id.top3);
        loading = findViewById(R.id.loading);

        top1_name = findViewById(R.id.top1_name);
        top2_name = findViewById(R.id.top2_name);
        top3_name = findViewById(R.id.top3_name);

        dateTXT = findViewById(R.id.dateTop3);
        cal = findViewById(R.id.datepickerTop3);
        //init object
        okHttpConnection = new OKHttpConnection();
        progressDialog = new ProgressDialog(Top3.this);
        thread = new BackgroundThread();



        //calendar버튼
        cal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                final Calendar Cal = Calendar.getInstance();
                mDate = Cal.get(Calendar.DATE);
                mMonth = Cal.get(Calendar.MONTH);
                mYear = Cal.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(Top3.this, android.R.style.Theme_DeviceDefault_Dialog, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOFMonth) {
                        dateTXT.setText(year + "-" + (monthOfYear +1) + "-" + dayOFMonth);
                        requestDate = year + "-" +(monthOfYear +1) + "-" + dayOFMonth;
                    }
                }, mYear, mMonth, mDate);

                datePickerDialog.show();

            }
        });

        //서버연동 및 스레드 시작

        thread.start();
    }

    //background에서 thread로 handler가 계속 돌면서 imageList를 받아옴
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (requestDate != null) {
                okHttpConnection.sendToServer_Top3(getOKHttpCallback(), requestDate);


                if (imageList != null) {
                    progressDialog.dismiss();
                    top1.setImageBitmap(imageList.get(0));
                    top2.setImageBitmap(imageList.get(1));
                    top3.setImageBitmap(imageList.get(2));

                    thread.stopThread(false);
                    Log.d("seul", "thread check");
                } else {
           /*         Glide.with(Top3.this)
                            .asGif()
                            .load(R.raw.loading)
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .into(loading);*/

                    progressDialog.setMessage("loading");
                    progressDialog.setCancelable(true);
                    progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Horizontal);
                    progressDialog.show();
                }
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

                for(int i=0; i<images.getImages().size(); i++){
                    String top3ImgStr = images.getImages().get(i);
                    byte[] bytes = Base64Util.decode(top3ImgStr);
                    Bitmap imagesInTop3 = Base64Util.byteArrayToBitmap(bytes);
                    imageList.add(imagesInTop3);
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
