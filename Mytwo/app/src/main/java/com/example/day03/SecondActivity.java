package com.example.day03;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button button=(Button) findViewById(R.id.button_2) ;
        editText=(EditText)findViewById(R.id.edit_1);
        imageView=(ImageView)findViewById(R.id.image_1);
        progressBar=(ProgressBar)findViewById(R.id.pro_1);
        Button button1=(Button)findViewById(R.id.butt_3);
        Button button2=(Button)findViewById(R.id.butt_4);
        Button button3=(Button)findViewById(R.id.butt_5);
        button.setOnClickListener(this);
        imageView.setOnClickListener(this);
        progressBar.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);//百分比布局测试
        Intent intent=getIntent();
        Log.d("SecondActivity",intent.getStringExtra("data"));
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.butt_5:
                Intent intent=new Intent(SecondActivity.this,SixActivity.class);
                startActivity(intent);
            case R.id.butt_4:
                ProgressDialog progressDialog = new ProgressDialog(SecondActivity.this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;
            case R.id.butt_3:
                AlertDialog.Builder dialog=new AlertDialog.Builder (SecondActivity.this);
                dialog.setTitle("这是一个警告框");
                dialog.setMessage("下面是你的记警告内容");
                dialog.setCancelable(false);
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                dialog.show();
                break;
            case R.id.button_2:
                //Toast.makeText(SecondActivity.this,editText.getText().toString(),Toast.LENGTH_SHORT).show();
                int pro=progressBar.getProgress();
                pro=pro+10;
                progressBar.setProgress(pro);
                break;
            case R.id.image_1:
                imageView.setImageResource(R.drawable.img_2);
                break;
            case R.id.button_3:
                if(progressBar.getVisibility()==View.GONE){
                    progressBar.setVisibility(View.VISIBLE);
                }else{
                    progressBar.setVisibility(View.GONE);
                }
                default:
                    break;
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("key", "Hello FirstActivity");
        setResult(RESULT_OK, intent);
        finish();
    }
}
