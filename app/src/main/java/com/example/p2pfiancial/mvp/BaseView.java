package com.example.p2pfiancial.mvp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;

/**
 * MVPPlugin
 *  邮箱 784787081@qq.com
 */

public interface BaseView {
     void onCreate(@Nullable Bundle savedInstanceState);

     Context getContext();
}
