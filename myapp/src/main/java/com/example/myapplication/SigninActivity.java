package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity {

    private EditText et_pwd;
    private EditText et_name;
    private MyOpenHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        et_name = (EditText) findViewById(R.id.et_name);
        et_pwd =  (EditText) findViewById(R.id.et_pwd);
        helper =  new MyOpenHelper(this);
        

    }
    public void signin(View view){

        String name = et_name.getText().toString().trim();
        String pwd = et_pwd.getText().toString().trim();
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name",name);
        values.put("pwd",pwd);
        final String hint="注册成功！将返回登录";
        long userid = db.insert("user", null, values);
        if(userid!=-1){
//            Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
                    // TODO Auto-generated method stub

                    Toast.makeText(getApplicationContext(), hint, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SigninActivity.this, LoginActivity.class);
                    startActivity(intent);
                    SigninActivity.this.finish();
                }
            }, 1000);

        }else{
            Toast.makeText(this, "注册失败", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }

    //自动跳转到登录界面



}
