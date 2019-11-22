package com.bwei.p2p;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;

import com.bwei.base.UserManager;

import java.util.List;

public class GestureHelper {
    public static final int MAX_SIZE = 4;
    public static final int MAX_TIMES = 5;
    private static final String GESTURE_PWD_KEY = "gesture_pwd_key";
    private Context context;
    private String message;
    private String storagePwd;
    private String tmpPwd;
    private int times;
    private boolean isFinish;
    private boolean isOk;
    private UserManager user;
    public GestureHelper(Context context) {
        this.context = context;
        user = UserManager.getInstance();
    }

    public void validateForSetting(List<Integer> hitIndexList) {
        this.isFinish = false;
        this.isOk = false;

        if (hitIndexList == null || hitIndexList.size()<MAX_SIZE)
        {
            this.tmpPwd = null;
            this.message = getSizeErrorMsg();
            return;
        }
        //第一次绘制
        if (TextUtils.isEmpty(tmpPwd)){
            this.tmpPwd = convertString(hitIndexList);
            this.message = getReDrawMsg();
            this.isOk =true;
            return;
        }
        //第二次画
        if (this.tmpPwd.equals(convertString(hitIndexList))){
            this.message = getSettingSuccessMsg();
            saveToPwd(this.tmpPwd);
            this.isOk = true;
            this.isFinish = true;
        }else {
            this.tmpPwd = null;
            this.message = getDiffPreErrorMsg();
        }
    }

    public void loginVallidata(List<Integer> hitIndexList){
        this.isOk = false;
        if (hitIndexList == null || hitIndexList.size()<MAX_SIZE){
            this.times++;
            this.isFinish = this.times >= MAX_TIMES;
            this.message = getPwdErrorMsg();
            return;
        }
        this.storagePwd = getFromStorage();
        if (!TextUtils.isEmpty(this.storagePwd) && this.storagePwd.equals(convertString(hitIndexList))) {
            this.message = getCheckingSuccessMsg();
            this.isOk = true;
            this.isFinish = true;
        } else {
            this.times++;
            this.isFinish = this.times >= MAX_SIZE;
            this.message = getPwdErrorMsg();
        }
    }

    @SuppressLint("DefaultLocale")
    private String getPwdErrorMsg() {
   return String.format("密码错误，还剩%d次机会", getRemainTimes());
    }
    private String getCheckingSuccessMsg() {
        return "解锁成功！";
    }
    private String getDiffPreErrorMsg() {
        return "与上次不一致,请重新绘制";
    }

    private String getSettingSuccessMsg() {
//        sp.edit().putString("inputCode","input").apply();
        return "手势解锁图案 设置成功!";
    }

    private void saveToPwd(String tmpPwd) {
        user.saveGesture(tmpPwd);
    }

    private String getReDrawMsg() {
        return "请确认绘制的解锁图案";
    }

    private String convertString(List<Integer> hitIndexList) {
        return hitIndexList.toString();
    }

    private String getSizeErrorMsg() {
        return "至少连接"+MAX_SIZE+"个点";
    }
    private int getRemainTimes() {
        return (times < 5) ? (MAX_TIMES - times) : 0;
    }
    public boolean isOk(){
        return isOk;
    }

    public String getMessage() {
        return message;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public void setFinish(boolean finish) {
        isFinish = finish;
    }

    private String getFromStorage() {
        return UserManager.getInstance().readGesture();
    }}