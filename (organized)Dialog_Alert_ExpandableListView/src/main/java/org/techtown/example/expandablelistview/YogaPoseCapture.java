package org.techtown.example.expandablelistview;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class YogaPoseCapture extends Activity {
    ImageView stopBtnBeginner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yoga_pose_capture);

        Toast.makeText(YogaPoseCapture.this,
                "촬영 버튼을 눌러주세요", Toast.LENGTH_LONG).show();

        stopBtnBeginner = (ImageView) findViewById(R.id.BeginnercaptureBtn);
        //res/drawable 폴더레 있는 이미지 셋팅하기
        stopBtnBeginner.setImageResource(R.drawable.camera_icon);
        stopBtnBeginner.setOnClickListener(new BeginnerCaptureImage());
    }

    class BeginnerCaptureImage implements View.OnClickListener{

        @Override
        public void onClick(View v) {

        }
    }
}