package com.example.sharedpreferencestest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button) findViewById(R.id.getSharedPreference);
        button.setOnClickListener(this);
        Button button1=(Button)findViewById(R.id.getPreferences);
        button1.setOnClickListener(this);
        Button button2=(Button)findViewById(R.id.getDefaultSharedPreferences);
        button2.setOnClickListener(this);
        Button button3=(Button)findViewById(R.id.getData);
        button3.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.getSharedPreference:
                sharedPreferences=
                        getSharedPreferences("第一种方法",MODE_PRIVATE);
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putString("姓名","阿里巴巴");
                edit.putInt("年龄",22);
                edit.putBoolean("married", false);
                edit.apply();
                Toast.makeText(MainActivity.this,"已经保存数据1",Toast.LENGTH_SHORT).show();
                break;
            case R.id.getPreferences :
                SharedPreferences sharedPreferences1=getPreferences(MODE_PRIVATE);
                SharedPreferences.Editor edit1 = sharedPreferences1.edit();
                edit1.putString("姓名","亚马逊");
                edit1.putInt("年龄",25);
                edit1.putBoolean("married", true);
                edit1.apply();
                Toast.makeText(MainActivity.this,"已经保存数据2",Toast.LENGTH_SHORT).show();
                break;
            case R.id.getDefaultSharedPreferences:
                SharedPreferences sharedPreferences2= PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                SharedPreferences.Editor edit2 = sharedPreferences2.edit();
                edit2.putString("姓名","t鞥寻");
                edit2.putInt("年龄",40);
                edit2.putBoolean("married", true);
                edit2.apply();
                Toast.makeText(MainActivity.this,"已经保存数据3",Toast.LENGTH_SHORT).show();
                break;
            case R.id.getData:
                String name=sharedPreferences.getString("姓名","1000");
                int age=sharedPreferences.getInt("年龄",20);
                boolean bool=sharedPreferences.getBoolean("married", true);
                Log.d("MainActivity", "name is " + name);
                Log.d("MainActivity", "age is " + age);
                Log.d("MainActivity", "married is " + bool);
                break;
            default:
                break;
        }
    }
}
