package com.example.kongjianote1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.ToggleButton;

import java.io.BufferedReader;

public class MainActivity extends AppCompatActivity {

    private ArrayAdapter<String> arrayAdapter;
    private AutoCompleteTextView autoCompleteTextView;
    private MultiAutoCompleteTextView multiAutoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.tablebut1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
        Button button1=(Button)findViewById(R.id.tablebut2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main3Activity.class);
                startActivity(intent);
            }
        });
        Button button2=(Button)findViewById(R.id.viewstubbut);
        final ViewStub vs=(ViewStub)findViewById(R.id.viewstub);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vs.inflate();
            }
        });
        Button button3=(Button)findViewById(R.id.ConstraintLayoutBut) ;
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Main4Activity.class);
                startActivity(intent);
            }
        });
        String data[]={"hello","how","happy","hebei","hing"};
        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.auto);
        arrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,data);
        autoCompleteTextView.setAdapter(arrayAdapter);
        String data2[] = {"11111111@qq.com", "2222222@qq.com", "333333@qq.com", "44448@qq.com"};
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data2);
        multiAutoCompleteTextView = (MultiAutoCompleteTextView) findViewById(R.id.many_auto);
        multiAutoCompleteTextView.setAdapter(arrayAdapter);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        final ToggleButton toggleButton=(ToggleButton)findViewById(R.id.change_state);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                toggleButton.setChecked(isChecked);
                toggleButton.setBackgroundResource(isChecked?R.drawable.orange_bg:R.drawable.red_bg);
            }
        });

        final CheckBox checkBox1=(CheckBox)findViewById(R.id.morning_food);
        final CheckBox checkBox2=(CheckBox)findViewById(R.id.launch_food);
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    String str=checkBox1.getText().toString();
                    Log.d("morning",str);
                }
            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    String str=checkBox2.getText().toString();
                    Log.d("launch",str);
                }
            }
        });
        RadioGroup radioGroup=(RadioGroup)findViewById(R.id.RadioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.bu1:
                        Log.d("text","man");
                        break;
                    case R.id.bu2:
                        Log.d("text","woman");
                        break;
                    default:
                        break;
                }
            }
        });
    }


}
