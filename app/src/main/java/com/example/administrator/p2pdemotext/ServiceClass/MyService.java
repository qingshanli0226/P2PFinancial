package com.example.administrator.p2pdemotext.ServiceClass;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

public class MyService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public class Mybind extends Binder{
        public MyService getMyservice(){
            return MyService.this;
        }
    }
}


