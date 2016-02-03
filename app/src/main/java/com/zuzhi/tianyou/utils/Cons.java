package com.zuzhi.tianyou.utils;

import android.graphics.drawable.Drawable;

import com.loc.r;
import com.zuzhi.tianyou.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 常量类
 * Created by lichao on 2015/12/16.
 */
public class Cons {
    //IndexFragment tag
    public static final String FRAMENT_INDEX = "IndexFragment";

    //CommodityInfoActivity tag
    public static final String ACTIVITY_COMMODITYINFO = "CommodityInfoActivity";
    //IndexFragment tag 首页碎片标志
    public static final String TAG_FRAGMENT_INDEX = "index";
    //ClassFragment tag 类目碎片标志
    public static final String TAG_FRAGMENT_CLASS = "class";

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

    /**
     * test data of index guide 首页导航测试数据
     */
    public static String[] STRARR_INDEX_GUIDE = new String[]{
            "商务代理", "鉴证业务", "策划咨询", "兼并上市", "小微企业", "中大企业",
            "上市公司", "自营业务"
    };

    public static int[] ID_DRAWABLE_INDEX_GUIDE = new int[]{
            R.drawable.home_module1, R.drawable.home_module2, R.drawable.home_module3,
            R.drawable.home_module4, R.drawable.home_module5, R.drawable.home_module6,
            R.drawable.home_module7, R.drawable.home_module8
    };

    /**
     * test data of index topic text 首页推荐类别
     */
    public static String[] STRARR_INDEX_TOPIC = new String[]{
            "鉴证报告",
            "顾问咨询",
            "上市服务",
            "商务代理",
            "金牌专家"
    };

    /**
     * test data of index topic title 首页推荐标题
     */
    public static String[][] STRARR_INDEX_TOPIC_TITLE = new String[][]{
            {"验资报告", "审计报告", "税审报告", "XX报告", "OO报告"},
            {"财会咨询", "税务咨询", "投资咨询", "XX咨询"},
            {"新三板挂牌", "IPO服务", "并购重组", "XX重组", "OO重组"},
            {"小微企业", "外资企业", "高新政府补贴", "XX企业", "OO企业"},
            {"李永", "陈斌", "卢汉", "李超"},

    };

    /**
     * test data of index topic info 首页推荐描述
     */
    public static String[][] STRARR_INDEX_TOPIC_INFO = new String[][]{
            {"财税服务省力省心", "财税服务省力省心", "财税服务省力省心", "财税服务省力省心", "财税服务省力省心"},
            {"高效开发", "财税服务省力省心", "财税服务省力省心", "高效开发"},
            {"财税服务省力省心", "财税服务省力省心", "财税服务省力省心", "财税服务省力省心", "财税服务省力省心"},
            {"高效开发", "财税服务省力省心", "财税服务省力省心", "财税服务省力省心", "财税服务省力省心"},
            {"资深财会专家", "财会协会会员", "海龟计算专家", "程序员鼓励师"},

    };

    /**
     * test data of index topic img 首页推荐图片
     */
    public static int[][] IDARR_INDEX_TOPIC_IMG = new int[][]{
            {R.drawable.temp_vp_report1, R.drawable.temp_vp_report2, R.drawable.temp_vp_report3,
                    R.drawable.temp_vp_report1, R.drawable.temp_vp_report2},
            {R.drawable.temp_vp_consult1, R.drawable.temp_vp_consult2, R.drawable.temp_vp_consult3,
                    R.drawable.temp_vp_consult1},
            {R.drawable.temp_vp_report1, R.drawable.temp_vp_report2, R.drawable.temp_vp_report3,
                    R.drawable.temp_vp_report1, R.drawable.temp_vp_report2},
            {R.drawable.temp_vp_consult1, R.drawable.temp_vp_consult2, R.drawable.temp_vp_consult3,
                    R.drawable.temp_vp_consult1, R.drawable.temp_vp_consult2},
            {R.drawable.temp_vp_master1, R.drawable.temp_vp_master2, R.drawable.temp_vp_master3,
                    R.drawable.temp_vp_master1},

    };

    /**
     * test data of index hot service img 首页热门服务图片
     */
    public static int[] IDARR_INDEX_HOT_SERVICE_IMG = new int[]{
            R.drawable.temp_icon1, R.drawable.temp_icon2, R.drawable.temp_icon3,
            R.drawable.temp_icon1, R.drawable.temp_icon2, R.drawable.temp_icon3};

    /**
     * test data of index hot service title 首页热门服务标题
     */
    public static String[] STRARR_INDEX_HOT_SERVICE_TITLE = new String[]{
            "大企业税务风险管理1",
            "大企业税务风险管理2",
            "大企业税务风险管理3",
            "大企业税务风险管理4",
            "大企业税务风险管理5",
            "大企业税务风险管理6"};

    /**
     * test data of index hot service info1 首页热门服务描述1
     */
    public static String[] STRARR_INDEX_HOT_SERVICE_INFO1 = new String[]{
            "由专家1提供服务",
            "由专家2提供服务",
            "由专家3提供服务",
            "由专家4提供服务",
            "由专家5提供服务",
            "由专家6提供服务"};

    /**
     * test data of index hot service info2 首页热门服务描述2
     */
    public static String[] STRARR_INDEX_HOT_SERVICE_INFO2 = new String[]{
            "10年经验",
            "5年经验",
            "3年经验",
            "2年经验",
            "3年经验",
            "5年经验"};

    /**
     * test data of index hot service price1 首页热门服务价格1
     */
    public static String[] STRARR_INDEX_HOT_SERVICE_PRICE1 = new String[]{
            "￥10000.0",
            "￥20000.0",
            "￥23330.0",
            "￥10000.0",
            "￥20000.0",
            "￥23303.0"};

    /**
     * test data of index hot service price2 首页热门服务价格2
     */
    public static String[] STRARR_INDEX_HOT_SERVICE_PRICE2 = new String[]{
            "￥42004.0",
            "￥410304.0",
            "￥32004.0",
            "￥62004.0",
            "￥52004.0",
            "￥89004.0"};


    /**
     * test data of index hot service attribute 首页热门服务属性
     */
    public static String[] STRARR_INDEX_HOT_SERVICE_ATTRIBUTE = new String[]{
            "限时",
            "普普通通",
            "减免",
            "限时",
            "普普通通",
            "减免"};

    /**
     * test data of evaluate head 评论头像测试数据
     */
    public static int[] IDARR_EVALUATE_HEAD = new int[]{
            R.drawable.temp_vp_master1,
            R.drawable.temp_vp_master2,
            R.drawable.temp_vp_master3
    };


    /**
     * test data of evaluate rating 评论评分
     */
    public static float[] RATINGARR_EVALUATE = new float[]{
            3.5f,
            4.5f,
            3.5f
    };

    /**
     * test data of evaluate name 评论姓名测试数据
     */
    public static String[] STRARR_EVALUATE_NAME = new String[]{
            "张亮",
            "李强",
            "王刚"};

    /**
     * test data of evaluate content 评论内容测试数据
     */
    public static String[] STRARR_EVALUATE_CONTENT = new String[]{
            "服务不错，下次还来",
            "差强人意，继续努力！",
            "一般一般"};

    /**
     * test data of evaluate date 评论时间测试数据
     */
    public static String[] STRARR_EVALUATE_DATE = new String[]{
            "2天前",
            "今天",
            "3小时前"};
}
