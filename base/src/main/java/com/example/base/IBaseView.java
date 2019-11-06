package com.example.base;


import java.util.List;

//他是被presenter回调的
public interface IBaseView<T> {

   void onGetDataSucess(List<T> mlist,T data);
   void onGetDataListSucess(List<T>data);
   void onGetDataFiled(String fileMess);
}
