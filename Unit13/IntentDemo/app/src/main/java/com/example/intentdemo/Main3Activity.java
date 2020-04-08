package com.example.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Person person=(Person)getIntent().getSerializableExtra("person_data");
        Log.d("Main3Activity",person.getName()+" "+person.getSex()+" "+person.getAge());
    }
}
