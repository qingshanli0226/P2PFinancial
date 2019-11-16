package com.example.p2pdemo.gesture;

import android.content.Context;

import com.example.modulecommon.Constructor;
import com.example.p2pdemo.ACache;

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
    private ACache aCache;

    public GestureHelper(Context context) {
        this.context = context;
        aCache = ACache.get(context);
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
        if (this.tmpPwd.isEmpty()){
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
    }

    private String getDiffPreErrorMsg() {
        return "与上次不一致,请重新绘制";
    }

    private String getSettingSuccessMsg() {
        return "手势解锁图案设置成功!";
    }

    private void saveToPwd(String tmpPwd) {
       aCache.put(Constructor.KEY_GESTURE,tmpPwd);
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
}
