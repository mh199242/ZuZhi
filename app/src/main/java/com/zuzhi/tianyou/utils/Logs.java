package com.zuzhi.tianyou.utils;

import android.util.Log;

import com.zuzhi.tianyou.MyApplication;
import com.zuzhi.tianyou.activity.MapActivity;

/**
 * Created by 超 on 2016/1/26.
 */
public class Logs {

    /**
     * log 日志
     * @param tag owner 使用者
     * @param string information 信息
     */
    public static void i(String tag, String string) {
        if (MyApplication.b_Debug) {
            Log.i(tag, string);
        }
    }
}
