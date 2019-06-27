package com.example.synthesize;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Main5Activity extends AppCompatActivity {

    /**
     * Synthesize
     */
    private TextView mMa5Tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        initView();
    }

    private void initView() {
        mMa5Tv = (TextView) findViewById(R.id.ma5_tv);
        String data = getIntent().getStringExtra("data");
        mMa5Tv.setText(data);
    }
}
