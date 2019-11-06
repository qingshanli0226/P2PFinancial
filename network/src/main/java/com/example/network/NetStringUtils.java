package com.example.network;

public class NetStringUtils {
    //http://169.254.44.116:8080/P2PInvest/index
    public static final String IP="192.168.100.1";
    //169.254.44.116
    public static final String BASE_URL="http://"+ IP +":8080/P2PInvest/";
    public static final String PRODUCT = BASE_URL + "product";//访问“全部理财”产品

    public static final String LOGIN = BASE_URL + "login";//登录

    public static final String INDEX ="index";//访问“homeFragment”

    public static final String USERREGISTER = BASE_URL + "UserRegister";//访问“homeFragment”

    public static final String FEEDBACK = BASE_URL + "FeedBack";//注册

    public static final String UPDATE = BASE_URL + "update.json";//更新应用
}
