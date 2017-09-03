package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class showActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        TextView tv_show = (TextView) findViewById(R.id.tv_show);

        Intent intent = getIntent();
        String content = intent.getStringExtra("content");

        tv_show.setText(content);

    }
}
