package com.example.httputildemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button);
        button.setOnClickListener(this);
        Button button1=(Button)findViewById(R.id.button2);
        button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.button){
            sendRequestWithHttpURLConnection();
        }
        if(view.getId()==R.id.button2){
            sendRequestWithOkHttp();
        }

    }
    private void sendRequestWithHttpURLConnection(){
        String address="http://192.168.1.103:80/get_data1.json";
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                try{
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String version = jsonObject.getString("version");
                        Log.d("MainActivity", "id is " + id);
                        Log.d("MainActivity", "name is " + name);
                        Log.d("MainActivity", "version is " + version);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onError(Exception e) {
                Log.d("MainActivity","error 出现");
            }
        });
    }
    private void sendRequestWithOkHttp(){
        String address="http://192.168.1.103:80/get_data1.json";
        HttpUtil.sendHttpRequest2(address, new okhttp3.Callback() {

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String responseData=response.body().string();
                try {//先把服务器返回的数据传入得到一个JSONArray对象中
                    JSONArray jsonArray = new JSONArray(responseData);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String id = jsonObject.getString("id");
                        String name = jsonObject.getString("name");
                        String version = jsonObject.getString("version");
                        Log.d("MainActivity", "id is " + id);
                        Log.d("MainActivity", "name is " + name);
                        Log.d("MainActivity", "version is " + version);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.d("MainActivity","error 出现");
            }
        });
    }
}
