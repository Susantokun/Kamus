package com.susantokun.project3_kamus.ui.preload;

import com.susantokun.project3_kamus.base.IPresenter;

/**
 * Created by Susantokun on 11 November 2018
 */

public interface IPreloadPresenter extends IPresenter<IMainView, PreLoadInteractor> {
    void preloadDictionaries();
}
