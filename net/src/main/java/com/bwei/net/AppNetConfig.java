package com.bwei.net;

import io.reactivex.schedulers.Schedulers;

/**
 * Created by shkstart on 2016/12/2 0002.
 * 配置网络请求相关的地址
 */
public class AppNetConfig {

    public static final String GIFURL="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1573139733221&di=ccccaf398c83eb586f615e149140201e&imgtype=0&src=http%3A%2F%2Fb-ssl.duitang.com%2Fuploads%2Fitem%2F201504%2F03%2F20150403H2136_Zcae8.thumb.224_0.gif";
    public static final String IPADDRESS = "169.254.118.136";

    public static final String BASE_URL = "http://" + IPADDRESS + ":8080/P2PInvest/";
//如下的IPADDRESS可直接访问尚硅谷后台的服务器及数据库，不用在本地安装tomcat及mysql数据库
//    public static final String IPADDRESS = "182.92.5.3";
//
//    public static final String BASE_URL = "http://" + IPADDRESS + ":8081/P2PInvest/";

    public static final String PRODUCT = "product";//访问“全部理财”产品

    public static final String LOGIN =  "login";//登录

    public static final String INDEX =  "index";//访问“homeFragment”

    public static final String USERREGISTER = "UserRegister";//访问“homeFragment”

    public static final String FEEDBACK = "FeedBack";//注册

    public static final String UPDATE = "update.json";//更新应用


}
