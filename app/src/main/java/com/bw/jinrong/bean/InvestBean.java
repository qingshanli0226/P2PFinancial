package com.bw.jinrong.bean;

import java.util.List;

public class InvestBean {

    /**
     * data : [{"id":"1","name":"鏂版墜绂忓埄璁\u2033垝","money":"10","yearRate":"8.00","suodingDays":"30","minTouMoney":"100","memberNum":"100","progress":"50"},{"id":"2","name":"璐㈢閬�90澶╄鍒�","money":"20","yearRate":"8.00","suodingDays":"40","minTouMoney":"1000","memberNum":"100","progress":"60"},{"id":"3","name":"鏈堟湀鍗囩悊璐㈣鍒�","money":"200","yearRate":"11.00","suodingDays":"100","minTouMoney":"10000","memberNum":"400","progress":"90"},{"id":"4","name":"180澶╃悊璐㈣鍒�(鍔犳伅5%)","money":"40","yearRate":"15.00","suodingDays":"80","minTouMoney":"10000","memberNum":"200","progress":"30"},{"id":"5","name":"涓儏灞\u20ac鎶曡祫鍟嗕笟缁忚惀","money":"500","yearRate":"10.00","suodingDays":"150","minTouMoney":"50000","memberNum":"150","progress":"40"},{"id":"6","name":"涓鑰佸笀璐拱杞﹁締","money":"10","yearRate":"8.00","suodingDays":"60","minTouMoney":"5000","memberNum":"60","progress":"40"},{"id":"7","name":"灞屼笣涓嬫捣缁忓晢璁\u2033垝","money":"20","yearRate":"13.00","suodingDays":"120","minTouMoney":"10000","memberNum":"80","progress":"90"},{"id":"8","name":"缇庝汉楸煎奖瑙嗘媿鎽勬姇璧�","money":"500","yearRate":"14.00","suodingDays":"100","minTouMoney":"2000","memberNum":"1000","progress":"80"},{"id":"9","name":"android鑰佸笀鑷繁鍛ㄨ浆","money":"5","yearRate":"8.00","suodingDays":"90","minTouMoney":"1000","memberNum":"100","progress":"40"},{"id":"10","name":"鎽╂墭缃楁媺娲楅挶璁\u2033垝","money":"1000","yearRate":"10.00","suodingDays":"200","minTouMoney":"100000","memberNum":"1000","progress":"60"}]
     * success : true
     */

    private boolean success;
    private List<DataBean> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * name : 鏂版墜绂忓埄璁″垝
         * money : 10
         * yearRate : 8.00
         * suodingDays : 30
         * minTouMoney : 100
         * memberNum : 100
         * progress : 50
         */

        private String id;
        private String name;
        private String money;
        private String yearRate;
        private String suodingDays;
        private String minTouMoney;
        private String memberNum;
        private String progress;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getYearRate() {
            return yearRate;
        }

        public void setYearRate(String yearRate) {
            this.yearRate = yearRate;
        }

        public String getSuodingDays() {
            return suodingDays;
        }

        public void setSuodingDays(String suodingDays) {
            this.suodingDays = suodingDays;
        }

        public String getMinTouMoney() {
            return minTouMoney;
        }

        public void setMinTouMoney(String minTouMoney) {
            this.minTouMoney = minTouMoney;
        }

        public String getMemberNum() {
            return memberNum;
        }

        public void setMemberNum(String memberNum) {
            this.memberNum = memberNum;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }
    }
}
