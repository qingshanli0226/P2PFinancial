package jni.example.p2pinvest.mvp.view.fragment;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import jni.example.base.BaseFragment;
import jni.example.base.BasePresenter;
import jni.example.base.IPresenter;
import jni.example.base.IView;
import jni.example.common.Constant;
import jni.example.common.ConstantMain;
import jni.example.p2pinvest.R;
import jni.example.p2pinvest.adapter.MyInvestListAdapter;
import jni.example.p2pinvest.bean.Product;
import jni.example.p2pinvest.mvp.presenter.InvestAllPresenter;
import jni.example.p2pinvest.view.MyTextView;
import jni.example.p2pinvest.view.PageManager;

public class ChildInvestAllFragment extends BaseFragment implements IView<Product> {
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what== ConstantMain.PRODUCT){
                products = (Product) msg.obj;
                List<Product.DataBean> data = products.getData();
                dataBeans.addAll(data);
                adapter.notifyDataSetChanged();
            }
        }
    };
    private Product products;
    private IPresenter<Product> presenter;
    private PageManager pageManager;
    private MyTextView tvProductTitle;
    private ListView investAllListView;
    private RelativeLayout relativeLayout;
    private MyInvestListAdapter adapter;
    private ArrayList<Product.DataBean> dataBeans = new ArrayList<>();
    @Override
    public int layoutId() {
        return R.layout.child_invest_fragment_all;
    }

    @Override
    public void init(View view) {
        tvProductTitle = (MyTextView) view.findViewById(R.id.tv_product_title);
        investAllListView = (ListView) view.findViewById(R.id.invest_all_list_view);
        relativeLayout = (RelativeLayout) view.findViewById(R.id.invest_layout);
        pageManager = new PageManager(getContext());
        pageManager.setRelativeLayout(relativeLayout);

        presenter = new InvestAllPresenter();
        presenter.attachView(this);
        adapter = new MyInvestListAdapter(dataBeans);
    }

    @Override
    public void initData() {
        if (!isConnected()) {
            showNotNetWorkPage();
            hideLoading();
            hideErrorPage();
            return;
        }
        hideNotNetWorkPage();

        presenter.getData();
        investAllListView.setAdapter(adapter);
    }

    @Override
    public void showLoading() {
        pageManager.showLoading();
    }

    @Override
    public void hideLoading() {
        pageManager.hideLoading();
    }

    @Override
    public void showErrorPage() {
        pageManager.showErrorPage();
    }

    @Override
    public void hideErrorPage() {
        pageManager.hideErrorPage();
    }

    @Override
    public void showNotNetWorkPage() {
        pageManager.showNotNetWorkPage();
    }

    @Override
    public void hideNotNetWorkPage() {
        pageManager.hideNotNetWorkPage();
    }

    @Override
    public void onGetDataFailed(String msg) {

    }

    @Override
    public void onGetDataSuccess(Product data) {
        Message obtain = Message.obtain();
        obtain.what = ConstantMain.PRODUCT;
        obtain.obj = data;
        handler.sendMessage(obtain);
        Toast.makeText(getActivity(), "PRODUCT--数据请求成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onGetDataListSuccess(List<Product> data) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        pageManager=null;
    }

    @Override
    public void onConnected() {
        super.onConnected();
        initData();
        hideNotNetWorkPage();
    }

    @Override
    public void onDisConnected() {
        super.onDisConnected();
        showNotNetWorkPage();
        Toast.makeText(getActivity(), "当前网络连接已经断开", Toast.LENGTH_SHORT).show();
    }
}
