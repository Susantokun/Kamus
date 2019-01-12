package com.susantokun.project3_kamus.ui.preload;

import com.susantokun.project3_kamus.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Susantokun on 11 November 2018
 */

public class PreloadPresenter extends BasePresenter<IMainView, PreLoadInteractor> implements IPreloadPresenter {

    public PreloadPresenter(PreLoadInteractor mvpInteractor, CompositeDisposable compositeDisposable) {
        super(mvpInteractor, compositeDisposable);
    }

    @Override
    public void preloadDictionaries() {
        getCompositeDisposable().add(getInteractor().saveDictionaries()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(new DisposableObserver<Integer>() {
                @Override
                public void onNext(Integer integer) {
                    getIView().publishProgress(integer);
                }

                @Override
                public void onError(Throwable e) {
                    getIView().onError(e.getLocalizedMessage());
                }

                @Override
                public void onComplete() {
                    getIView().onPreLoadCompleted();
                }
            }));

    }
}
