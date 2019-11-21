package com.example.p2pfiancial.gesturelock;

import android.content.Context;
import android.text.TextUtils;

import com.example.p2pfiancial.cache.ACache;
import com.example.p2pfiancial.userinfo.UserInfoManager;

import java.util.List;

public class PatternHelper {
    public static final int MAX_SIZE = 4;
    public static final int MAX_TIMES = 5;
    private static final String GESTURE_PWD_KEY = "gesture_pwd_key";

    private String message;
    private String storagePwd;
    private String tmpPwd;
    private int times;
    private boolean isFinish;
    private boolean isOk;
    private ACache aCache;

    public PatternHelper(Context context){
        this.aCache = ACache.get(context);
    }



    //设置手势
    public void validateForSetting(List<Integer> hitIndexList) {
        this.isFinish = false;
        this.isOk = false;

        if ((hitIndexList == null) || (hitIndexList.size() < MAX_SIZE)) {
            this.tmpPwd = null;
            this.message = getSizeErrorMsg(); //"至少连接个%d点, 请重新绘制"
            return;
        }

        //1. draw first time
        if (TextUtils.isEmpty(this.tmpPwd)) {
            this.tmpPwd = convert2String(hitIndexList);
            this.message = getReDrawMsg(); //获取重新绘制消息"请再次绘制解锁图案"
            this.isOk = true;
            return;
        }

        //2. draw second times
        if (this.tmpPwd.equals(convert2String(hitIndexList))) {
            this.message = getSettingSuccessMsg();//获取设置成功消息 "手势解锁图案设置成功"
            saveToStorage(this.tmpPwd); //保存密码
            this.isOk = true;
            this.isFinish = true;
        } else {
            this.tmpPwd = null;
            this.message = getDiffPreErrorMsg(); //"与上次绘制不一致, 请重新绘制"
        }
    }

    //解锁 验证密码
    public void validateForChecking(List<Integer> hitIndexList) {
        this.isOk = false;
        if ((hitIndexList == null) || (hitIndexList.size() < MAX_SIZE)) {
            this.times++;
            this.isFinish = this.times >= MAX_SIZE;
            this.message = getPwdErrorMsg();
            return;
        }

        this.storagePwd = getFromStorage(); //获取密码
        if (!TextUtils.isEmpty(this.storagePwd) && this.storagePwd.equals(convert2String(hitIndexList))) {
            this.message = getCheckingSuccessMsg(); //"解锁成功"
            this.isOk = true;
            this.isFinish = true;
        } else {
            this.times++;
            this.isFinish = this.times >= MAX_SIZE;
            this.message = getPwdErrorMsg();
        }
    }

    public String getMessage(){
        return this.message;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public boolean isOk() {
        return isOk;
    }

    private String getCheckingSuccessMsg() {
        return "解锁成功！";
    }

    //保存到存储
    private void saveToStorage(String tmpPwd) {
        UserInfoManager.getInstance().saveGestureLock(tmpPwd);
    }

    //获取密码
    private String getFromStorage() {
        return UserInfoManager.getInstance().readGestureLock();
    }

    private String getPwdErrorMsg() {
        return String.format("密码错误, 还剩%d次机会", getRemainTimes());
    }

    //获取剩余时间
    private int getRemainTimes() {
        return (times < 5) ? (MAX_TIMES - times) : 0;
    }

    private String getDiffPreErrorMsg() {
        return "与上次绘制不一致, 请重新绘制";
    }


    //获取设置成功消息
    private String getSettingSuccessMsg() {
        return "手势解锁图案设置成功";
    }

    //获取重新绘制消息
    private String getReDrawMsg() {
        return "请再次绘制解锁图案";
    }

    //转换字符串
    private String convert2String(List<Integer> hitIndexList) {

        return hitIndexList.toString();
    }

    private String getSizeErrorMsg() {
        return String.format("至少连接个%d点, 请重新绘制", MAX_SIZE);
    }


}
