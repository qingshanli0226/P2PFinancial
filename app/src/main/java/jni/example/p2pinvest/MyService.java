package jni.example.p2pinvest;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import jni.example.base.IGetDateListener;
import jni.example.base.IPresenter;
import jni.example.p2pinvest.bean.Index;
import jni.example.p2pinvest.mvp.presenter.HomePresenter;
import jni.example.p2pinvest.mvp.presenter.VersionsPresenter;

public class MyService extends Service {
    private Index index;
    private IPresenter presenter;
    private IPresenter versionsPresenter;

    public void registerGetDateListener(IGetDateListener listener){
        presenter.attachListener(listener);
        versionsPresenter.attachListener(listener);
    }

    public void unRegisterGetDateListener(){
        presenter.detachListener();
        versionsPresenter.detachListener();
    }

    public class MyBind extends Binder{
        public MyService getService(){
            return MyService.this;
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBind();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        presenter = new HomePresenter();
        versionsPresenter = new VersionsPresenter();

    }

    public void getUpDate(){
        versionsPresenter.getData();
    }

    public void getDate(){
        presenter.getData();
        Log.d("lhf","调用方法");
    }
}
