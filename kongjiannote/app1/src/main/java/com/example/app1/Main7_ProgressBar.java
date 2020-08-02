package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class Main7_ProgressBar extends AppCompatActivity implements View.OnClickListener {
    private Button button1;//增加进度
    private Button button2;//减少进度
    private Button button3;//重置进度
    private Button button4;//通知显示进度
    private ProgressBar progressBar;//进度条
    private TextView textView;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7__progress_bar);
        progressBar=(ProgressBar)findViewById(R.id.progressbar);
        textView=(TextView)findViewById(R.id.text);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        button4=(Button)findViewById(R.id.button4);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        int progress=progressBar.getProgress();
        int progress2=progressBar.getSecondaryProgress();
        int maxProgress=progressBar.getMax();
        textView.setText("第一进度百分比是："+(int)(progress/(float)maxProgress*100)
        +"%\n"+"第二进度百分比是："+(int)(progress2/(float)maxProgress*100)+"%");
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                progressBar.incrementProgressBy(10);
                progressBar.incrementSecondaryProgressBy(10);
                break;
            case R.id.button2:
                progressBar.incrementProgressBy(-10);
                progressBar.incrementSecondaryProgressBy(-10);
                break;
            case R.id.button3:
                progressBar.setProgress(20);
                progressBar.setSecondaryProgress(30);
                break;
            case R.id.button4:
                progressDialog=new ProgressDialog(this);
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setTitle("对话框");
                progressDialog.setIcon(R.mipmap.ic_launcher);
                progressDialog.setMax(100);
                progressDialog.incrementProgressBy(50);
                progressDialog.setIndeterminate(false);
                progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Main7_ProgressBar.this,"congratulation！",Toast.LENGTH_SHORT).show();
                    }
                });
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
        }
        int progress=progressBar.getProgress();
        int progress2=progressBar.getSecondaryProgress();
        int maxProgress=progressBar.getMax();
        textView.setText("第一进度百分比是："+(int)(progress/(float)maxProgress*100)
                +"%\n"+"第二进度百分比是："+(int)(progress2/(float)maxProgress*100)+"%");
    }
}
