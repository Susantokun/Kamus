package com.susantokun.project3_kamus.ui.detail;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.susantokun.project3_kamus.R;
import com.susantokun.project3_kamus.base.BaseActivity;
import com.susantokun.project3_kamus.data.model.Kamus;

import butterknife.BindView;
import butterknife.ButterKnife;

public class KamusDetailActivity extends BaseActivity {

    public static final String DICTIONARY_INTENT = "kamus.intent";

    @BindView(R.id.tv_word)
    TextView tvWord;
    @BindView(R.id.tv_mean)
    TextView tvMean;
    @BindView(R.id.tv_translate)
    TextView tvTranslate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamus_detail);
        setUnbinder(ButterKnife.bind(this));
        setUp();
    }

    @Override
    protected void setUp() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Kamus kamus = getIntent().getParcelableExtra(DICTIONARY_INTENT);
        showDetails(kamus);
    }

    private void showDetails(Kamus kamus) {
        tvWord.setText(kamus.getWord());
        tvMean.setText(kamus.getMean());
        tvTranslate.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
