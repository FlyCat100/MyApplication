package com.example.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import android.widget.BaseAdapter;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<newsInfo> lists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView lv = (ListView) findViewById(R.id.lv);
        ImageView iv = (ImageView) findViewById(R.id.iv);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(){
                    @Override
                    public void run() {
                        try {
                            String path = "http://www.baidu.com";
                            URL url = new URL(path);
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setReadTimeout(5000);
                            conn.setRequestMethod("GET");
                            int code = conn.getResponseCode();
                            if(code==200){
                                InputStream is = conn.getInputStream();
                                String content = StreamUtils.readStream(is);
                                Intent intent = new Intent(MainActivity.this,showActivity.class);
                                intent.putExtra("content",content);
                                startActivityForResult(intent,10);
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }.start();

            }
        });

        lists = new ArrayList<>();
        for (int i = 0; i <= 15; i++) {
            newsInfo news = new newsInfo();
            news.setTitle("这是一条有趣的标题" + i);
            news.setContent("这是一条有内涵的新闻"+i);
            lists.add(news);
        }
        lv.setAdapter(new MyAdapter());

    }


    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return lists.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View convertview, ViewGroup viewGroup) {
            View view;
            if (convertview == null) {
                view = View.inflate(getApplicationContext(), R.layout.item, null);
            } else {
                view = convertview;
            }
            TextView tv_content = (TextView) view.findViewById(R.id.tv_content);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);

            tv_title.setText(lists.get(i).getTitle());
            tv_content.setText(lists.get(i).getContent());
            return view;
        }
    }
}
