package com.example.synthesize2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int a=3;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (a>=0){
                mMyTv.setText(a--+"");
            }else{
                timer.cancel();
                startActivity(new Intent(MainActivity.this,AttributeActivity.class));
            }
        }
    };
    private ImageView mMyImg;
    /**
     * 3
     */
    private TextView mMyTv;
    /**
     * 跳过
     */
    private Button mMyBt;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initTimer();
    }

    private void initTimer() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.animate_layout);
        mMyImg.setAnimation(animation);
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        },1000,1000);
    }

    private void initView() {
        mMyImg = (ImageView) findViewById(R.id.myImg);
        mMyTv = (TextView) findViewById(R.id.myTv);
        mMyBt = (Button) findViewById(R.id.myBt);
        mMyBt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.myBt:
                timer.cancel();
                startActivity(new Intent(MainActivity.this,AttributeActivity.class));
                break;
        }
    }
}
