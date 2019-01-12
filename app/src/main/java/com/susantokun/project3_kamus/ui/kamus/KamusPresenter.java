package com.susantokun.project3_kamus.ui.kamus;

import com.susantokun.project3_kamus.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Susantokun on 11 November 2018
 */

public class KamusPresenter extends BasePresenter<IKamusView, IKamusInteractor> implements IKamusPresenter {

    public KamusPresenter(IKamusInteractor iInteractor, CompositeDisposable compositeDisposable) {
        super(iInteractor, compositeDisposable);
    }

    @Override
    public void searchDictionaryByWord(String query, boolean isEnglish) {
        getCompositeDisposable().add(getInteractor().searchDictionaryByWord(query, isEnglish)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dictionaries ->
                        getIView().displayData(dictionaries)
                ));
    }

    @Override
    public void fetchAllData(boolean isEnglish) {
        getCompositeDisposable().add(getInteractor().getAllData(isEnglish)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(dictionaries -> getIView().displayData(dictionaries)));
    }
}
