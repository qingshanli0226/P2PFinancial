package com.example.net;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkhttpUntils extends AsyncTask<String,Void,String> {
    Banner banner;
    ArrayList<Integer> integerArrayList;

    public OkhttpUntils(Banner banner, ArrayList<Integer> integerArrayList) {
        this.banner = banner;
        this.integerArrayList = integerArrayList;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        Gson gson = new Gson();
    //    Log.d("onPostExecute", "onPostExecute: "+s);

   BannerData bannerData=gson.fromJson(s.toString(),BannerData.class);
//        ArrayList<String> list=new ArrayList<>();
//        for (int i = 0; i < bannerData.getImageArr().size(); i++) {
//            list.add(bannerData.getImageArr().get(i).getIMAURL());
//        }
        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        String[] titles = new String[]{"分享砍学费", "人脉总动员", "想不到你是这样的app", "购物节，爱不单行"};
        banner.setBannerTitles(Arrays.asList(titles));
        banner.isAutoPlay(true);
        //设置banner动画效果
        banner.setBannerAnimation(Transformer.DepthPage);
        banner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        banner.setIndicatorGravity(BannerConfig.CENTER);
        banner.setImages(integerArrayList);
    //    banner.setImageLoader(new BannerLoader());
        banner.setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
           imageView.setImageResource((Integer) path);
            }
        });
        banner.start();
    }

    @Override
    protected String doInBackground(String... strings) {
        String url=AppNetConfig.INDEX;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).get().build();

        Response execute = null;
         String s="";
        try {
            execute = client.newCall(request).execute();

            s=execute.body().toString();
            Log.d("onPostExecute", "onPostExecute: "+s);
        } catch (IOException e) {

            e.printStackTrace();
        }

        return s;
    }
}
