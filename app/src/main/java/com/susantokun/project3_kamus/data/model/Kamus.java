package com.susantokun.project3_kamus.data.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import static android.provider.BaseColumns._ID;
import static com.susantokun.project3_kamus.data.sqlite.SqliteContract.DictionaryColumns.*;

/**
 * Created by Susantokun on 11 November 2018
 */

public class Kamus implements Parcelable{
    private int id ;
    private String word ;
    private String mean ;

    public Kamus(String word, String mean) {
        this.word = word;
        this.mean = mean;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMean() {
        return mean;
    }

    public void setMean(String mean) {
        this.mean = mean;
    }

    public Kamus(Cursor cursor) {
        this.id = cursor.getInt(cursor.getColumnIndexOrThrow(_ID));
        this.word = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_WORD));
        this.mean = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MEAN));
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.word);
        dest.writeString(this.mean);
    }

    protected Kamus(Parcel in) {
        this.id = in.readInt();
        this.word = in.readString();
        this.mean = in.readString();
    }

    public static final Parcelable.Creator<Kamus> CREATOR = new Parcelable.Creator<Kamus>() {
        @Override
        public Kamus createFromParcel(Parcel source) {
            return new Kamus(source);
        }

        @Override
        public Kamus[] newArray(int size) {
            return new Kamus[size];
        }
    };
}
