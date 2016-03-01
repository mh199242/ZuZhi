package com.zuzhi.tianyou.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.WindowManager.BadTokenException;

public class DialogUtils {

    /**
     * 声明progress
     */
    private static ProgressDialog progressDialog;


    /**
     * the text of show 显示的消息
     */
    private static String str_msg = "加载中...";


    /**
     * get dialog message 获得对话框消息
     */
    public static String getMessage() {
        return str_msg;
    }

    /**
     * set dialog message 设置对话框消息
     */
    public static void setMessage(String msg) {
        str_msg = msg;
    }

    /**
     * 显示dialog
     */
    public static void showProgressDialog(Context context, String msg) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(msg);
        try {
            progressDialog.show();
        } catch (BadTokenException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * 显示dialog
     */
    public static void showProgressDialog(Context context) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
            progressDialog = null;
        }
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(str_msg);
        try {
            progressDialog.show();
        } catch (BadTokenException exception) {
            exception.printStackTrace();
        }
    }

    static Handler welHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            dismisDialog();
        }

    };

    /**
     * 取消dialog
     */
    public static void dismissProgressDialog() {
        // startDelay();
        dismisDialog();
    }

    /**
     * 设置延时
     */
    private static void startDelay() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                    Message message = new Message();
                    welHandler.sendMessage(message);//
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private static void dismisDialog() {
        if (null != progressDialog && progressDialog.isShowing() == true) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

}
