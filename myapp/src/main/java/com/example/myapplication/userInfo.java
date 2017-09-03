package com.example.myapplication;

/**
 * Created by 25737 on 2017/9/2.
 */

public class userInfo {
    private int _id;
    private String name;
    private String pwd;

    public int get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
