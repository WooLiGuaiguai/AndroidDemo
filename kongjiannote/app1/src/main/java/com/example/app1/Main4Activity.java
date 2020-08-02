package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Main4Activity extends AppCompatActivity {
    private List<String> list;
    private ArrayAdapter<String> arrayAdapter;
    private Spinner spinner;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        textView=(TextView)findViewById(R.id.text);
        spinner=(Spinner)findViewById(R.id.spinner);
        //1.初始化数据
        list=new ArrayList<>();
        list.add("请选择年级");list.add("一年级");list.add("二年级");list.add("三年级");
        list.add("四年级");list.add("五年级");list.add("六年级");
        //2.适配器初始化，加载数据
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,list);
        //3.为适配器设置下拉列表的菜单样式
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //4.加载适配器
        spinner.setAdapter(arrayAdapter);
        //5.spinner设置监听事件
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String str = arrayAdapter.getItem(position);//list.get(position)
                textView.setText("您想选择的年级是："+str);
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


    }
}
