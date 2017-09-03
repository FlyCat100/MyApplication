package com.example.myapplication;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * Created by 25737 on 2017/9/2.
 */
public class StreamUtils {
    public static String readStream(InputStream is)throws Exception{
        ByteArrayOutputStream baso = new ByteArrayOutputStream();
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len=is.read(buf))!=-1){
            baso.write(buf,0,len);
        }
        is.close();
        return baso.toString();
    }
}
