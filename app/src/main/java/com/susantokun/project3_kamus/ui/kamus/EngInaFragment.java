package com.susantokun.project3_kamus.ui.kamus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.susantokun.project3_kamus.R;

import butterknife.OnClick;

/**
 * Created by Susantokun on 11 November 2018
 */

public class EngInaFragment extends KamusFragment {


    public EngInaFragment() {
        // Required empty public constructor
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter.fetchAllData(true);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                presenter.searchDictionaryByWord(charSequence.toString(),true);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @OnClick(R.id.btn_clear)
    void onClearBtnClicked() {
        searchBar.setText("");
        presenter.fetchAllData(true);
    }
}