package jni.example.lib_core.mvp.view;

/**
 * 所有View抽象出来的父接口
 * showDialog ->显示请求等待对话框
 * hideDialog->关闭请求等待对话框
 */
public interface IView {

    void showDialog();
    void hidedialog();
    void showToast(String msg);
}
