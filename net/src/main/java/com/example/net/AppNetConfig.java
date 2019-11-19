package com.example.net;

/**
 * Created by shkstart on 2016/12/2 0002.
 * 配置网络请求相关的地址
 */
public class AppNetConfig {

    public static final String IPADDRESS = "169.254.44.116";

    public static final String BASE_URL = "http://" + IPADDRESS + ":8080/P2PInvest/";
//如下的IPADDRESS可直接访问尚硅谷后台的服务器及数据库，不用在本地安装tomcat及mysql数据库
//    public static final String IPADDRESS = "182.92.5.3";

//http://169.254.44.116:8080/P2PInvest/FeedBack
//    public static final String BASE_URL = "http://" + IPADDRESS + ":8081/P2PInvest/";

    public static final String PRODUCT =  "product";//访问“全部理财”产品

    public static final String LOGIN =  "login";//登录

    public static final String INDEX = "index";//访问“homeFragment”

    public static final String USERREGISTER = BASE_URL + "UserRegister";//访问“homeFragment”

    public static final String FEEDBACK = BASE_URL + "FeedBack";//注册

    public static final String UPDATE = BASE_URL + "update.json";//更新应用


}
