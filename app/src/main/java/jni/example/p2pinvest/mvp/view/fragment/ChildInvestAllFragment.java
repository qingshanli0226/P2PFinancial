package jni.example.p2pinvest.mvp.view.fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jni.example.base.BaseFragment;
import jni.example.base.IPresenter;
import jni.example.base.IView;
import jni.example.p2pinvest.R;
import jni.example.p2pinvest.adapter.MyInvestListAdapter;
import jni.example.p2pinvest.bean.Product;
import jni.example.p2pinvest.mvp.presenter.InvestPresenter;
import jni.example.p2pinvest.view.MyTextView;
import jni.example.p2pinvest.manager.PageManager;

public class ChildInvestAllFragment extends BaseFragment implements IView<Product> {

    private IPresenter<Product> iPresenter;
    //TODO 跑马灯
    private MyTextView tvProductTitle;
    private ListView investAllListView;
    private MyInvestListAdapter adapter;
    //TODO 加载页管理
    private PageManager pageManager;
    private RelativeLayout relativeLayout;
    private ArrayList<Product.DataBean> dataBeans = new ArrayList<>();

    @Override
    public int layoutId() {
        return R.layout.child_invest_fragment_all;
    }

    @Override
    public void init(View view) {
        tvProductTitle = (MyTextView) view.findViewById(R.id.tv_product_title);
        investAllListView = (ListView) view.findViewById(R.id.invest_all_list_view);
        relativeLayout = view.findViewById(R.id.invest_layout);
        pageManager = new PageManager(getContext());
        pageManager.setRelativeLayout(relativeLayout);
        adapter = new MyInvestListAdapter(dataBeans);
        iPresenter = new InvestPresenter();
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

        iPresenter.attachView(this);
        iPresenter.getData();
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
        dataBeans.clear();
        dataBeans.addAll(data.getData());
        adapter.notifyDataSetChanged();
        InvestFragment fragment = (InvestFragment) getParentFragment();
        fragment.setProduct(data);
    }

    @Override
    public void onGetDataListSuccess(List<Product> data) {

    }

    @Override
    public void onPostDataSuccess(Product data) {

    }

    @Override
    public void onPostDataFailed(String handleError) {

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
