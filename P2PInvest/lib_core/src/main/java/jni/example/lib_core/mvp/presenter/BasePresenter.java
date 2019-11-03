package jni.example.lib_core.mvp.presenter;

import jni.example.lib_core.mvp.model.IModel;
import jni.example.lib_core.mvp.view.IView;


/**
 * BasePresenter 泛型必须实现Imodule接口 或者IView 接口
 * @param <M>
 * @param <V>
 */
public abstract class BasePresenter<M extends IModel,V extends IView> implements IPresenter {
    protected M module;
    protected V v;

    public void BasePresenter(M module,V view){
        this.module = module;
        this.v = view;
    }

    @Override
    public void destroy() {
        if(module!=null)
            module.destroy();
        this.module = null;
        this.v = null;

    }
}
