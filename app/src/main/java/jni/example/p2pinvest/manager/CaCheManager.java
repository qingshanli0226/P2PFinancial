package jni.example.p2pinvest.manager;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

import jni.example.base.IGetDateListener;
import jni.example.common.ConstantMain;
import jni.example.common.NetConnectManager;
import jni.example.p2pinvest.MyService;
import jni.example.p2pinvest.bean.Index;

public class CaCheManager {
    private GetDateListener listener = null;
    private Object object;
    private List<GetDateListener> listeners = new LinkedList<>();
    private static CaCheManager manager;
    private static String indexPath = "/sdcard/indexData.txt";
    private static String productPath = "/sdcard/productData.txt";

    public void registerGetDateListener(GetDateListener listener){
        if(!listeners.contains(listener)){
            listeners.add(listener);
        }
    }

    public void unRegisterGetDateListener(GetDateListener listener){
        if(listeners.contains(listener)){
            listeners.remove(listener);
        }
    }

    private CaCheManager() {
    }

    public MyService myService;

    public static CaCheManager getInstance() {
        if (manager == null) {
            manager = new CaCheManager();
        }
        return manager;
    }

    public void init(Context application) {
        Intent intent = new Intent(application, MyService.class);
        application.startService(intent);
        application.bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                MyService.MyBind bind = (MyService.MyBind) service;
                myService = bind.getService();
                myService.registerGetDateListener(new IGetDateListener() {
                    @Override
                    public void onGetDataFailed(String msg) {

                    }

                    @Override
                    public void onGetDataSuccess(int requestCode,Object data) {
                        object = data;
                        if(requestCode== ConstantMain.INDEX){
                            writeObject(data,indexPath);
                            for (GetDateListener getDateListener : listeners) {
                                getDateListener.onGetIndex((Index) data);
                            }
                        }
                    }

                    @Override
                    public void onGetDataListSuccess(List data) {

                    }


                });
                myService.getDate();
                Log.d("lhf","服务注册,请求数据");
                //注册listener，监听当前网络连接的变化
                NetConnectManager.getInstance().registerNetConnectListener(new NetConnectManager.INetConnectListener() {
                    @Override
                    public void onConnected() {
                        myService.getDate();
                        Log.d("lhf","有网络了,请求数据");
                    }

                    @Override
                    public void onDisConnected() {

                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    public static Object readObject(String fileName) {
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            Object object = ois.readObject();
            return object;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return null;
    }

    public static void writeObject(Object obj,String outputFile) {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(new File(outputFile));
            oos = new ObjectOutputStream(fos);
            Log.d("lhf","写入SD卡");
            oos.writeObject(obj);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public Object getObject(){
        return readObject(indexPath);
    }

    public interface GetDateListener{
        void onGetIndex(Index index);
    }
}
