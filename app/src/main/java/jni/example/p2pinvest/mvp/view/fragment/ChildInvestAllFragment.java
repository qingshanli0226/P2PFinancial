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

public class ChildInvestAllFragment extends BaseFragment {

    private IPresenter<Product> iPresenter;
    //TODO 跑马灯
    private MyTextView tvProductTitle;
    private ListView investAllListView;
    private MyInvestListAdapter adapter;

    private ArrayList<Product.DataBean> dataBeans = new ArrayList<>();

    @Override
    public int layoutId() {
        return R.layout.child_invest_fragment_all;
    }

    @Override
    public int RelativeLayoutID() {
        return R.id.invest_layout;
    }

    @Override
    public void init(View view) {
        tvProductTitle = (MyTextView) view.findViewById(R.id.tv_product_title);
        investAllListView = (ListView) view.findViewById(R.id.invest_all_list_view);

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
    public void onGetDataSuccess(Object data) {
        super.onGetDataSuccess(data);
        Product product = (Product) data;
        dataBeans.clear();
        dataBeans.addAll(product.getData());
        adapter.notifyDataSetChanged();
        InvestFragment fragment = (InvestFragment) getParentFragment();
        fragment.setProduct(product);
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
