package com.example.databasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        setContentView(R.layout.activity_main);
        final MyDatabaseHelper myDatabaseHelper=new MyDatabaseHelper(MainActivity.this,"database",null,2);
        Button button=(Button)findViewById(R.id.create_database);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myDatabaseHelper.getWritableDatabase();
            }
        });
        Button button1=(Button)findViewById(R.id.add);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=myDatabaseHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                // 开始组装第一条数据
                values.put("name", "The Da Vinci Code");
                values.put("author", "Dan Brown");
                values.put("pages", 454);
                values.put("price", 16.96);
                db.insert("Book", null, values);// 插入第一条数据
                values.clear();
                // 开始组装第二条数据
                values.put("name", "The Lost Symbol");
                values.put("author", "Dan Brown");
                values.put("pages", 510);
                values.put("price", 19.95);
                db.insert("Book", null, values); // 插入第二条数据
                values.clear();
            }
        });
        Button button2=(Button)findViewById(R.id.update_data);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=myDatabaseHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                //修改数据
                values.put("price",20000);
                db.update("Book",values,"name = ?",new String[]{"The Lost Symbol"} );
            }
        });
        Button button3=(Button)findViewById(R.id.delete_data);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=myDatabaseHelper.getWritableDatabase();
                db.delete("Book","name = ?",new String[]{"The Lost Symbol"} );
            }
        });
        Button button4=(Button)findViewById(R.id.query_data);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db=myDatabaseHelper.getWritableDatabase();
                Cursor query = db.query("Book", null, "price=?",
                        new String[]{"20000"}, null, null, "pages");
                if (query.moveToFirst()) {
                    do {
                     // 遍历Cursor对象， 取出数据并打印
                        String name = query.getString(query.getColumnIndex("name"));
                        String author = query.getString(query.getColumnIndex("author"));
                        int pages = query.getInt(query.getColumnIndex("pages"));
                        double price = query.getDouble(query.getColumnIndex("price"));
                        Log.d("MainActivity", "book name is " + name);
                        Log.d("MainActivity", "book author is " + author);
                        Log.d("MainActivity", "book pages is " + pages);
                        Log.d("MainActivity", "book price is " + price);
                    } while (query.moveToNext());
                }
                query.close();
                /*db.rawQuery("select * from Book", null);
                db.execSQL("delete from Book where pages > ?", new String[] { "500" });
                db.execSQL("update Book set price = ? where name = ?",
                        new String[] { "10.99", "The Lost Symbol"});
                db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
                        new String[] { "The Lost Symbol", "Dan Brown", "510", "19.95" });*/
            }
        });
    }
}
