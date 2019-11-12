package com.example.common;
/**
 * Created by shkstart on 2016/12/2 0002.
 * 配置网络请求相关的地址
 */
public class Constant {

    public static final boolean PRINT_LOG = true;

    public static final String IPADDRESS = "169.254.44.116";
//    public static final String IPADDRESS = "169.254.118.136";
//    public static final String IPADDRESS = "169.254.95.169";

    //错误地址，用于看加载页面
//    public static final String IPADDRESS = "169.254.174.111";

    public static final String BASE_URL = "http://" + IPADDRESS + ":8080/P2PInvest/";
//如下的IPADDRESS可直接访问尚硅谷后台的服务器及数据库，不用在本地安装tomcat及mysql数据库
//    public static final String IPADDRESS = "182.92.5.3";
////
//    public static final String BASE_URL = "http://" + IPADDRESS + ":8081/P2PInvest/";

    public static final String PRODUCT = "product";//访问“全部理财”产品

    public static final String LOGIN = "login";//登录

    public static final String INDEX = "index";//访问“homeFragment”

    public static final String USERREGISTER = "UserRegister";//访问“homeFragment”

    public static final String FEEDBACK = "FeedBack";//注册

    public static final String UPDATE = "update.json";//更新应用


}
