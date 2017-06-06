package com.bb.offerapp2.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bb.offerapp2.R;
import com.bb.offerapp2.util.HttpCientCallback;
import com.bb.offerapp2.util.HttpTool;

/**
 * Created by bb on 2017/4/12.
 */
public class Pic extends Activity {
    private final String PATH = "https://d2lm6fxwu08ot6.cloudfront.net/img-thumbs/960w/82975551C7.jpg";
    private final String PATH2 = "https://3hsyn13u3q9dhgyrg2qh3tin-wpengine.netdna-ssl.com/wp-content/uploads/2016/04/SplitShire-190071.jpg";
    private final String PATH3 = "https://d2lm6fxwu08ot6.cloudfront.net/img-thumbs/960w/592EBB6D4B.jpg";
    private Button button,button2;
    private ImageView imageView, imageView2, imageView3;
    TextView textView, textView2, textView3;
    public Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                byte[] data = (byte[]) msg.obj;
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
                        data.length);
                imageView.setImageBitmap(bitmap);
                textView.setText(" ");

            }
            if (msg.what == 2) {
                byte[] data = (byte[]) msg.obj;
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
                        data.length);
                imageView2.setImageBitmap(bitmap);
                textView2.setText(" ");

            }
            if (msg.what == 3) {
                byte[] data = (byte[]) msg.obj;
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,
                        data.length);
                imageView3.setImageBitmap(bitmap);
                textView3.setText("");

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pic);
        imageView = (ImageView) findViewById(R.id.imageView1);
        imageView2 = (ImageView) findViewById(R.id.imageView2);
        imageView3 = (ImageView) findViewById(R.id.imageView3);
        textView = (TextView) findViewById(R.id.tv1);
        textView2 = (TextView) findViewById(R.id.tv2);
        textView3 = (TextView) findViewById(R.id.tv3);

        button = (Button) findViewById(R.id.btn);
        button2 = (Button) findViewById(R.id.btn2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                picDown();
                textView.setText("正在加载。。。");
                textView2.setText("正在加载。。。");
                textView3.setText("正在加载。。。");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Pic.this,AsyncTaskTest.class);
                startActivity(intent);
            }
        });
    }

    private void picDown() {
        HttpTool.sendRequestWithHttpCient(PATH, new HttpCientCallback() {
            @Override
            public void onFinish(byte[] data) {
                // ...省略对返回结果的处理代码
                Message message = Message.obtain();
                message.obj = data;
                message.what = 1;
                handler.sendMessage(message);
            }
            @Override
            public void onError(Exception e) {
                // ...省略请求失败的处理代码
            }
        });

        HttpTool.sendRequestWithHttpCient(PATH2, new HttpCientCallback() {
            @Override
            public void onFinish(byte[] data) {
                // ...省略对返回结果的处理代码
                Message message = Message.obtain();
                message.obj = data;
                message.what = 2;
                handler.sendMessage(message);
            }
            @Override
            public void onError(Exception e) {
                // ...省略请求失败的处理代码
            }
        });

        HttpTool.sendRequestWithHttpCient(PATH3, new HttpCientCallback() {
            @Override
            public void onFinish(byte[] data) {
                // ...省略对返回结果的处理代码
                Message message = Message.obtain();
                message.obj = data;
                message.what = 3;
                handler.sendMessage(message);
            }
            @Override
            public void onError(Exception e) {
                // ...省略请求失败的处理代码
            }
        });
    }
}

