package org.techtown.example.expandablelistview;

import android.Manifest;
import android.content.Context;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.hardware.Camera;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;


import org.jetbrains.annotations.NotNull;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import java.util.Timer;
import java.util.TimerTask;

public class SittingPose extends AppCompatActivity implements View.OnClickListener{

    //MainActivity
    static Context context;
    private TimerTask tt;


    //send image to Django
    static String imageString;
    private String responseData;
    private byte[] pictureByteArr;
    private Bitmap imageBitmap;

    //layout
    private ImageView captureBtn,stopBtn,imageView;

    //object
    private OKHttpConnection okHttpConnection;
    private TextToSpeech_yogat tts;
    private CountDownTimer countDownTimer;
    private TimerTask sampleDataTimer;

    //camera preview
    private Camera mCamera;
    private int mCameraFacing;
    private RelativeLayout preview;
    private CameraPreview mCameraView;

    private Animation animFadeIn, animFadeOut;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.beginner);


        //init
        mCamera = getCameraInstance();
        context = getApplicationContext();
        okHttpConnection = new OKHttpConnection();
        tts = new TextToSpeech_yogat(this);


        //layout init
        imageView = findViewById(R.id.imageView);
        preview = findViewById(R.id.preview);
        captureBtn = findViewById(R.id.BeginnercaptureBtn);
        stopBtn = findViewById(R.id.stopBtnBeginner);




        //카메라 프리뷰
        //mCameraFacing = 전면 or 후면을 결정
        mCameraFacing = (mCameraFacing == Camera.CameraInfo.CAMERA_FACING_BACK) ?
                Camera.CameraInfo.CAMERA_FACING_FRONT
                : Camera.CameraInfo.CAMERA_FACING_BACK;
        mCameraView = new CameraPreview(this, mCamera, mCameraFacing,0,0);

        preview.addView(mCameraView);
        imageView.setAlpha(90); //이미지 투명도
        imageView.bringToFront();   //imageView 최상단으로 올리기
        stopBtn.bringToFront();
        captureBtn.bringToFront();


        //촬영 버튼
        captureBtn.setOnClickListener(this);
        //정지 버튼
        stopBtn.setOnClickListener(this);
        //handler = new MainHandler();
    }   //onCreate
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }

    }


    //Django서버에 이미지를 전송
    public void sendToServer() {
        try {

            //http통신에선 데이터를 주고 받을 때 String형식을 통해 주고받을 수 있음
            //Base64를 통해서 byteArray를 String으로 변환해서 전송
            imageBitmap =  mCameraView.getBitmap();
            pictureByteArr = Base64Util.bitmapToByteArray(imageBitmap);
            imageString = Base64Util.encode(pictureByteArr);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        okHttpConnection.sendToServer_main(imageString);
        responseData = OKHttpConnection.responseData;
        Log.d("seul","responseData in sendToServer method in Main is" + responseData);


    }

    public Camera getCameraInstance() {
        Camera c = null;
        try {
            // attempt to get a Camera instance
            c = Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // returns null if camera is unavailable
        return c;
    }


    //timer가 끝난 후 사진 촬영
    public void timerInTakingPictures(){

        //timer가 실행될 때 해야할 일
        tt = new TimerTask() {
            @Override
            public void run() {
                mCameraView.capture();
                sendToServer();
                Log.d("seul","responseData in Timer is " + responseData  );

                tts.sayText(responseData,"SittingPose");
            }
        };
        sampleDataTimer = new TimerTask(){
            @Override
            public void run() {
                imageView.setImageResource(R.drawable.chair);
            }
        };

        //timer 설정 (해야할 일 , 몇 초후에 시작할지, 타이머 주기)
        Timer timer = new Timer();
        timer.schedule(tt,10000,10000);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.BeginnercaptureBtn){
            Toast.makeText(SittingPose.this,"자세를 잡아주세요",Toast.LENGTH_LONG).show();

            timerInTakingPictures();
            //지속시간, 카운트다운 시간
            countDownTimer = new CountDownTimer(5000, 5000) {

                public void onTick(long millisUntilFinished) {
                    animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.fade_in);

                    imageView.startAnimation(animFadeIn);
                    imageView.setImageResource(R.drawable.chair);

                }

                public void onFinish() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    animFadeOut = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.fade_out);
                    imageView.startAnimation(animFadeOut);

                    Toast.makeText(SittingPose.this,"다음 자세로 넘어갑니다",Toast.LENGTH_LONG).show();
                    animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(),
                            R.anim.fade_in);

                    imageView.startAnimation(animFadeIn);
                    imageView.setImageResource(R.drawable.cobra);
                }
            }.start();
        }else if(v.getId() == R.id.stopBtnBeginner){
            tt.cancel();
        }
    }
  /*  class ValueHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            imageView.setImageResource(R.drawable.chair);
        }
    }*/
}
