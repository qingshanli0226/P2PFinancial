package com.bw.common

class AppNetConfig {

    var IPADDRESS:String = "169.254.44.116"

    var BASE_URL:String = "http://$IPADDRESS:8080/P2PInvest/"

    //访问"全部理财"产品
    var PRODUCT:String = BASE_URL + "product"

    //登录
    var LOGIN:String = BASE_URL + "login"

    //访问"homeFragment"
    var INDEX:String = BASE_URL + "index"

    //访问"homeFragment"
    var USERREGISTER:String = BASE_URL + "UserRegister"

    //注册
    var FEEDBACK:String = BASE_URL + "FeedBack"

    //更新应用
    var UPDATE:String = BASE_URL + "update.json"


}