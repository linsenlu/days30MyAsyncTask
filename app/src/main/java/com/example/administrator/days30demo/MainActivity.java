package com.example.administrator.days30demo;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    String path = "http://img1.imgtn.bdimg.com/it/u=3519872635,3148252276&fm=21&gp=0.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageView);

        MyTask task = new MyTask();
        task.setOncompleteListener(new MyTask.onCompleteListener() {
            @Override
            public void onCompleteListener(Bitmap bitmap) {
                if(null!=bitmap){
                    imageView.setImageBitmap(bitmap);
                }
            }
        });
        //执行异步任务
        task.execute(path);
    }
}
