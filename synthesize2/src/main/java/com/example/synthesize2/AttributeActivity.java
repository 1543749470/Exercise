package com.example.synthesize2;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class AttributeActivity extends AppCompatActivity implements View.OnClickListener {
    int a=3;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (a>=0){
                mMyTv2.setText(a--+"");
            }else{
                timer.cancel();
                startActivity(new Intent(AttributeActivity.this,PagerActivity.class));
            }
        }
    };
    private ImageView mMyImg2;
    /**
     * 3
     */
    private TextView mMyTv2;
    /**
     * 跳过
     */
    private Button mMyBt2;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attribute);
        initView();
        initTimer();
    }

    private void initTimer() {
        ObjectAnimator alpha = ObjectAnimator.ofFloat(mMyImg2, "alpha", 1f, 0f, 1f);
        alpha.setDuration(5000);
        alpha.start();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        },1000,1000);
    }

    private void initView() {
        mMyImg2 = (ImageView) findViewById(R.id.myImg2);
        mMyTv2 = (TextView) findViewById(R.id.myTv2);
        mMyBt2 = (Button) findViewById(R.id.myBt2);
        mMyBt2.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.myBt2:
                timer.cancel();
                timer.cancel();
                startActivity(new Intent(AttributeActivity.this,PagerActivity.class));
                break;
        }
    }
}
