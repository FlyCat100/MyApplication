package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import static android.R.attr.password;

public class LoginActivity extends AppCompatActivity {

    private EditText let_name;
    private EditText let_pwd;
    private CheckBox cb_islogin;
    private SharedPreferences sp;
    private MyOpenHelper helper;
    private String pwd;
    private String name;
    private Button bt_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        let_name =  (EditText) findViewById(R.id.let_name);
        let_pwd =  (EditText) findViewById(R.id.let_pwd);
        cb_islogin =  (CheckBox) findViewById(R.id.ck_login);
        bt_login =  (Button) findViewById(R.id.bt_login);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pwd =  let_pwd.getText().toString().trim();
                name =  let_name.getText().toString().trim();
                //2.判断用户名密码是否为空 如果什么也不输入 给用户友好提示
                if (TextUtils.isEmpty(name) ||TextUtils.isEmpty(pwd) ){  //如果if条件成立 就说明用户名为空

                    Toast.makeText(LoginActivity.this,"用户名或者密码为空",Toast.LENGTH_LONG).show();
                }else{

                    if (cb_islogin.isChecked()){
                        //说明cb已经勾选
                        //3.说明用户名密码不为空 使用sp来存数据  获取sp的实例
                        //3.h1 获取sp的编辑器
                        SharedPreferences.Editor edit = sp.edit();
                        //3.2 存数据 用户名 和密码
                        edit.putString("name",name);
                        edit.putString("pwd",pwd);
                        //3.3记录checkbox状态
                        edit.putBoolean("isChecked",true);
                        //3.4 调用commit方法提交数据
                        edit.commit();
                    }else{
                        //3.5 使用sp在获取一个edit实例
                        SharedPreferences.Editor edit = sp.edit();
                        edit.putBoolean("isChecked",false);
                        edit.commit();
                    }

                    if(checkUser(name,pwd).moveToNext()){
                        Intent intent=new Intent();
                        Bundle bundle=new Bundle();
                        bundle.putString("KEY_NAME",name);
                        intent.setClass(LoginActivity.this, MainActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                    else{
                        new AlertDialog.Builder(LoginActivity.this).setMessage("登录失败")
                                .setPositiveButton("确定", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub
                                        let_name.setText("");
                                        let_pwd.setText("");
                                        let_name.requestFocus();
                                    }
                                }).show();
                    }

                }
            }
        });

        //获取sharedprefences对象
        sp =  getSharedPreferences("config", 0);
        boolean state = sp.getBoolean("isChecked", false);
        String name = sp.getString("name", "");
        String pwd = sp.getString("pwd", "");
        if(state){
            cb_islogin.setChecked(true);

            let_name.setText(name);
            let_pwd.setText(pwd);
        }else{
            cb_islogin.setChecked(false);

            let_name.setText("");
            let_pwd.setText("");
        }

    }

    public void lsignin(View view){
        Intent intent = new Intent(this,SigninActivity.class);
        startActivity(intent);
    }

    //checkuser
    public Cursor checkUser(String name, String pwd) {
        helper = new MyOpenHelper(LoginActivity.this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor mCursor = (SQLiteCursor) db.query(true, MyOpenHelper.DATABASE_TABLE,
                new String[] { "name" }, "name=? and pwd=?",
                new String[] { name, pwd }, null, null, null, null);

        return mCursor;

    }
}
