package com.example.synthesize2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    /**
     * Synthesize2
     */
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
        String data = getIntent().getStringExtra("data");
        mTv.setText(data);
    }
}
