
package com.example.p2pdemo.Fragment.MainFragment
import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.base.BaseFragment
import com.example.base.FragmentPresenter.HomePresenter
import com.example.base.IBaseView
import com.example.net.HomeBaen
import com.example.p2pdemo.R
import com.youth.banner.Banner
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.home_fragment.view.*


class HomeFragment : BaseFragment(),IBaseView<HomeBaen>{

    var bannerList= mutableListOf<String>()
    override fun onGetDataSucess(data: HomeBaen?) {
        for (item in 0 until data!!.imageArr.size){
            val imageurl = data!!.imageArr.get(item).imaurl.toString()
            Log.e("##",imageurl)
            bannerList.add(imageurl)

        }

    }


    override fun onGetDataListSucess(data: MutableList<HomeBaen>?) {
    }

    override fun onGetDataFiled(fileMess: String?) {
    }



    override fun setView(): Int {
        return R.layout.home_fragment
    }

    override fun inItData(view: View) {


        //请求数据Banner
        val homePresenter = HomePresenter()
        homePresenter.attachView(this)
        homePresenter.getData()

        if(bannerList!=null){
            var titleList= mutableListOf<String>()
            titleList.add("1")
            titleList.add("2")
            titleList.add("3")
            titleList.add("4")
            val homeBanner = view.home_Banner
            homeBanner.setImages(bannerList)
                .setImageLoader(MyLoader())
                .setBannerTitles(titleList)
                .setDelayTime(1500)
                .start()

        }



    }
    inner class MyLoader:ImageLoader(){
        override fun displayImage(context: Context?, path: Any?, imageView: ImageView?) {

            Glide.with(context).load(path).into(imageView)
        }

    }



}