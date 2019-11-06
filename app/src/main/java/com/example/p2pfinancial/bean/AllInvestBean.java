package com.example.p2pfinancial.bean;

public class AllInvestBean {

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

    @Override
    public String toString() {
        return "AllInvestBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", money='" + money + '\'' +
                ", yearRate='" + yearRate + '\'' +
                ", suodingDays='" + suodingDays + '\'' +
                ", minTouMoney='" + minTouMoney + '\'' +
                ", memberNum='" + memberNum + '\'' +
                ", progress='" + progress + '\'' +
                '}';
    }
}
