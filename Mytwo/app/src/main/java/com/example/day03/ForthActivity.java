package com.example.day03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ForthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forth);
        Button button=(Button)findViewById(R.id.button_6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int data=2500;
                Intent intent=getIntent();
                intent.putExtra("key",data);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
