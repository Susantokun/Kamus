package com.susantokun.project3_kamus.ui.kamus;

import com.susantokun.project3_kamus.base.IInteractor;
import com.susantokun.project3_kamus.data.model.Kamus;
import com.susantokun.project3_kamus.data.preferences.IPreferencesHelper;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Susantokun on 11 November 2018
 */

public interface IKamusInteractor extends IInteractor<IPreferencesHelper> {
    Observable<List<Kamus>> searchDictionaryByWord(String query, boolean isEnglish);
    Observable<List<Kamus>> getAllData(boolean isEnglish);
}