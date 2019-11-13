package com.bwei.base.bean;

import java.util.List;

public class Products {

    /**
     * data : [{"id":"1","name":"新手福利计划","money":"10","yearRate":"8.00","suodingDays":"30","minTouMoney":"100","memberNum":"100","progress":"50"},{"id":"2","name":"财神道90天计划","money":"20","yearRate":"8.00","suodingDays":"40","minTouMoney":"1000","memberNum":"100","progress":"60"},{"id":"3","name":"月月升理财计划","money":"200","yearRate":"11.00","suodingDays":"100","minTouMoney":"10000","memberNum":"400","progress":"90"},{"id":"4","name":"180天理财计划(加息5%)","money":"40","yearRate":"15.00","suodingDays":"80","minTouMoney":"10000","memberNum":"200","progress":"30"},{"id":"5","name":"中情局投资商业经营","money":"500","yearRate":"10.00","suodingDays":"150","minTouMoney":"50000","memberNum":"150","progress":"40"},{"id":"6","name":"中学老师购买车辆","money":"10","yearRate":"8.00","suodingDays":"60","minTouMoney":"5000","memberNum":"60","progress":"40"},{"id":"7","name":"屌丝下海经商计划","money":"20","yearRate":"13.00","suodingDays":"120","minTouMoney":"10000","memberNum":"80","progress":"90"},{"id":"8","name":"美人鱼影视拍摄投资","money":"500","yearRate":"14.00","suodingDays":"100","minTouMoney":"2000","memberNum":"1000","progress":"80"},{"id":"9","name":"android老师自己周转","money":"5","yearRate":"8.00","suodingDays":"90","minTouMoney":"1000","memberNum":"100","progress":"40"},{"id":"10","name":"摩托罗拉洗钱计划","money":"1000","yearRate":"10.00","suodingDays":"200","minTouMoney":"100000","memberNum":"1000","progress":"60"}]
     * success : true
     */

    public boolean success;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 1
         * name : 新手福利计划
         * money : 10
         * yearRate : 8.00
         * suodingDays : 30
         * minTouMoney : 100
         * memberNum : 100
         * progress : 50
         */

        public String id;
        public String name;
        public String money;
        public String yearRate;
        public String suodingDays;
        public String minTouMoney;
        public String memberNum;
        public String progress;
    }
}
