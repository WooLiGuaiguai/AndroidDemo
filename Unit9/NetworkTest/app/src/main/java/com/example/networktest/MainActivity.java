package com.example.networktest;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.text_view);
        //使用HttpURLConnection 发送Http请求
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        HttpURLConnection connection=null;
                        BufferedReader reader=null;
                        try {
                            //通过传入目标网络的uri 获取HttpURLConnection实例
                            URL uri=new URL("http://www.baidu.com");
                            connection=(HttpURLConnection)uri.openConnection();
                            //设置Http请求所使用的方法
                            //GET 表示希望从服务器那里获取数据， 而POST则表示希望提交数据给服务器。
                            connection.setRequestMethod("GET");
                            /*connection.setRequestMethod("POST");
                            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
                            out.writeBytes("username=admin&password=123456");*/
                            //然后自由定制 设置连接超时、 读取超时的毫秒数
                            connection.setConnectTimeout(8000);
                            connection.setReadTimeout(8000);
                            //获服务器返回的输入流
                            InputStream is = connection.getInputStream();
                            //对获取到的输入流进行读取
                            reader=new BufferedReader(new InputStreamReader(is));
                            StringBuilder response=new StringBuilder();
                            String line;
                            while((line=reader.readLine())!=null){
                                response.append(line);
                            }
                            showResponse(response.toString());
                        }catch (IOException e) {
                            e.printStackTrace();
                        }finally {
                            if(reader!=null){
                                try {
                                    reader.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                            //关闭Http连接
                            if(connection!=null){
                                connection.disconnect();
                            }
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
