package com.example.month6.databean;

public class RegisterBean {
    @Override
    public String toString() {
        return "RegisterBean{" +
                "isExist=" + isExist +
                '}';
    }

    /**
     * isExist : true
     */

    private boolean isExist;

    public boolean isIsExist() {
        return isExist;
    }

    public void setIsExist(boolean isExist) {
        this.isExist = isExist;
    }
}
