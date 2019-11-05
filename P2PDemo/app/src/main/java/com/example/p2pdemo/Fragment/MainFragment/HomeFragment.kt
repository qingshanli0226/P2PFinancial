
package com.example.p2pdemo.Fragment.MainFragment
import android.util.Log
import android.view.View
import com.example.base.BaseFragment
import com.example.base.FragmentPresenter.HomePresenter
import com.example.base.IBasePresenter
import com.example.base.IBaseView
import com.example.net.HomeBaen
import com.example.p2pdemo.R


class HomeFragment : BaseFragment(),IBaseView<HomeBaen>{
    override fun onGetDataSucess(mlist: MutableList<HomeBaen>?, data: HomeBaen?) {
        Log.e("##",mlist.toString())
    }



    override fun onGetDataListSucess(data: MutableList<HomeBaen>?) {
    }

    override fun onGetDataFiled(fileMess: String?) {
    }


    var iBasePresenter:IBasePresenter?=null

    override fun setView(): Int {
        return R.layout.home_fragment
    }

    override fun inItData(view: View) {
        iBasePresenter=HomePresenter()




    }



}