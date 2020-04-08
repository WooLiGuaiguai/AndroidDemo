package com.example.okhttp_xmlpull;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=(Button)findViewById(R.id.button);
        textView=(TextView)findViewById(R.id.text_view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //创建一个OkHttpClient实例
                            OkHttpClient client=new OkHttpClient();
                            //1.发送Http请求 想要GET 创建一个Request对象
                            Request request=new Request.Builder()
                                    .url("http://192.168.1.103:80/get_data.xml")//设置网络目标地址
                                    .build();
                            //2.发送Http请求 想要POST
                            //构建一个RequestBody对象存放要提交的参数
                            RequestBody requestbody=new FormBody.Builder()
                                    .add("username","admin")
                                    .add("password","123456")
                                    .build();
                            //然后向请求中传入该对象
                            Request request1=new Request.Builder()
                                    .url("http://www.baidu.com")//设置网络目标地址
                                    .post(requestbody)
                                    .build();
                            //调用OkkHttpClient的newcall方法创建一个call对象
                            // 并调用它的execute()方法来发送请求并获取服务器返回的数据
                            Response response = client.newCall(request).execute();
                            //得到返回数据的具体内容
                            String responseDate = response.body().string();
                            //parseXMLWithPull(responseDate);
                            parseXMLWithSAX(responseDate);
                        }catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        Button button1=(Button)findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            OkHttpClient client = new OkHttpClient();
                            Request request = new Request.Builder()
                                 // 指定访问的服务器地址是电脑本机
                                    .url("http://192.168.1.103:80/get_data.json")
                                    .build();
                            Response response = client.newCall(request).execute();
                            String responseData = response.body().string();
                            parseJSONWithJSONObject(responseData);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        Button button2=(Button)findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            OkHttpClient okHttpClient=new OkHttpClient();
                            Request request=new Request.Builder()
                                    .url("http://192.168.1.103:80/get_data1.json")
                                    .build();
                            Response response=okHttpClient.newCall(request).execute();
                            String responseData=response.body().string();
                            parseJSONWithGSON(responseData);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
    private void parseJSONWithGSON(String jsonData){
        Gson gson=new Gson();
        //对于单个对象  直接将JSON数据解析成App对象
        //App app=gson.fromJson(jsonData,App.class);
        //对于一组对象   借助TypeToken将期望解析成的数据类型传入到fromJson() 方法中
        List<App> appList = gson.fromJson(jsonData, new TypeToken<List<App>>()
        {}.getType());
        for (App app : appList) {
            Log.d("MainActivity", "id is " + app.getId());
            Log.d("MainActivity", "name is " + app.getName());
            Log.d("MainActivity", "version is " + app.getVersion());
        }
    }
    private void parseJSONWithJSONObject(String jsonData) {
        try {//先把服务器返回的数据传入得到一个JSONArray对象中
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String version = jsonObject.getString("version");
                Log.d("MainActivity", "id is " + id);
                Log.d("MainActivity", "name is " + name);
                Log.d("MainActivity", "version is " + version);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private  void parseXMLWithSAX(String xmlDate){
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            XMLReader xmlReader= saxParserFactory.newSAXParser().getXMLReader();
            ContentHandler contentHandler=new ContentHandler();
            xmlReader.setContentHandler(contentHandler);
            xmlReader.parse(new InputSource(new StringReader(xmlDate)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showResponse(final String response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // 在这里进行UI操作， 将结果显示到界面上
                textView.setText(response);
            }
        });
    }
    private void parseXMLWithPull(String xmlData) {
        try {
            //首先获取工厂类实例
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            //然后通过实例获得XmlPullParser对象
            XmlPullParser xmlPullParser = factory.newPullParser();
            //将 获得数据传入解析对象进行解析
            xmlPullParser.setInput(new StringReader(xmlData));
            //得到当前的解析事件
            int eventType = xmlPullParser.getEventType();
            String id = "";
            String name = "";
            String version = "";
            while (eventType != XmlPullParser.END_DOCUMENT) {
                //获得当前结点的名字
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    // 开始解析某个节点
                    case XmlPullParser.START_TAG:
                        if ("id".equals(nodeName)) {
                            id = xmlPullParser.nextText();
                        } else if ("name".equals(nodeName)) {
                            name = xmlPullParser.nextText();
                        } else if ("version".equals(nodeName)) {
                            version = xmlPullParser.nextText();
                        }
                        break;
                    // 完成解析某个节点
                    case XmlPullParser.END_TAG:
                        if ("app".equals(nodeName)) {
                            Log.d("MainActivity", "id is " + id);
                            Log.d("MainActivity", "name is " + name);
                            Log.d("MainActivity", "version is " + version);
                        }
                        break;
                    default:break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

