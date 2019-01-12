package com.susantokun.project3_kamus.ui.kamus;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.susantokun.project3_kamus.R;
import com.susantokun.project3_kamus.data.model.Kamus;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Susantokun on 11 November 2018
 */

public class KamusView extends RecyclerView.ViewHolder {

    @BindView(R.id.tv_word)
    TextView tvWord ;
    @BindView((R.id.tv_mean))
    TextView tvMean ;

    public KamusView(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Kamus kamus) {
        tvWord.setText(kamus.getWord());
        tvMean.setText(kamus.getMean());
        tvMean.setMaxLines(2);
    }
}
