package com.example.intentdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class Main4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        Person person=(Person)getIntent().getParcelableExtra("person_data");
        Log.d("Main4Activity",person.getName()+" "+person.getSex()+" "+person.getAge());
    }
}
