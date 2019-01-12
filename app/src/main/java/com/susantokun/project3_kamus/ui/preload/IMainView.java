package com.susantokun.project3_kamus.ui.preload;

import com.susantokun.project3_kamus.base.IView;

/**
 * Created by Susantokun on 11 November 2018
 */

public interface IMainView extends IView {
    void publishProgress(Integer progressValue);

    void onPreLoadCompleted();
}