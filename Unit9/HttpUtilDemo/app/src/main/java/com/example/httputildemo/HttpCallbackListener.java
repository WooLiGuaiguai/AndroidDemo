package com.example.httputildemo;

public interface HttpCallbackListener {

    //java回调机制 保证HttpUtil子线程产生的数据可以返回
    //onFinish() 方法表示当服务器成功响应我们请求的时候调用 onFinish() 方法中的参数代表着服务器返回的数据
    //onError() 表示当进行网络操作出现错误的时候调用 onError() 方法中的参数记录着错误的详细信息。
    void onFinish(String response);
    void onError(Exception e);
}
