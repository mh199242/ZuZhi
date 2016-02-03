package com.zuzhi.tianyou;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.annotation.NonNull;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.DataCleanManager;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MyApplication extends Application {
    //debug mode or release mode 是否debug模式
    public static boolean b_Debug = true;
    //Gson
    public static Gson gson;
    //Volley请求队列
    public static RequestQueue mVolleyQueue;
    //    //用户信息bean
//    public static UserBean user;
    //图片显示设置
    public static DisplayImageOptions dis_ImgOptions;


    // /data/data/[packagename]/cache目录，存放一些其他缓存 File cache = getCacheDir();
    public static String CACHE = "";

    //  /data/data/[packagename]/databases，存放数据库
    public static String DATABASES = "";

    //  /data/data/[packagename]/lib，应用的so目录
    public static String LIB = "";

    // /data/data/[packagename]/shared_prefs 应用的SharedPreferences保存
    public static String SHARED_PREFS = "";

    //single application
    private static MyApplication instance;

    //saved activtys 已保存的activity ，主要针对singleInstance模式
    private List<Activity> activitys = null;

    public static MyApplication getInstance() {

        return instance;
    }


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        activitys = new LinkedList<Activity>();
        instance = this;
        initImageLoader();
        readUserInfo();

        //初始化Gson
        gson = new Gson();
        //init a volley request queue 初始化一个volley请求队列
        mVolleyQueue = Volley.newRequestQueue(this);

        CACHE = "/data/data/" + getApplicationContext().getPackageName() + "/cache";
        DATABASES = "/data/data/" + getApplicationContext().getPackageName() + "/databases";
        LIB = "/data/data/" + getApplicationContext().getPackageName() + "/lib";
//        SHARED_PREFS = "/data/data/" + getApplicationContext().getPackageName() + "/shared_prefs";


    }

    /**
     * 检测当前缓存值
     */
    public static String checkCache() {
        try {
            long size = DataCleanManager.getFolderSize(new File(CACHE)) +
                    DataCleanManager.getFolderSize(new File(DATABASES)) +
                    DataCleanManager.getFolderSize(new File(LIB)) +
                    DataCleanManager.getFolderSize(new File(SHARED_PREFS)) +
                    DataCleanManager.getFolderSize(StorageUtils.getOwnCacheDirectory(instance, Cons.CACHE_IMAGE_DIR));
            return DataCleanManager.getFormatSize(size);

        } catch (Exception e) {

            e.printStackTrace();
        return "检测错误";
        }
    }

    /**
     * 清除缓存
     */

    public static boolean clearCache() {
        DataCleanManager.deleteDir(new File(CACHE));
        DataCleanManager.deleteDir(new File(DATABASES));
        DataCleanManager.deleteDir(new File(LIB));
       DataCleanManager.deleteDir(new File(SHARED_PREFS));
       return DataCleanManager.deleteDir(StorageUtils.getOwnCacheDirectory(instance, Cons.CACHE_IMAGE_DIR));
    }

    private void readUserInfo() {
        // TODO Auto-generated method stub
//        user = new UserBean();
//        SharedPreferences sp = SharepreUtil.getInstant(getApplicationContext());
//        user.setId(sp.getString("id", "0"));
//        user.setFace(sp.getString("face", ""));
//        user.setJifen(sp.getInt("jifen", 0));
//        user.setMoney(sp.getFloat("money", 0));
//        user.setNickname(sp.getString("nickname", ""));
//        user.setPhone(sp.getString("phone", ""));
//        user.setUsername(sp.getString("username", ""));
    }

    /**
     * 更新用户信息
     */
    public static void updataUserInfo(Context context) {

//        Editor edit = SharepreUtil.getInstant(context).edit();
//        edit.putString("id", user.getId());
//        edit.putString("nickname", user.getNickname());
//        edit.putString("username", user.getUsername());
//        edit.putFloat("money", user.getMoney());
//        edit.putString("face", user.getFace());
//        edit.putInt("jifen", user.getJifen());
//        edit.putString("phone", user.getPhone());
//        edit.commit();

    }

    /**
     * 判断是否登录
     */
    public static boolean checkIsLogin(Context context) {
//        SharedPreferences sp = SharepreUtil.getInstant(context);
//        if (sp.getString("id", "0").equals("0")) {
//            return false;
//        } else {
        return true;
//        }
    }

    /**
     * 退出登陆--清空个人信息
     *
     * @param context
     */
    public static void clearUserInfo(Context context) {
//        user.setNickname("");
//        user.setJifen(0);
//        user.setMoney(0);
//        user.setPhone("");
//        user.setId("0");
//        user.setFace("");
//        user.setUsername("");
//        updataUserInfo(context);
    }

    private void initImageLoader() {
        // TODO Auto-generated method stub
        dis_ImgOptions = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.empty) // 设置图片下载期间显示的图片
//                .showImageForEmptyUri(R.drawable.ic_empty) // 设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.drawable.ic_error) // 设置图片加载或解码过程中发生错误显示的图片
                .resetViewBeforeLoading(false)  // default 设置图片在加载前是否重置、复位
//                .delayBeforeLoading(1000)  // 下载前的延迟时间
                .cacheInMemory(true) // default  设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // default  设置下载的图片是否缓存在SD卡中
//                .preProcessor(...)
//        .postProcessor(...)
//        .extraForDownloader(...)
                .considerExifParams(false) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.ARGB_8888) // default 设置图片的解码类型
//                .decodingOptions(...)  // 图片的解码设置
                .displayer(new SimpleBitmapDisplayer()) // default  还可以设置圆角图片new RoundedBitmapDisplayer(20)
                .handler(new Handler()) // default
                .build();

        File cacheDir = StorageUtils.getOwnCacheDirectory(
                getApplicationContext(), Cons.CACHE_IMAGE_DIR);
        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(
                getApplicationContext()).memoryCacheExtraOptions(480, 800)
                // 即保存的每个内存缓存文件的最大长宽
                .threadPoolSize(5)
                        // 线程池内加载的数量
                .diskCacheFileCount(100)
                        // 设置硬盘缓存的文件的最多个数
                .threadPriority(Thread.NORM_PRIORITY - 2)
                        // 配置线程优先级
                .denyCacheImageMultipleSizesInMemory()//.内存缓存

                .diskCache(new UnlimitedDiskCache(cacheDir)).build();//硬盘缓存路径

        ImageLoader.getInstance().init(configuration);
    }

    /**
     * 清除图片缓存
     */
    public void clearImageLoaderCachek() {
//        ImageLoader.getInstance().clearMemoryCache();  // 清除内存缓存
//        ImageLoader.getInstance().clearDiskCache();  // 清除本地缓存
//        ToastUtil.showLongToast(this, "清除本地缓存成功");
    }

    // 添加Activity到容器中
    public void addActivity(Activity activity) {
        if (activitys != null && activitys.size() > 0) {
            if (!activitys.contains(activity)) {
                activitys.add(activity);
            }
        } else {
            activitys.add(activity);
        }

    }

    // 遍历所有Activity并finish
    public void exit() {
        if (activitys != null && activitys.size() > 0) {
            for (Activity activity : activitys) {
                activity.finish();
            }
        }
        System.exit(0);
    }
}
