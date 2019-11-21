package com.example.p2pfiancial.bean;

import java.io.Serializable;

public class LoginBean {

    /**
     * data : {"name":"","imageurl":"http://192.168.191.1:8080/P2PInvest/images/tx.png","iscredit":"true","phone":""}
     * success : true
     */

    private DataBean data;
    private boolean success;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static class DataBean implements Serializable {
        /**
         * name :
         * imageurl : http://192.168.191.1:8080/P2PInvest/images/tx.png
         * iscredit : true
         * phone :
         */

        private String name;
        private String imageurl;
        private String iscredit;
        private String phone;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageurl() {
            return imageurl;
        }

        public void setImageurl(String imageurl) {
            this.imageurl = imageurl;
        }

        public String getIscredit() {
            return iscredit;
        }

        public void setIscredit(String iscredit) {
            this.iscredit = iscredit;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }
    }
}
