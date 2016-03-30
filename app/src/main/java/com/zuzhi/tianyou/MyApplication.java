package com.zuzhi.tianyou;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Handler;
import android.support.multidex.MultiDexApplication;

import com.easemob.chat.EMChat;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestQueue;
import com.zuzhi.tianyou.entity.UserEntity;
import com.zuzhi.tianyou.im.DemoHelper;
import com.zuzhi.tianyou.utils.Cons;
import com.zuzhi.tianyou.utils.DataCleanManager;
import com.zuzhi.tianyou.utils.SharepreUtil;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class MyApplication extends MultiDexApplication {
    //wechart api
    public IWXAPI wechat;

    //debug mode or release mode 是否debug模式
    public static boolean b_Debug = true;
    //Gson
    public static Gson gson;

    //    //用户信息bean
    public static UserEntity user;
    //图片显示设置
    public static DisplayImageOptions dis_ImgOptions;

    // 创建请求队列
    public RequestQueue queue;


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

    /**
     * 当前用户nickname,为了苹果推送不是userid而是昵称
     */
    public static String currentUserNick = "";

    public static MyApplication getInstance() {

        return instance;
    }

    /**
     * regist app to wechat 注册应用到微信
     */
    public void regToWx() {
        //通过WXAPIFactory工厂，获取IWXAPI的实例
        wechat = WXAPIFactory.createWXAPI(this, Cons.WECHAT_APPID, false);

        //将应用注册到微信
        wechat.registerApp(Cons.WECHAT_APPID);
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        instance = this;
        initImageLoader();
        readUserInfo();

        //初始化Gson
        gson = new Gson();

        //init a NoHttp request queue 初始化一个NoHttp请求队列
        NoHttp.init(this);
        queue = NoHttp.newRequestQueue();

        CACHE = "/data/data/" + getApplicationContext().getPackageName() + "/cache";
        DATABASES = "/data/data/" + getApplicationContext().getPackageName() + "/databases";
        LIB = "/data/data/" + getApplicationContext().getPackageName() + "/lib";
        SHARED_PREFS = "/data/data/" + getApplicationContext().getPackageName() + "/shared_prefs";

        //init easemob SDK 初始化环信SDK
        //init demo helper
        DemoHelper.getInstance().init(this);

        /**
         * debugMode == true 时为打开，sdk 会在log里输入调试信息
         * @param debugMode
         * 在做代码混淆的时候需要设置成false
         */
        EMChat.getInstance().setDebugMode(true);//在做打包混淆时，要关闭debug模式，避免消耗不必要的资源

        regToWx();
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
        user = new UserEntity();
        SharedPreferences sp = SharepreUtil.getInstant(getApplicationContext());
        user.setId(sp.getLong("id", 0));
        user.setPhone(sp.getString("phone", ""));
        user.setHeadImg(sp.getString("headImg", ""));
        user.setName(sp.getString("name", ""));
        user.setHasPhone(sp.getString("hasPhone", ""));
        user.setWorkId(sp.getLong("workId", 0));
        user.setCompanyName(sp.getString("companyName", ""));
        user.setType(sp.getInt("type", 0));
    }

    /**
     * 更新用户信息
     */
    public static void updataUserInfo(Context context) {

        SharedPreferences.Editor edit = SharepreUtil.getInstant(context).edit();

        edit.putLong("id", user.getId());
        edit.putString("phone", user.getPhone());
        edit.putString("headImg", user.getHeadImg());
        edit.putString("name", user.getName());
        edit.putString("hasPhone", user.getHasPhone());
        edit.putLong("workId", user.getWorkId());
        edit.putString("companyName", user.getCompanyName());
        edit.putInt("type", user.getType());
        edit.commit();

    }

    /**
     * 判断是否登录
     */
    public static boolean checkIsLogin(Context context) {
        SharedPreferences sp = SharepreUtil.getInstant(context);
        if (sp.getString("id", "0").equals("0")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 退出登陆--清空个人信息
     *
     * @param context
     */
    public static void clearUserInfo(Context context) {
        user.setId(0);
        user.setPhone("");
        user.setHeadImg("");
        user.setName("未登录");
        user.setHasPhone("");
        user.setWorkId(0);
        user.setCompanyName("");
        user.setType(0);
        updataUserInfo(context);
    }

    private void initImageLoader() {
        // TODO Auto-generated method stub
        dis_ImgOptions = new DisplayImageOptions.Builder()
//                .showImageOnLoading(R.drawable.empty) // 设置图片下载期间显示的图片
//                .showImageForEmptyUri(R.drawable.ic_empty) // 设置图片Uri为空或是错误的时候显示的图片
//                .showImageOnFail(R.drawable.ic_error) // 设置图片加载或解码过程中发生错误显示的图片
                .resetViewBeforeLoading(false)  // default 设置图片在加载前是否重置、复位
//                .delayBeforeLoading(1000)  // 下载前的延迟时间
                .cacheInMemory(false) // default  设置下载的图片是否缓存在内存中
                .cacheOnDisk(true) // default  设置下载的图片是否缓存在SD卡中
//                .preProcessor(...)
//        .postProcessor(...)
//        .extraForDownloader(...)
                .considerExifParams(false) // default
                .imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default 设置图片以如何的编码方式显示
                .bitmapConfig(Bitmap.Config.RGB_565) // default 设置图片的解码类型
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
     * clear image cache
     */
    public void clearImageLoaderCachek() {
//        ImageLoader.getInstance().clearMemoryCache();  // 清除内存缓存
//        ImageLoader.getInstance().clearDiskCache();  // 清除本地缓存
//        ToastUtil.showLongToast(this, "清除本地缓存成功");
    }


}
