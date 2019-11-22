
package com.example.p2pdemo.Fragment.MainFragment
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.base.BaseFragment
import com.example.base.IBaseView
import com.example.p2pdemo.Bean.HomeBaen
import com.example.p2pdemo.Manager.CacheManager
import com.example.p2pdemo.Presenter.HomePresenter
import com.example.p2pdemo.R
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.home_fragment.view.*
import org.json.JSONObject


class HomeFragment : BaseFragment(),IBaseView<HomeBaen>, CacheManager.IHomeReceivedListener{
    override fun onPostDataFiled(postData: HomeBaen?) {

    }


    override fun onHomeDataReceived(homeBaen: HomeBaen?) {


        val imageArr = homeBaen!!.imageArr
        for (item in 0 until imageArr.size){
            val imageurl = imageArr.get(item).imaurl.toString()
            bannerList.add(imageurl)
        }
        if(bannerList!=null){
            var titleList= mutableListOf<String>()
            titleList.add("1")
            titleList.add("2")
            titleList.add("3")
            titleList.add("4")
            val homeBanner =mView!!.home_Banner
            homeBanner.setImages(bannerList)
                .setImageLoader(MyLoader())
                .setBannerTitles(titleList)
                .setDelayTime(1500)
                .start()
        }

        val homeLoadImg = mView!!.HomeLoadImg
        homeLoadImg.visibility=View.VISIBLE


    }

    var mView:View?=null
    override fun onConnected() {
        inItData()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onDisConnected() {
        Toast.makeText(context,"网络连接已断开",Toast.LENGTH_SHORT).show()
    }

    var bannerList= mutableListOf<String>()
    override fun onGetDataSucess(resultCode: Int, data: HomeBaen?) {


        if(resultCode==100){
            for (item in 0 until data!!.imageArr.size){
                val imageurl = data!!.imageArr.get(item).imaurl.toString()
                bannerList.add(imageurl)
            }
            if(bannerList!=null){
                var titleList= mutableListOf<String>()
                titleList.add("1")
                titleList.add("2")
                titleList.add("3")
                titleList.add("4")
                val homeBanner =mView!!.home_Banner
                homeBanner.setImages(bannerList)
                    .setImageLoader(MyLoader())
                    .setBannerTitles(titleList)
                    .setDelayTime(1500)
                    .start()



            }
        }

    }


    override fun unLoadView() {
        Handler().postDelayed({
            val loadImg = view!!.HomeLoadImg
            loadImg.visibility=View.GONE
            val homePage = view!!.home_page
            homePage.visibility=View.VISIBLE
        },2000)




    }

    override fun loadView() {
            val homeLoadImg = mView!!.HomeLoadImg
            homeLoadImg.visibility=View.VISIBLE

    }


    override fun onGetDataListSucess(data: MutableList<HomeBaen>?) {
    }

    override fun onGetDataFiled(fileMess: String?) {
        Handler().postDelayed({
            val homePage = view!!.home_page
            homePage.visibility=View.GONE
            val loadImg = view!!.HomeLoadImg
            loadImg.visibility=View.GONE
            val errorImg = mView!!.HomeErrorImg
            errorImg.visibility=View.VISIBLE
            errorImg.init(HomePresenter())
        },2000)

    }



    override fun setView(): Int {
        return R.layout.home_fragment
    }


    override fun inItData() {

        mView=baseView
        mView!!.H_titleBar.setTitleName(resources.getString(R.string.titleBar1))
        val homeData = CacheManager.getInstance().homeData
        //如果缓存有就拿出来,否则就注册拿数据
        if(homeData!=null){
            val jsonObject = JSONObject(homeData)
            val imageArr = jsonObject.getJSONArray("imageArr")
            for (item in 0 until imageArr.length()){
                val jsonObject1 = imageArr.getJSONObject(item)
                val image = jsonObject1.getString("IMAURL")
                bannerList.add(image)
            }
            if(bannerList!=null){
                var titleList= mutableListOf<String>()
                titleList.add("1")
                titleList.add("2")
                titleList.add("3")
                titleList.add("4")
                val homeBanner =mView!!.home_Banner
                homeBanner.setImages(bannerList)
                    .setImageLoader(MyLoader())
                    .setBannerTitles(titleList)
                    .setDelayTime(1500)
                    .start()
            }
            baseView.home_page.visibility=View.VISIBLE
        }else{
            CacheManager.getInstance().registerListener(this)
        }






    }


    inner class MyLoader:ImageLoader(){
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {
            Glide.with(context).load(path).into(imageView)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        CacheManager.getInstance().unRegisterListener(this)


    }




}