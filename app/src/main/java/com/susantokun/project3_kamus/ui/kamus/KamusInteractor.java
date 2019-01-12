package com.susantokun.project3_kamus.ui.kamus;

import android.content.Context;
import android.database.Cursor;

import com.susantokun.project3_kamus.base.BaseInteractor;
import com.susantokun.project3_kamus.data.model.Kamus;
import com.susantokun.project3_kamus.data.preferences.IPreferencesHelper;
import com.susantokun.project3_kamus.data.preferences.PreferencesHelper;
import com.susantokun.project3_kamus.data.sqlite.SqliteManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Susantokun on 11 November 2018
 */

public class KamusInteractor extends BaseInteractor<IPreferencesHelper> implements IKamusInteractor {

    private Context context;

    public KamusInteractor(PreferencesHelper preferencesHelper, Context context) {
        super(preferencesHelper);
        this.context = context;
    }

    @Override
    public Observable<List<Kamus>> searchDictionaryByWord(String query, boolean isEnglish) {
        return Observable.create(e -> {
            List<Kamus> dictionaries = new ArrayList<>();

            final Cursor cursor = SqliteManager.getInstance(context).selectByWord(query, isEnglish);

            if (cursor.moveToFirst()) {
                do {
                    dictionaries.add(new Kamus(cursor));
                } while (cursor.moveToNext());
            }
            cursor.close();

            e.onNext(dictionaries);
        });
    }

    @Override
    public Observable<List<Kamus>> getAllData(boolean isEnglish) {
        return Observable.create(e -> {
            List<Kamus> dictionaries = new ArrayList<>();

            final Cursor cursor = SqliteManager.getInstance(context).selectAllData(isEnglish);

            if (cursor.moveToFirst()) {
                do {
                    dictionaries.add(new Kamus(cursor));
                } while (cursor.moveToNext());
            }

            cursor.close();

            e.onNext(dictionaries);
        });
    }
}