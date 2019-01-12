package com.susantokun.project3_kamus.ui.kamus;

import com.susantokun.project3_kamus.base.IPresenter;

/**
 * Created by Susantokun on 11 November 2018
 */

public interface IKamusPresenter extends IPresenter<IKamusView, IKamusInteractor> {
    void searchDictionaryByWord(String query, boolean isEnglish);
    void fetchAllData(boolean isEnglish);
}
