package com.susantokun.project3_kamus.data.sqlite;

import android.provider.BaseColumns;

/**
 * Created by Susantokun on 11 November 2018
 */

public class SqliteContract {
    public static final String TABLE_ENG_INA = "table_eng_ina";
    public static final String TABLE_INA_ENG = "table_ina_eng";

    public static final class DictionaryColumns implements BaseColumns {
        public static final String COLUMN_WORD = "word";
        public static final String COLUMN_MEAN = "mean";
    }
}
