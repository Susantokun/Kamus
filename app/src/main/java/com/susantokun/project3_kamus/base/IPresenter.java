package com.susantokun.project3_kamus.base;

/**
 * Created by Susantokun on 11 November 2018
 */

public interface IPresenter<V extends IView, I extends IInteractor> {
    void onAttach(V iView);
    void onDetach();
    V getIView();
    I getInteractor();
}
