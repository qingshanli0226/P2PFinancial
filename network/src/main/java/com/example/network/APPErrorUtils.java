package com.example.network;

import android.util.Log;

public class APPErrorUtils {
    //网络 1000   存储or文件2000   数据3000  业务4000
    public static final String NET_RRROR="1000";

    public static final String FILE_RRROR="2000";

    public static final String DATA_RRROR="3000";

    public static final String MY_RRROR="4000";

    public static void findError(Throwable e){
        Log.e("xxxx","发现错误:"+e.getMessage());

    }
}
