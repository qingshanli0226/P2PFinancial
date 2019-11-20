package com.example.p2pdemo.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.base.BaseActivity;
import com.example.p2pdemo.R;
import com.wangnan.library.GestureLockView;
import com.wangnan.library.listener.OnGestureLockListener;

public class HandActivity  extends BaseActivity {

    TextView handTv;
    GestureLockView lock;

    int count=5;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){

                Log.e("##","999");
                handTv.setText("已解锁");
                count=5;
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        }
    };

    @Override
    protected void InitView() {
        setContentView(R.layout.activity_hand);

    }

    @Override
    protected void InitData() {
        handTv=findViewById(R.id.handTv);
        lock = findViewById(R.id.lock);

        SharedPreferences shou = getSharedPreferences("shou", Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = shou.edit();
        edit.putString("result", "");
        edit.commit();
    }

    @Override
    protected void InitTitle() {


        lock.setGestureLockListener(new OnGestureLockListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(String progress) {

            }

            @Override
            public void onComplete(String result) {


                SharedPreferences shou = getSharedPreferences("shou", Context.MODE_PRIVATE);
                String string = shou.getString("result", null);
                if(string.length()>0){
                    if(result.length()>=4){
                        if(result.equals(string)){
                            Toast.makeText(HandActivity.this, "解锁成功!", Toast.LENGTH_SHORT).show();
                            lock.clearView();
                            finish();
                        }else{
                            --count;
                            handTv.setText("解锁失败!,还有"+count+"次机会,失败后请10秒后重试");
                            lock.showErrorStatus(1000);
                            if(count<1) {
                                handTv.setText("已锁定");
                                Toast.makeText(HandActivity.this, "您没有机会啦,请在5秒后重试", Toast.LENGTH_SHORT).show();
                                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
                                new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                        try {
                                            Thread.sleep(10000);
                                            Message message = new Message();
                                            message.what=1;
                                            handler.sendMessage(message);

                                        }catch (Exception e){

                                        }
                                    }
                                }).start();



                            }

                        }
                    }else{
                        Toast.makeText(HandActivity.this, "至少绘制4个点!", Toast.LENGTH_SHORT).show();
                        lock.clearView();
                    }



                }else{
                    if(result.length()>=4){
                        SharedPreferences preferences = getSharedPreferences("shou", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = preferences.edit();
                        edit.putString("result",result);
                        edit.commit();
                        Toast.makeText(HandActivity.this, "设置成功!", Toast.LENGTH_SHORT).show();
                        lock.clearView();
                        finish();

                    }else{
                        Toast.makeText(HandActivity.this, "至少连接4个点", Toast.LENGTH_SHORT).show();
                        lock.clearView();
                    }


                }







            }
        });





    }


}
