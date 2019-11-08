
package com.example.p2pdemo.Fragment.MainFragment
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.base.BaseFragment
import com.example.base.IBaseView
import com.example.p2pdemo.Bean.HomeBaen
import com.example.p2pdemo.Presenter.HomePresenter
import com.example.p2pdemo.R
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.home_fragment.view.*


class HomeFragment : BaseFragment(),IBaseView<HomeBaen>{
    var mView:View?=null

    override fun unLoadView() {
        Handler().postDelayed({
            val loadImg = view!!.HomeLoadImg
            loadImg.visibility=View.GONE
            val homePage = view!!.home_page
            homePage.visibility=View.VISIBLE
        },3000)


    }

    override fun loadView() {
            val homeLoadImg = mView!!.HomeLoadImg
            homeLoadImg.visibility=View.VISIBLE



    }

    var bannerList= mutableListOf<String>()
    override fun onGetDataSucess(data: HomeBaen?) {
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


    override fun onGetDataListSucess(data: MutableList<HomeBaen>?) {
    }

    override fun onGetDataFiled(fileMess: String?) {
    }



    override fun setView(): Int {
        return R.layout.home_fragment
    }

    override fun inItData(viewH: View) {

        mView=viewH

        //请求数据Banner
        val homePresenter = HomePresenter()
        homePresenter.attachView(this)
        homePresenter.getData()


    }
    inner class MyLoader:ImageLoader(){
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {

            Glide.with(context).load(path).into(imageView)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        HomePresenter().detchView()

    }



}