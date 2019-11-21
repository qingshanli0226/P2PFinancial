package jni.example.p2pinvest.bean;

public class UserBean {

    /**
     * data : {"name":"123","imageurl":"http://192.168.191.1:8080/P2PInvest/images/tx.png","iscredit":"true","phone":"123"}
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

    public static class DataBean {
        /**
         * name : 123
         * imageurl : http://192.168.191.1:8080/P2PInvest/images/tx.png
         * iscredit : true
         * phone : 123
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
