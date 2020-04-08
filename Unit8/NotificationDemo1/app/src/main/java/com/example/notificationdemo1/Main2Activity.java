package com.example.notificationdemo1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.FileProvider;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Picture;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main2Activity extends AppCompatActivity {

    private ImageView imageView;
    private Uri Image_uri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification=new NotificationCompat.Builder(this)
                .setContentTitle("这是一个通知")
                .setContentText("只有小图标标题 内容")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("uper.onCreate(savee);\n" +
                        "        setContentView(R.layout.activity_main2);\n" +
                        "        NotificationManager manager = (NotificationManager) " +
                        "getSystemService(NOTIFICATION_SERVICE);"))
                .setStyle(new NotificationCompat.BigPictureStyle().bigPicture
                        (BitmapFactory.decodeResource(getResources(), R.drawable.big_image)))
       //setPriority() 方法接收一个整型参数用于设置这条通知的重要程度
       // 一共有5个常量值可选：
       // PRIORITY_DEFAULT 表示默认的重要程度， 和不设置效果是一样的；
       //PRIORITY_MIN表示最低的重要程度，系统只会在特定的场景才显示这条通知，比如用户下拉状态栏的时
       // PRIORITY_LOW 表示较低的重要程度，系统可能会将这类通知缩小，或改变其显示的顺序，将其排在更重要的通知之后
       // PRIORITY_HIGH 表示较高的重要程度，系统可能会将这类通知放大，或改变其显示的顺序，将其排在比较靠前的位置；
       // PRIORITY_MAX 表示最高的重要程度， 这类通知消息必须要让用户立刻看到， 甚至需要用户做出响应操作。
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();
        manager.cancel(1);
        Button button=(Button) findViewById(R.id.button);
        imageView=(ImageView) findViewById(R.id.image);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                File outputImage=new File(getExternalCacheDir(),"output_image.jpg");
                try{
                    if(outputImage.exists()){
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(Build.VERSION.SDK_INT>=24){
                    Image_uri= FileProvider.getUriForFile(Main2Activity.this,"com.example.notificationdemo1.photo",outputImage);
                }else{
                    Image_uri=Uri.fromFile(outputImage);
                }
                Intent intent=new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT,Image_uri);
                startActivityForResult(intent,1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    try {// 将拍摄的照片显示出来
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(Image_uri));
                        imageView.setImageBitmap(bitmap);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                break;
        }
    }
}
