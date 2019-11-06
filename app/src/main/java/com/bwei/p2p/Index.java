package com.bwei.p2p;

import java.util.List;

/**
 * Created by shkstart on 2016/12/2 0002.
 */
public class Index {


    /**
     * imageArr : [{"ID":"1","IMAPAURL":"http://gwop.xtrich.com/xtApp/lexianghuo1.html","IMAURL":"http://169.254.118.136:8080/P2PInvest/images/index01.png"},{"ID":"2","IMAPAURL":"http://gwop.xtrich.com/xtApp/new-plan.html","IMAURL":"http://169.254.118.136:8080/P2PInvest/images/index02.png"},{"ID":"3","IMAPAURL":"http://gwop.xtrich.com/xtApp/new-plan.html","IMAURL":"http://169.254.118.136:8080/P2PInvest/images/index03.png"},{"ID":"5","IMAPAURL":"http://gwop.xtrich.com/xtApp/twcx.html","IMAURL":"http://169.254.118.136:8080/P2PInvest/images/index04.png"}]
     * proInfo : {"id":"1","memberNum":"100","minTouMoney":"100","money":"10","name":"硅谷彩虹新手计划","progress":"90","suodingDays":"30","yearRate":"8.00"}
     */

    public ProInfoBean proInfo;
    public List<ImageArrBean> imageArr;

    public static class ProInfoBean {
        /**
         * id : 1
         * memberNum : 100
         * minTouMoney : 100
         * money : 10
         * name : 硅谷彩虹新手计划
         * progress : 90
         * suodingDays : 30
         * yearRate : 8.00
         */

        public String id;
        public String memberNum;
        public String minTouMoney;
        public String money;
        public String name;
        public String progress;
        public String suodingDays;
        public String yearRate;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMemberNum() {
            return memberNum;
        }

        public void setMemberNum(String memberNum) {
            this.memberNum = memberNum;
        }

        public String getMinTouMoney() {
            return minTouMoney;
        }

        public void setMinTouMoney(String minTouMoney) {
            this.minTouMoney = minTouMoney;
        }

        public String getMoney() {
            return money;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProgress() {
            return progress;
        }

        public void setProgress(String progress) {
            this.progress = progress;
        }

        public String getSuodingDays() {
            return suodingDays;
        }

        public void setSuodingDays(String suodingDays) {
            this.suodingDays = suodingDays;
        }

        public String getYearRate() {
            return yearRate;
        }

        public void setYearRate(String yearRate) {
            this.yearRate = yearRate;
        }

        @Override
        public String toString() {
            return "ProInfoBean{" +
                    "id='" + id + '\'' +
                    ", memberNum='" + memberNum + '\'' +
                    ", minTouMoney='" + minTouMoney + '\'' +
                    ", money='" + money + '\'' +
                    ", name='" + name + '\'' +
                    ", progress='" + progress + '\'' +
                    ", suodingDays='" + suodingDays + '\'' +
                    ", yearRate='" + yearRate + '\'' +
                    '}';
        }
    }

    public static class ImageArrBean {
        /**
         * ID : 1
         * IMAPAURL : http://gwop.xtrich.com/xtApp/lexianghuo1.html
         * IMAURL : http://169.254.118.136:8080/P2PInvest/images/index01.png
         */

        public String ID;
        public String IMAPAURL;
        public String IMAURL;

        @Override
        public String toString() {
            return "ImageArrBean{" +
                    "ID='" + ID + '\'' +
                    ", IMAPAURL='" + IMAPAURL + '\'' +
                    ", IMAURL='" + IMAURL + '\'' +
                    '}';
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getIMAPAURL() {
            return IMAPAURL;
        }

        public void setIMAPAURL(String IMAPAURL) {
            this.IMAPAURL = IMAPAURL;
        }

        public String getIMAURL() {
            return IMAURL;
        }

        public void setIMAURL(String IMAURL) {
            this.IMAURL = IMAURL;
        }
    }
}
