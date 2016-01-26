package com.zuzhi.tianyou.utils;

import com.zuzhi.tianyou.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常量类
 * Created by lichao on 2015/12/16.
 */
public class Cons {
    public static final String FRAMENT_INDEX = "IndexFragment";//index fragment 首页碎片
    public static final int FRAMENT_GETMONNEY = 4;//赚钱碎片
    public static final int FRAMENT_MODULEDESIGN = 7;//模块设计碎片

    public static final int RESULT_LOGIN_ACTIVITY = 2;//登录页面返回码
    public static final int RESULT_REGIST_ACTIVITY = 3;//注册页面标志

    public static final int ACTIVITY_BANKFANANCING = 4;//银行理财页面
    public static final int ACTIVITY_ZHOUBIANSHANGHU = 5;//周边商户页面
    public static final int ACTIVITY_SERVICELIST = 6;//服务列表页面
    public static final int ACTIVITY_HOUSERENT = 7;//服务列表页面
    public static final int ACTIVITY_TOUR = 8;//周边旅游页面

    public static final int REQUEST_DEAFAULT = 100;
    public static final int REQUEST_GETMONNEY = 101;//赚钱请求码
    public static final int REQUEST_SETNICKNAME = 102;//昵称设置请求码

    public static final String TAG_FRAGMENT_MAIN_INDEX = "index";//首页标志
    public static final String TAG_FRAGMENT_MAIN_MYCOMMUNITY = "mycommunity";//我的社区标志
    public static final String TAG_FRAGMENT_MAIN_EDIANZHUAN = "edianzhuan";//e点赚标志
    public static final String TAG_FRAGMENT_MAIN_MY = "my";//我的标志
    public static final String TAG_FRAGMENT_GETMONNEY = "getmonney";//赚钱标志
    public static final String TAG_FRAGMENT_MODULEDESIGN = "moduledesign";//模块定制标志

    public static final String TAG_RESULT_NICKNAME_ACTIVITY = "result_nickname_activity";//昵称页面返回标志


    //域名
    public final static String DOMAIN = "http://mysqapp.qweweq.com/index.php/app/index/";

    //注册发送验证码接口
    public final static String IDENTIFYING_CODE_REGIST = "reginsertYzm";

    //注册接口
    public final static String REGIST = "reginsert";

    //找回密码接口
    public final static String FIND_PASSWORD = "Member/resetpass";

    //找回密码发送验证码接口
    public final static String IDENTIFYING_CODE_FIND_PASSWORD = "Member/resetYzm";

    //登陆接口
    public final static String LOGIN = "login";

    //修改头像接口
    public final static String CHANGE_HEAD = "Member/changeavatar";

    //修改密码接口
    public final static String CHANGE_PASSWORD = "Member/changepass";

    //意见反馈接口
    public final static String SUGGESTION_POST = "Member/feedback";

    //修改昵称接口
    public final static String CHANGE_NICKNAME = "Member/changecnname";

    //每日签到接口
    public final static String SIGN_IN = "Member/signin";

    //图片缓存目录
    public final static String CACHE_IMAGE_DIR = "ZuZhi/ImgCache";




    /**
     * 手机号验证
     *
     * @param str
     * @return 验证通过返回true
     */
    public static boolean isMobile(String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,8,7][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }

}