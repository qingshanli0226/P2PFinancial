package jni.example.p2pinvest.mvp.contract;

import jni.example.lib_core.mvp.model.IModel;
import jni.example.lib_core.mvp.view.IView;
import jni.example.p2pinvest.entity.BaseEntity;

/**
 * 订阅接口:
 * 规范model以及view相关子接口以及抽象方法
 */
public interface MainContract {
    interface MainIView extends IView{
        void success(BaseEntity entity);
    }

    interface MainIModel extends IModel{
        String requestMain(String str);

    }
}
