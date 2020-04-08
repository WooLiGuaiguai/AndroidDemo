package Fruit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.listviewtest.R;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {
    private int resourceId;
    public FruitAdapter(Context context, int textViewResourceid, List<Fruit> objs){
        super(context,textViewResourceid,objs);
        resourceId = textViewResourceid;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Fruit fruit = getItem(position); // 获取当前项的Fruit实例
        View view;
        ViewHolder viewHolder;
        //getView() 方法中还有一个convertView 参数， 这个参数用于将之前加载好的布局进行缓存， 以便之后可以进行重用。
        if(convertView==null){
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
                //每次在getView() 方法中还是会调用View 的findViewById() 方法来获取一次控件的实例 于是优化
            viewHolder=new ViewHolder();
            viewHolder.fruitImage = (ImageView) view.findViewById(R.id.fruit_id);
            viewHolder.fruitName = (TextView) view.findViewById(R.id.fruit_name);
            view.setTag(viewHolder);//// 将ViewHolder存储在View中
        }else{
            view=convertView;
            viewHolder=(ViewHolder) view.getTag();
        }
        viewHolder.fruitImage.setImageResource(fruit.getImageId());
        viewHolder.fruitName.setText(fruit.getName());
        return view;
    }
    class ViewHolder{
        ImageView fruitImage;
        TextView fruitName;
    }
}
