package org.techtown.example.expandablelistview;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Diet extends Activity implements View.OnClickListener{


    private ImageView backImg1,backImg2,backImg3,backImg4,backImg5;
    private int resId;
    SinglePose singlePose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_pose);

        backImg1 = findViewById(R.id.singlePose1);
        backImg2 = findViewById(R.id.singlePose2);
        backImg3 = findViewById(R.id.singlePose3);
        backImg4 = findViewById(R.id.singlePose4);
        backImg5 = findViewById(R.id.singlePose5);
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


        backImg1.setOnClickListener(this);
        backImg2.setOnClickListener(this);
        backImg3.setOnClickListener(this);
        backImg4.setOnClickListener(this);
        backImg5.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.singlePose1){
            Intent intent = new Intent(Diet.this, SinglePose.class);
            intent.putExtra("resId", R.drawable.cat);
            startActivity(intent);
        }else if(v.getId() == R.id.singlePose2){
            Intent intent = new Intent(Diet.this, SinglePose.class);
            intent.putExtra("resId", R.drawable.cobra);
            startActivity(intent);
        }else if(v.getId() == R.id.singlePose3){
            Intent intent = new Intent(Diet.this, SinglePose.class);
            intent.putExtra("resId", R.drawable.bow);
            startActivity(intent);
        }else if(v.getId() == R.id.singlePose4){
            Intent intent = new Intent(Diet.this, SinglePose.class);
            intent.putExtra("resId", R.drawable.down_dog);
            startActivity(intent);
        }else{
            Intent intent = new Intent(Diet.this, SinglePose.class);
            intent.putExtra("resId", R.drawable.bridge);
            startActivity(intent);
        }

    }
}