package com.example.administrator.days30demo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/7/8.
 */
public class MyTask extends AsyncTask<String,Void,Bitmap> {

    private Bitmap bitmap;
    onCompleteListener listener;
    public MyTask() {

    }
    public interface onCompleteListener{
        void onCompleteListener(Bitmap bitmap);
    }
    public void setOncompleteListener(onCompleteListener listener){
        this.listener = listener;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String path = params[0];
        URL url ;
        HttpURLConnection connection =null;
        InputStream inputstream = null;
        try {
            url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();
            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK){
            inputstream = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputstream);
                Log.e("print","DownLoad success");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("print","DownLoad fail");
        }finally {
            if (null!=inputstream){
                try {
                    inputstream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null!=connection){
                connection.disconnect();
            }
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap result) {
        if (null!=listener){
            listener.onCompleteListener(result);
        }
    }
}
