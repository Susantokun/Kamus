package com.susantokun.project3_kamus.ui.preload;

import com.susantokun.project3_kamus.base.IInteractor;
import com.susantokun.project3_kamus.data.preferences.PreferencesHelper;

import io.reactivex.Observable;

/**
 * Created by Susantokun on 11 November 2018
 */

public interface IPreLoadInteractor extends IInteractor<PreferencesHelper> {
    Observable<Integer> saveDictionaries();
}
