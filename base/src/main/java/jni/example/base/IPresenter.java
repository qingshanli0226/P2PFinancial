package jni.example.base;

public interface IPresenter<T> {
    void getData();
    void attachView(IView<T> iView);
    void detachView();
}
