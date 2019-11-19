package com.example.administrator.p2pdemotext.Fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

import com.example.administrator.p2pdemotext.Base.BaseFragment;
import com.example.administrator.p2pdemotext.R;
import com.example.administrator.p2pdemotext.ui.SecondaryPage.AboutUsActivity;

public class FragmentMore  extends BaseFragment<Object> {
    private LinearLayout fragmentMoreLogin;
    private LinearLayout fragmentMorePass;
    private LinearLayout fragmentMoreRePass;
    private LinearLayout fragmentMoreRelation;
    private LinearLayout fragmentMoreFeedBack;
    private LinearLayout fragmentMoreShare;
    private LinearLayout fragmentMoreRegards;
    private Switch moreswitch;



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_more;
    }

    @Override
    protected void initData() {
        super.initData();
        moreswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moreswitch.isChecked()==true){
                    SharedPreferences sp=getActivity().getSharedPreferences("ssh",0);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("type","0");
                    edit.commit();
                    Toast.makeText(getContext(), "开启手势密码", Toast.LENGTH_SHORT).show();
                }else if (moreswitch.isChecked()==false){
                    SharedPreferences sp=getActivity().getSharedPreferences("ssh",0);
                    SharedPreferences.Editor edit = sp.edit();
                    edit.putString("type","123");
                    edit.commit();
                    Toast.makeText(getContext(), "关闭手势密码", Toast.LENGTH_SHORT).show();
                }

            }
        });
        //关于硅谷理财
        fragmentMoreRegards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "关于硅谷金融", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getContext(),AboutUsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {


        fragmentMoreLogin = (LinearLayout) view.findViewById(R.id.fragmentMoreLogin);
        fragmentMorePass = (LinearLayout) view.findViewById(R.id.fragmentMorePass);
        fragmentMoreRePass = (LinearLayout) view.findViewById(R.id.fragmentMoreRePass);
        fragmentMoreRelation = (LinearLayout) view.findViewById(R.id.fragmentMoreRelation);
        fragmentMoreFeedBack = (LinearLayout) view.findViewById(R.id.fragmentMoreFeedBack);
        fragmentMoreShare = (LinearLayout) view.findViewById(R.id.fragmentMoreShare);
        fragmentMoreRegards = (LinearLayout) view.findViewById(R.id.fragmentMoreRegards);
        moreswitch = (Switch) view.findViewById(R.id.moreswitch);
    }

    @Override
    protected void initTopTitle() {

    }
}
