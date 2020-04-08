package com.example.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(this);
        Button button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(this);
        Button button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(this);
        Button button4=(Button)findViewById(R.id.button4);
        button4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button1:
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("name","迪丽热巴");
                intent.putExtra("sex","woman");
                intent.putExtra("age","20");
                startActivity(intent);
                break;
            case R.id.button2:
                Person person=new Person();
                person.setAge(25);
                person.setName("古力娜扎");
                person.setSex("女");
                Intent intent1=new Intent(MainActivity.this,Main3Activity.class);
                //intent1.putExtra("person_data",person);
                startActivity(intent1);
                break;
            case R.id.button3:
                Person person1=new Person();
                person1.setAge(39);
                person1.setName("海瑟薇");
                person1.setSex("女");
                Intent intent2=new Intent(MainActivity.this,Main4Activity.class);
                intent2.putExtra("person_data",person1);
                startActivity(intent2);
                break;
            case R.id.button4:
                Intent intent3=new Intent(MainActivity.this,MyService.class);
                MainActivity.this.startService(intent3);
                break;
            default:
                break;
        }
    }
}
