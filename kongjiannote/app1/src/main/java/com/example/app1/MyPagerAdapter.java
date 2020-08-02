package com.example.app1;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class MyPagerAdapter extends PagerAdapter {
    private List<View>list;//存放每个页面的列表
    private List<String> titlelist;//存放标题的列表
    MyPagerAdapter(List<View>list,List<String> titlelist){
        this.list=list;
        this.titlelist=titlelist;
    }
    @Override
    public int getCount() {//返回页面的数量
        return list.size();
    }
    @Override//判断view是否来自于对象
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }

    @NonNull
    @Override//实例化一个页面
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        container.addView(list.get(position));
        return list.get(position);
    }
    @Override//销毁一个页面
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        container.removeView(list.get(position));
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
    }
}
