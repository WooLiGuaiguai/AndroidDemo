package com.example.servicebestpractice_asynctask;
//定义一个回调接口， 用于对下载过程中的各种状态进行监听和回调。
//onProgress() 方法用于通知当前的下载进度
//onSuccess() 方法用于通知下载成功事件
//onFailed() 方法用于通知下载失败事
//onPaused() 方法用于通知下载暂停事件
//onCanceled() 方法用于通知下载取消事件。
public interface DownloadListener {
    void onProgress(int progress);

    void onSuccess();

    void onFailed();

    void onPaused();

    void onCanceled();
}
