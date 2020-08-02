package com.example.app1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main3Activity extends AppCompatActivity {
    private ListView listView;
    private List<Map<String,Object>> dataList;
    private SimpleAdapter simpleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        listView=(ListView)findViewById(R.id.list1);
        //1.准备获取数据源
        dataList=new ArrayList<>();
        dataList=getDataList();
        //2.为适配器加载数据源  ListView每个item是自定义的  所以item要设置一个自定义xml文件
        simpleAdapter=new SimpleAdapter(this,dataList,R.layout.item,
                new String[]{"image","text"},new int[]{R.id.image,R.id.text});
        //3.加载适配器
        listView.setAdapter(simpleAdapter);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int state) {
                switch (state){
                    case SCROLL_STATE_FLING:
                        Log.d("Main3Activity","手指离开屏幕，由于惯性继续滑动");
                        Map<String,Object> map=new HashMap<>();
                        map.put("Image",R.mipmap.ic_launcher);
                        map.put("text","今天是个好日子");
                        dataList.add(map);
                        simpleAdapter.notifyDataSetChanged();
                        break;
                    case SCROLL_STATE_IDLE:
                        Log.d("Main3Activity","手指离开屏幕，界面停止滑动");
                        break;
                    case SCROLL_STATE_TOUCH_SCROLL:
                        Log.d("Main3Activity","手指没有离开屏幕，视图仍在滑动");
                        break;
                }
            }
            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
            }
        });
    }
    public List<Map<String,Object>> getDataList(){
        for(int i=0;i<20;i++){
            Map<String,Object> map=new HashMap<>();
            map.put("image",R.mipmap.ic_launcher);
            map.put("text","文件序号："+i);
            dataList.add(map);
        }
        return dataList;
    }

}
