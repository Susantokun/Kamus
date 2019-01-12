package com.susantokun.project3_kamus.base;

import android.support.annotation.StringRes;

/**
 * Created by Susantokun on 11 November 2018
 */

public interface IView {
    void onError(@StringRes int resId);
    void onError(String message);
    void showMessage(String message);
    void showMessage(@StringRes int resId);
}
