package com.susantokun.project3_kamus.data.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.susantokun.project3_kamus.data.model.Kamus;

import java.util.List;

import static com.susantokun.project3_kamus.data.sqlite.SqliteContract.*;
import static com.susantokun.project3_kamus.data.sqlite.SqliteContract.DictionaryColumns.*;

/**
 * Created by Susantokun on 11 November 2018
 */

public class SqliteManager {
    private static SqliteManager sInstance;
    private SQLiteDatabase mDb;
    private SqliteHelper mDbHelper;

    public SqliteManager(Context context) {
        mDbHelper = new SqliteHelper(context);
        mDb = mDbHelper.getWritableDatabase();
    }

    public static synchronized SqliteManager getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new SqliteManager(context.getApplicationContext());
        }

        return sInstance;
    }

    public void insertTransaction(List<Kamus> dictionaries, boolean isEnglish) {
        String DB_TABLE_NAME = isEnglish ? TABLE_ENG_INA : TABLE_INA_ENG;

        String sql = String.format("INSERT INTO %s (%s, %s) VALUES (?, ?)",
                DB_TABLE_NAME,
                COLUMN_WORD,
                COLUMN_MEAN);

        mDb.beginTransaction();

        SQLiteStatement statement = mDb.compileStatement(sql);

        for (Kamus dictionary : dictionaries) {
            statement.bindString(1, dictionary.getWord());
            statement.bindString(2, dictionary.getMean());
            statement.execute();
            statement.clearBindings();
        }

        mDb.setTransactionSuccessful();
        mDb.endTransaction();
    }

    public Cursor selectByWord(String query, boolean isEnglish) {
        String DB_TABLE_NAME = isEnglish ? TABLE_ENG_INA : TABLE_INA_ENG;
        return mDb.rawQuery("SELECT * FROM " + DB_TABLE_NAME +
                " WHERE " + COLUMN_WORD + " LIKE '%" + query.trim() + "%'", null);
    }

    public Cursor selectAllData(boolean isEnglish) {
        String DB_TABLE_NAME = isEnglish ? TABLE_ENG_INA : TABLE_INA_ENG;
        return mDb.rawQuery(String.format("SELECT * FROM %s ORDER BY %s ASC", DB_TABLE_NAME, COLUMN_WORD),
                null);
    }
}
