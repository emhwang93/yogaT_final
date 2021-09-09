package org.techtown.example.expandablelistview;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BackPainReliever extends Activity implements View.OnClickListener{

    private ImageView backImg1,backImg2,backImg3,backImg4,backImg5;
    private int resId;
    SinglePose singlePose;
    RelativeLayout relativeLayout1;
    RelativeLayout relativeLayout2;
    RelativeLayout relativeLayout3;
    RelativeLayout relativeLayout4;
    RelativeLayout relativeLayout5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_pose);

        backImg1 = findViewById(R.id.singlePose1);
        backImg2 = findViewById(R.id.singlePose2);
        backImg3 = findViewById(R.id.singlePose3);
        backImg4 = findViewById(R.id.singlePose4);
        backImg5 = findViewById(R.id.singlePose5);
        relativeLayout1 = (RelativeLayout) findViewById(R.id.rlt_layout_singlePose1);
        relativeLayout2 = (RelativeLayout) findViewById(R.id.rlt_layout_singlePose2);
        relativeLayout3 = (RelativeLayout) findViewById(R.id.rlt_layout_singlePose3);
        relativeLayout4 = (RelativeLayout) findViewById(R.id.rlt_layout_singlePose4);
        relativeLayout5 = (RelativeLayout) findViewById(R.id.rlt_layout_singlePose5);

        //고양이
        backImg1.setImageResource(R.drawable.cat);
        //코브라
        backImg2.setImageResource(R.drawable.cobra);
        //활
        backImg3.setImageResource(R.drawable.bow);
        //down dog
        backImg4.setImageResource(R.drawable.down_dog);
        //
        backImg5.setImageResource(R.drawable.bridge);


        relativeLayout1.setOnClickListener(this);
        relativeLayout2.setOnClickListener(this);
        relativeLayout3.setOnClickListener(this);
        relativeLayout4.setOnClickListener(this);
        relativeLayout5.setOnClickListener(this);

        }

    @Override
    public void onClick(View v) {
        //relative layout click listener 주기
        if(v.getId() == R.id.rlt_layout_singlePose1){
            Intent intent = new Intent(BackPainReliever.this, SinglePose.class);
            intent.putExtra("resId", R.drawable.cat);

            startActivity(intent);
        }

        if(v.getId() == R.id.rlt_layout_singlePose2){
            Intent intent = new Intent(BackPainReliever.this, SinglePose.class);
            intent.putExtra("resId", R.drawable.cobra);

            startActivity(intent);
        }
        if(v.getId() == R.id.rlt_layout_singlePose3){
            Intent intent = new Intent(BackPainReliever.this, SinglePose.class);
            intent.putExtra("resId", R.drawable.bow);

            startActivity(intent);
        }
        if(v.getId() == R.id. rlt_layout_singlePose4){
            Intent intent = new Intent(BackPainReliever.this, SinglePose.class);
            intent.putExtra("resId", R.drawable.down_dog);

            startActivity(intent);
        }
        if(v.getId() == R.id.rlt_layout_singlePose5){
            Intent intent = new Intent(BackPainReliever.this, SinglePose.class);
            intent.putExtra("resId", R.drawable.bridge);

            startActivity(intent);
        }

    }
}