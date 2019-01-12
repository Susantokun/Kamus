package com.susantokun.project3_kamus.base;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Susantokun on 11 November 2018
 */

public class BasePresenter <V extends IView, I extends IInteractor> implements IPresenter<V, I> {

    private V iView ;
    private I iInteractor ;

    private CompositeDisposable compositeDisposable ;

    public BasePresenter(I iInteractor, CompositeDisposable compositeDisposable) {
        this.iInteractor = iInteractor;
        this.compositeDisposable = compositeDisposable;
    }

    @Override
    public void onAttach(V iView) {
        this.iView = iView ;
    }

    @Override
    public void onDetach() {
        compositeDisposable.clear();
        iView = null ;
        iInteractor =null ;
    }

    @Override
    public V getIView() {
        return iView;
    }

    @Override
    public I getInteractor() {
        return iInteractor;
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }
}
