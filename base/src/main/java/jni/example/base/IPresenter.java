package jni.example.base;

public interface IPresenter<T> {
    void getData();
    void postData();
    void attachView(IView<T> iView);
    void detachView();
    void attachListener(IGetDateListener<T> listener);
    void detachListener();
}
