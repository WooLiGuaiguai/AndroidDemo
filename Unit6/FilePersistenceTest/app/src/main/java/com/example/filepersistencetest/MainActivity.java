package com.example.filepersistencetest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText=(EditText) findViewById(R.id.edit_1);

        String inputText = load();
        if (!TextUtils.isEmpty(inputText)) {
            editText.setText(inputText);
            editText.setSelection(inputText.length());
            Toast.makeText(this, "Restoring succeeded", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        String input=editText.getText().toString();
        save(input);
    }
    private void save(String inputtext){
        FileOutputStream fos= null;
        BufferedWriter bw=null;
        try {
            fos=openFileOutput("输入的数据", Context.MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fos);
            bw=new BufferedWriter(outputStreamWriter);
            bw.write(inputtext);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(bw!=null){
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
    private String load(){
        FileInputStream fis=null;
        BufferedReader br=null;
        StringBuilder sb=new StringBuilder();
        try{
            fis=openFileInput("输入的数据");
            InputStreamReader isr=new InputStreamReader(fis);
            br=new BufferedReader(isr);
            String len=" ";
            while((len=br.readLine())!=null){
                sb.append(len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if(br!=null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
