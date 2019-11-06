package jni.example.base;

public interface IActivity {
    //返回要关联的xml布局ID
    int layoutId();
    //初始化控件
    void init();

    //初始化数据源
    void initData();

    //设置样式
    void setWindow();
}
