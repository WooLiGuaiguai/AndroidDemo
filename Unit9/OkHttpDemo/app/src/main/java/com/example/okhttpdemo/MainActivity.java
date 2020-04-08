package com.example.okhttpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.text_view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //创建一个OkHttpClient实例
                            OkHttpClient client=new OkHttpClient();
                            //1.发送Http请求 想要GET 创建一个Request对象
                            Request request=new Request.Builder()
                                    .url("http://www.baidu.com")//设置网络目标地址
                                    .build();
                            //2.发送Http请求 想要POST
                            //构建一个RequestBody对象存放要提交的参数
                            RequestBody requestbody=new FormBody.Builder()
                                    .add("username","admin")
                                    .add("password","123456")
                                    .build();
                            //然后向请求中传入该对象
                            Request request1=new Request.Builder()
                                    .url("http://www.baidu.com")//设置网络目标地址
                                    .post(requestbody)
                                    .build();
                            //调用OkkHttpClient的newcall方法创建一个call对象
                            // 并调用它的execute()方法来发送请求并获取服务器返回的数据
                            Response response = client.newCall(request).execute();
                            //得到返回数据的具体内容
                            String responseDate = response.body().string();
                            showResponse(responseDate);
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作， 将结果显示到界面上
                textView.setText(response);
            }
        });
    }
}
