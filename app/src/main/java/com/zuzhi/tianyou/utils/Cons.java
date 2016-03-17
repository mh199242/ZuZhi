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
    //wechat sdk appid 微信SDK APPID
    public static final String WECHAT_APPID = "wx9a7ee3b009a54abe";

    //wechat sdk app secret 微信SDK SECRET
    public static final String WECHAT_SECRET = "17be9786ed83131bfd2fc22ba5521495";

    //IndexFragment tag
    public static final String FRAMENT_INDEX = "IndexFragment";

    //CommodityInfoActivity tag
    public static final String ACTIVITY_COMMODITYINFO = "CommodityInfoActivity";
    //IndexFragment tag 首页碎片标志
    public static final String TAG_FRAGMENT_INDEX = "index";
    //ClassFragment tag 类目碎片标志
    public static final String TAG_FRAGMENT_CLASS = "class";

    //域名
    public final static String DOMAIN = "http://101.201.208.117:8080/";

    //image host address
    public static String IMG_HOST = "";
    //域名
//    public final static String DOMAIN = "http://192.168.1.136:9080/";


    //发送验证码接口
    public final static String IDENTIFYING_CODE = "sendYzm";

    //注册接口
    public final static String REGIST = "reg";

    //找回密码
    public final static String FIND_PASSWORD = "restPassWord";

    //登陆接口
    public final static String LOGIN = "login";

    //get profession interface
    public final static String GET_PROFESSION = "dict";

    //bind cellphone interface
    public final static String BIND_CELLPHONE = "bindPhone";

    //index interface
    public final static String INDEX = "index";

    //index shop details
    public final static String SHOP_DETAILS = "shop";

    //class list interface
    public final static String CLASS_LIST = "itemindex";

    //item interface
    public final static String ITEM = "item";

    //item details interface
    public final static String ITEM_DETAIL = "itemdetail";

    //collection interface
    public final static String COLLECTION = "userCollection";

    //uncollection interface
    public final static String UNCOLLECTION = "escCollection";


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
            R.drawable.temp_vp_master3,
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


    /**
     * test data of masters head 专家团头像测试数据
     */
    public static int[] IDARR_MASTERS_HEAD = new int[]{
            R.drawable.temp_vp_master1, R.drawable.temp_vp_master2, R.drawable.temp_vp_master3,
            R.drawable.temp_vp_master1, R.drawable.temp_vp_master1, R.drawable.temp_vp_master1,
            R.drawable.temp_vp_master1, R.drawable.temp_vp_master1, R.drawable.temp_vp_master1};

    /**
     * test data of masters name 专家团姓名测试数据
     */
    public static String[] STRARR_MASTERS_NAME = new String[]{
            "张三",
            "李四",
            "王五",
            "赵六"};

    /**
     * test data of masters exp 专家团经验测试数据
     */
    public static String[] STRARR_MASTERS_EXP = new String[]{
            "9年经验",
            "7年经验",
            "3年经验",
            "4年经验"};

    /**
     * test data of masters good at1 专家团擅长领域1测试数据
     */
    public static String[] STRARR_MASTERS_GODD_AT1 = new String[]{
            "企业上市",
            "财会统计",
            "风险评估",
            "企业税务",
            "风险评估",
            "企业税务",
            "企业上市",
            "财会统计",
            "风险评估",
            "企业税务",
            "风险评估",
            "企业税务"};

    /**
     * test data of masters good at1 专家团擅长领域2测试数据
     */
    public static String[] STRARR_MASTERS_GODD_AT2 = new String[]{
            "财会统计",
            "风险评估",
            "企业税务",
            "企业上市",
            "财会统计",
            "风险评估",
            "企业税务",
            "企业上市"};

    /**
     * test data of masters good evaluate percent 专家团好评率测试数据
     */
    public static String[] STRARR_MASTERS_GODD_EVALUATE_PERCENT = new String[]{
            "74%",
            "98%",
            "90%",
            "94%"};

    /**
     * test data of masters completer number 专家团完成数量测试数据
     */
    public static String[] STRARR_MASTERS_COMPLETE_NUMBER = new String[]{
            "874单",
            "74单",
            "84单",
            "254单"};

    /**
     * test data of masters evaluate number 专家团被评价数量测试数据
     */
    public static String[] STRARR_MASTERS_EVALUATE_NUMBER = new String[]{
            "78",
            "345",
            "346",
            "345"};


    /**
     * test data of order status 订单进行中状态测试数据
     */
    public static String[] STRARR_ORDER_PROCESSING_STATUS = new String[]{
            "待支付",
            "商家服务中",
            "待支付",
            "商家服务中",
            "商家服务中",
            "商家服务中"
    };

    /**
     * test data of order status 订单完成状态测试数据
     */
    public static String[] STRARR_ORDER_COMPLETED_STATUS = new String[]{
            "交易成功",
            "交易成功",
            "交易成功",
            "交易成功",
            "交易成功",
            "交易成功"
    };

    /**
     * test data of order status 订单评论状态测试数据
     */
    public static int[] INTARR_ORDER_EVALUATE_STATUS = new int[]{
            1,
            0,
            0,
            1,
            0,
            1
    };

    /**
     * test data of company name 订单服务商名称测试数据
     */
    public static String[] STRARR_ORDER_COMPANY_NAME = new String[]{
            "永大会计事务所",
            "永中会计事务所",
            "永小会计事务所",
            "永稍大会计事务所",
            "永稍小会计事务所",
            "永不大不小会计事务所",};

    /**
     * test data of order total price 订单合计价格测试数据
     */
    public static String[] STRARR_ORDER_TOTAL_PRICE = new String[]{
            "￥2000.0",
            "￥2000.0",
            "￥2330.0",
            "￥1000.0",
            "￥2000.0",
            "￥2303.0"};

    /**
     * test data of order status 订单状态测试数据
     */
    public static String[] STRARR_ORDER_STATUS = new String[]{
            "您已确认服务完成",
            "项目已延期",
            "服务商申请延期",
            "服务商进行服务中",
            "订单已生成"};

    /**
     * test data of order date 订单日期测试数据
     */
    public static String[] STRARR_ORDER_DATE = new String[]{
            "2016-02-20",
            "2016-02-19",
            "2016-02-18",
            "2016-02-17",
            "2016-02-16"};

    /**
     * test data of order time 订单时间测试数据
     */
    public static String[] STRARR_ORDER_TIME = new String[]{
            "13:32:23",
            "13:32:22",
            "13:32:21",
            "13:32:10",
            "13:32:23"};

    /**
     * test data of order color 订单颜色测试数据
     */
    public static int[] IDARR_ORDER_COLOR = new int[]{
            R.color.color_text_status_green,
            R.color.color_text_status_red,
            R.color.color_text_status_red,
            R.color.color_text_status_gray,
            R.color.color_text_status_gray};

}
