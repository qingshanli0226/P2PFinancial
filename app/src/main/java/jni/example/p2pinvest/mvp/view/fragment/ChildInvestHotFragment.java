package jni.example.p2pinvest.mvp.view.fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import jni.example.base.BaseFragment;
import jni.example.base.IPresenter;
import jni.example.p2pinvest.R;
import jni.example.p2pinvest.bean.Product;

public class ChildInvestHotFragment extends BaseFragment {
    private Random random;

    private TagFlowLayout investHotFlowLayout;

    private ChildInvestAllFragment allFragment;
    private ArrayList<String> strings = new ArrayList<>();

    @Override
    public int layoutId() {
        return R.layout.child_invest_fragment_hot;
    }

    @Override
    public void init(View view) {
        random = new Random();
        investHotFlowLayout = (TagFlowLayout) view.findViewById(R.id.invest_hot_flowLayout);
        allFragment = new ChildInvestAllFragment();

    }

    public int getColor() {
        return  0xff000000 | random.nextInt(0x00ffffff);
    }

    @Override
    public void initData() {
        ((InvestFragment)getParentFragment()).onRegisterListener(new InvestFragment.GetDataListener() {
            @Override
            public void getDate(Product product) {
                List<Product.DataBean> data = product.getData();
                for (Product.DataBean datum : data) {
                    strings.add(datum.getName());
                }

                investHotFlowLayout.setAdapter(new TagAdapter<String>(strings) {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @SuppressLint("WrongConstant")
                    @Override
                    public View getView(FlowLayout parent, int position, String o) {
                        final TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.invest_hot_item, investHotFlowLayout, false);
                        final GradientDrawable gradientDrawable = new GradientDrawable();
                        gradientDrawable.setColor(getColor());
                        gradientDrawable.setCornerRadius(8f);
                        textView.setText(o);
                        textView.setBackground(gradientDrawable);
                        textView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(getActivity(), textView.getText().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                        textView.setOnTouchListener(new View.OnTouchListener() {
                            @Override
                            public boolean onTouch(View v, MotionEvent event) {
                                switch (event.getAction()){
                                    case MotionEvent.ACTION_DOWN:
                                        gradientDrawable.setColor(Color.WHITE);
                                        break;
                                    case MotionEvent.ACTION_UP:
                                        gradientDrawable.setColor(getColor());
                                        break;
                                }

                                return true;
                            }
                        });
                        return textView;
                    }
                });
            }
        });
    }
}
