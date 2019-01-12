package com.susantokun.project3_kamus.ui.preload;

import android.content.Context;

import com.susantokun.project3_kamus.R;
import com.susantokun.project3_kamus.base.BaseInteractor;
import com.susantokun.project3_kamus.data.model.Kamus;
import com.susantokun.project3_kamus.data.preferences.PreferencesHelper;
import com.susantokun.project3_kamus.data.sqlite.SqliteManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import io.reactivex.Observable;

/**
 * Created by Susantokun on 11 November 2018
 */

public class PreLoadInteractor extends BaseInteractor<PreferencesHelper> implements IPreLoadInteractor {

    private Context context;
    private double progress;
    private double maxProgress = 100;

    public PreLoadInteractor(PreferencesHelper preferencesHelper, Context context) {
        super(preferencesHelper);
        this.context = context;
    }

    private ArrayList<Kamus> preLoadRaw(int rawResource) {
        ArrayList<Kamus> dictionaries = new ArrayList<>();
        String line = null;
        BufferedReader reader;

        try {
            InputStream rawDict = context.getResources().openRawResource(rawResource);

            reader = new BufferedReader(new InputStreamReader(rawDict));

            do {
                line = reader.readLine();
                String[] splitStr = line.split("\t");

                Kamus dictionary;
                dictionary = new Kamus(splitStr[0], splitStr[1]);
                dictionaries.add(dictionary);
            } while (line != null);
        } catch (Exception err) {
            err.printStackTrace();
        }
        return dictionaries;
    }

    @Override
    public Observable<Integer> saveDictionaries() {
        return Observable.create(e -> {
            Boolean firstRun = getPreferencesHelper().getFirstRun();

            if (firstRun) {
                ArrayList<Kamus> engInaDictionaries = preLoadRaw(R.raw.english_indonesia);
                ArrayList<Kamus> inaEngDictionaries = preLoadRaw(R.raw.indonesia_english);

                e.onNext((int) progress);

                double progressMaxInsert = 100.0;
                double progressDiff = (progressMaxInsert - progress) / (engInaDictionaries.size()
                        + inaEngDictionaries.size() * 1000);

                try {
                    SqliteManager.getInstance(context).insertTransaction(engInaDictionaries, true);
                    progress += progressDiff;
                    e.onNext((int) progress);

                    SqliteManager.getInstance(context).insertTransaction(inaEngDictionaries, false);
                    progress += progressDiff;
                    e.onNext((int) progress);

                    getPreferencesHelper().setFirstRun(false);

                    e.onNext((int) maxProgress);
                } catch (Exception err) {
                    e.onError(err);
                } finally {
                    e.onComplete();
                }
            } else {
                try {
                    synchronized (this) {
                        this.wait(1000);
                        e.onNext(50);

                        this.wait(300);
                        e.onNext((int) maxProgress);
                        e.onComplete();
                    }
                } catch (Exception err) {
                    e.onError(err);
                }
            }
        });
    }
}
