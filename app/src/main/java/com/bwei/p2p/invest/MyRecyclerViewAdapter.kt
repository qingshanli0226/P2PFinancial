package com.bwei.p2p.invest

import com.bwei.base.bean.Products
import com.bwei.p2p.R
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class MyRecyclerViewAdapter( list: MutableList<Products.DataBean>, layoutId:Int): BaseQuickAdapter<Products.DataBean, BaseViewHolder>(layoutId,list){
    override fun convert(helper: BaseViewHolder?, item: Products.DataBean?) {
        helper!!.setText(R.id.p_money,item!!.money)
        helper!!.setText(R.id.p_yearlv,item!!.yearRate)
        helper!!.setText(R.id.p_suodingdays,item!!.suodingDays)
        helper!!.setText(R.id.p_minzouzi,item!!.minTouMoney)
        helper!!.setText(R.id.p_minnum,item!!.memberNum)
        helper!!.setText(R.id.item_title,item!!.name)
    }

}
