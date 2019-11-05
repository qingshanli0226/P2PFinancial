package jni.example.p2pinvest.mvp.presenter;

import jni.example.lib_core.mvp.presenter.BasePresenter;
import jni.example.p2pinvest.mvp.contract.MainContract;

public class MainPresenter extends BasePresenter<MainContract.MainIModel, MainContract.MainIView> {

    @Override
    public void BasePresenter(MainContract.MainIModel module, MainContract.MainIView view) {
        super.BasePresenter(module, view);
    }

    public void requestMain(){
        module.requestMain("Presenter->发起的网络请求");
    }

}
