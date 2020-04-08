package com.example.threaddemo_asynctask;

import android.os.AsyncTask;
//AsyncTask是一个抽象类，创建一个子类去继承它。
//在继承时为AsyncTask类指定3个泛型参数， 这3个参数的用途如下
//Params   在执行AsyncTask时需要传入的参数， 可用于在后台任务中使用。
//Progress 后台任务执行时，如果需要在界面上显示当前的进度，则使用这里指定的泛型作为进度单位
//Result   当任务执行完毕后，如果需要对结果进行返回，使用这里指定的泛型作为返回值类型。
public class ChangeTask extends AsyncTask <Void,Integer,Boolean>{
    //重写函数 完成任务编写
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
    }
}
