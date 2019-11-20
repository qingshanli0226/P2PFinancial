package jni.example.common;

public class Constant {
//    public static String BASE_URL = "http://www.qubaobei.com/ios/cf/";
    public static final boolean PRINT_LOG = true;

    public static final String MY_IP_ADDRESS = "192.168.100.1";
    public static final String WIFI_IP_ADDRESS = "192.168.62.1";

    public static final String IPADDRESS = "http://192.168.100.1:8080/P2PInvest/product";


    public static final String BASE_URL = "http://" + MY_IP_ADDRESS + ":8080/P2PInvest/";
//如下的IPADDRESS可直接访问尚硅谷后台的服务器及数据库，不用在本地安装tomcat及mysql数据库
//    public static final String IPADDRESS = "182.92.5.3";
//
//    public static final String BASE_URL = "http://" + IPADDRESS + ":8081/P2PInvest/";

    public static final String PRODUCT = "product";//访问“全部理财”产品

    public static final String LOGIN = "login";//登录

    public static final String INDEX = "index";//访问“homeFragment”

    public static final String USERREGISTER = BASE_URL + "UserRegister";//访问“homeFragment”

    public static final String FEEDBACK = "FeedBack";//注册

    public static final String UPDATE = "update.json";//更新应用

}
