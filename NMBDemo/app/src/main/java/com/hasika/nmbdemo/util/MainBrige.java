package com.hasika.nmbdemo.util;

import android.content.Context;

import com.hasika.nmbdemo.Control.NetorFile;

/**
 * Created by HaSiKa on 2016/6/12.
 * 批量初始化控制类
 * 保存控制类的实例.
 */
public class MainBrige {
    private static NetorFile netorFile;
    private static Context context_;

    public static NetorFile getNetorFile() {
        if(netorFile == null && context_ != null)
            netorFile = new NetorFile(context_);
        return netorFile;
    }
    public static void init(Context context){
        context_ = context;
    }
}
