package com.example.app1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.app1.DialogTest.Many_choose;
import com.example.app1.DialogTest.Single_choose;
import com.example.app1.DialogTest.list_choose;
import com.example.app1.DialogTest.submit_queren;
import com.example.app1.DialogTest.zidingyi;

public class Main9_TimePicker extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main9__time_picker);
        Button button1=(Button)findViewById(R.id.button1);
        Button button2=(Button)findViewById(R.id.button2);
        Button button3=(Button)findViewById(R.id.button3);
        Button button4=(Button)findViewById(R.id.button4);
        Button button5=(Button)findViewById(R.id.button5);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        /*button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main9_TimePicker.this, submit_queren.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main9_TimePicker.this, Single_choose.class);
                startActivity(intent);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main9_TimePicker.this, Many_choose.class);
                startActivity(intent);
            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main9_TimePicker.this, list_choose.class);
                startActivity(intent);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Main9_TimePicker.this, zidingyi.class);
                startActivity(intent);
            }
        });*/
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                AlertDialog.Builder builder1=new AlertDialog.Builder(this);
                builder1.setTitle("确认对话框");
                builder1.setIcon(R.mipmap.ic_launcher);
                builder1.setMessage("是否确认这条通知？");
                builder1.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Main9_TimePicker.this,"你点击来确定", Toast.LENGTH_SHORT).show();
                    }
                });
                builder1.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Main9_TimePicker.this,"你点击来取消", Toast.LENGTH_SHORT).show();
                    }
                });
                Dialog dialog1=builder1.create();
                dialog1.show();
                break;
            case R.id.button2:
                AlertDialog.Builder builder2=new AlertDialog.Builder(this);
                builder2.setTitle("请选择你的年级：");
                builder2.setIcon(R.mipmap.ic_launcher);
                final String[] strings={"一年级","二年级","三年级","四年级","五年级","六年级"};
                builder2.setSingleChoiceItems(strings, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Main9_TimePicker.this,"选中了"+strings[i],Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                Dialog dialog2=builder2.create();
                dialog2.show();
                break;
            case R.id.button3:
                AlertDialog.Builder builder3=new AlertDialog.Builder(this);
                builder3.setTitle("请选择你的年级：");
                builder3.setIcon(R.mipmap.ic_launcher);
                final String[] strins={"一年级","二年级","三年级","四年级","五年级","六年级"};
                builder3.setMultiChoiceItems(strins, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            Toast.makeText(Main9_TimePicker.this,"选中了"+strins[i],Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(Main9_TimePicker.this,"取消选择了"+strins[i],Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Dialog dialog3=builder3.create();
                dialog3.show();
                break;
            case R.id.button5:
                LayoutInflater inflater=LayoutInflater.from(this);
                View view1=inflater.inflate(R.layout.dialog_layout,null);
                AlertDialog.Builder builder5=new AlertDialog.Builder(this);
                builder5.setTitle("你喜欢吗");
                builder5.setView(view1);
                Dialog dialog5=builder5.create();
                dialog5.show();
                break;
        }
    }
}
