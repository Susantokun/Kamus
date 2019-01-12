package com.susantokun.project3_kamus.base;

/**
 * Created by Susantokun on 11 November 2018
 */

public class BaseInteractor<T> implements IInteractor<T> {

    private T preferencesHelper;

    public BaseInteractor(T preferencesHelper) {
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public T getPreferencesHelper() {
        return preferencesHelper;
    }
}
