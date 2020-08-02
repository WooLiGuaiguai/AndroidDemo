package com.example.okhttpdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1=(Button)findViewById(R.id.get_one);
        button1.setOnClickListener(this);
        Button button2=(Button)findViewById(R.id.get_second);
        button2.setOnClickListener(this);
        Button button3=(Button)findViewById(R.id.POST_ONE);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OkHttpClient okHttpClient=new OkHttpClient();
                FormBody formBody=new FormBody.Builder()
                        .add("user-name","admin")
                        .add("password","admin")
                        .build();
                final Request request=new Request.Builder()
                        .url("https://www.baidu.com")
                        .post(formBody)
                        .build();
                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Toast.makeText(MainActivity.this,"post failed",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String res=response.body().string();
                        Toast.makeText(MainActivity.this,"post successed",Toast.LENGTH_SHORT).show();
                        Log.d("onResponse:",res);
                    }
                });
            }
        });
        Button button4=(Button)findViewById(R.id.POST_two);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaType mediaType=MediaType.parse("text/plain;charset=utf-8");
                String requestbody=" I am android";
                Request request=new Request.Builder()
                        .url("https://api.github.com/markdown/raw")
                        .post(RequestBody.create(requestbody,mediaType))
                        .build();
                OkHttpClient okHttpClient=new OkHttpClient();
                Call call=okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Toast.makeText(MainActivity.this,"post failed",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        Log.d("", response.protocol() + " " +response.code() + " " + response.message());
                        Headers headers = response.headers();
                        for (int i = 0; i < headers.size(); i++) {
                            Log.d("", headers.name(i) + ":" + headers.value(i));
                        }
                        Log.d("onResponse: ",response.body().string());
                    }
                });
            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.get_one:
                //异步发起的请求会被加入到 Dispatcher 中的 runningAsyncCalls双端队列中通过线程池来执行。
                String url="https://www.baidu.com";
                //如果需要在get请求时传递参数
                //String url="https:www.baidu.com?username=admin&password=admin"
                //1.获取OkHttpClient对象
                OkHttpClient okHttpClient=new OkHttpClient();
                //2.构造Request对象 默认是get请求，这里进行指明并传入请求地址
                Request request=new Request.Builder()
                        .url(url)
                        .get()
                        .build();
                //3. 将Requset封装成Call
                Call call=okHttpClient.newCall(request);
                //4.提交异步请求
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {
                        Toast.makeText(MainActivity.this,"get failed",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                        String res=response.body().string();
                        Toast.makeText(MainActivity.this,"get successed",Toast.LENGTH_SHORT).show();
                        Log.d("onResponse:",res);
                    }
                });
                break;
            case R.id.get_second:
                String url2="https://www.baidu.com";
                //如果需要在get请求时传递参数
                //String url="https:www.baidu.com?username=admin&password=admin"
                //1.获取OkHttpClient对象
                OkHttpClient okHttpClient2=new OkHttpClient();
                //2.构造Request对象 默认是get请求，这里进行指明并传入请求地址
                Request request2=new Request.Builder()
                        .url(url2)
                        .get()
                        .build();
                //3. 将Requset封装成Call
                final Call call2=okHttpClient2.newCall(request2);
                //4.提交同步请求
                //最后一步是通过 Call#execute() 来提交请求，这种方式会阻塞调用线程，所以在Android中应放在子线程中执行，
                //否则有可能引起ANR异常，Android3.0 以后已经不允许在主线程访问网络。
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Response response = call2.execute();
                            Log.d("run: " ,response.body().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;
        }
    }
}
