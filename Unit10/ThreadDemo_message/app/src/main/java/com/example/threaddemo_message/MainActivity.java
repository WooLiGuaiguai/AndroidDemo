package com.example.threaddemo_message;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int UPDATE_TEXT = 1;
    private TextView text;
    @SuppressLint("HandlerLeak")
    //1.首先创建一个Handler对象 然后重写HandleMessage方法
    //3.发送出去后 进入MessageQueue队列，一个线程只有一个该队列
    //4.Looper调用loop方法将队列中的message取出并传递到handleMessage方法
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    text.setText("Nice to meet you");
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.change_text);
        text=(TextView)findViewById(R.id.text);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View view){
        switch (view.getId()){
            //2.然后子进程需要进行UI操作的时候
            case R.id.change_text:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //创建一个Message对象、设置他的what属性 并将这条Message发送出去
                        Message message=new Message();
                        message.what=UPDATE_TEXT;
                        handler.sendMessage(message);
                    }
                }).start();
                break;
            default:
                break;
        }
    }
}
