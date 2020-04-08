package com.example.litepaldemo;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.stetho.Stetho;

import org.litepal.LitePal;
import org.litepal.crud.LitePalSupport;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        LitePal.initialize(this);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.initize);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=LitePal.getDatabase();
            }
        });
        Button button1=(Button)findViewById(R.id.add);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=LitePal.getDatabase();
                Album album=new Album();
                album.setName("album");
                album.setPrice(10.99f);
                album.save();//save函数是从LitePalSupport继承而来
                Song song1 = new Song();
                song1.setName("song1");
                song1.setDuration(320);
                song1.setAlbum(album);
                song1.save();
                Song song2 = new Song();
                song2.setName("song2");
                song2.setDuration(356);
                song2.setAlbum(album);
                song2.save();

            }
        });
        Button button2=(Button)findViewById(R.id.delete);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LitePal.delete(Album.class,31);
                LitePal.deleteAll(Song.class,"name=?","alibaba");
                LitePal.deleteAll(Album.class,"name=?","album");
            }
        });
        Button button3=(Button)findViewById(R.id.update);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Album album=new Album();
                album.setName("album3");
                album.setPrice(2019);
                album.save();//save函数是从LitePalSupport继承而来
                Song song = new Song();
                song.setName("song3");
                song.setDuration(730);
                song.setAlbum(album);
                song.save();
                song.setName("alibaba");
                song.updateAll();//设置名字   然后对所有的song对象进行保存操作
                song.setToDefault("name");
                song.update(2);//将名字设置为默认值 对id为2的对象进行保存

            }
        });
        Button button4=(Button)findViewById(R.id.query);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //方法一 查询所有的Album对象
                List<Album> all = LitePal.findAll(Album.class);
                for (Album album : all) {
                    Log.d("MainActivity", " name is " + album.getName());
                }
                //方法二 查询第三个song对象
                Song song = LitePal.find(Song.class, 88);
                Log.d("MainActivity", " name is " + song.getName());
                //方法三  查询API 查询第一条数据
                Album album=LitePal.findFirst(Album.class);
                //方法四  连缀查询
                //order() 方法用于指定结果的排序方式，limit() 方法用于指定查询结果的数量，
                //offset() 方法用于指定查询结果的偏移量
                List<Song> songs = LitePal.select("name","id","album_id")
                        .where("name like ? and duration < ?", "song%", "200")
                        .order("duration dsc").limit(5).offset(3).find(Song.class);
                //方法五 原生态查找
                Cursor c = LitePal.findBySQL("select * from Album where  price = ?", "2019");
            }
        });

    }
}
