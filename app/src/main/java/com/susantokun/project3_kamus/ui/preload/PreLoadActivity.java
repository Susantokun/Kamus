package com.susantokun.project3_kamus.ui.preload;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.susantokun.project3_kamus.MainActivity;
import com.susantokun.project3_kamus.R;
import com.susantokun.project3_kamus.base.BaseActivity;
import com.susantokun.project3_kamus.data.preferences.PreferencesHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

public class PreLoadActivity extends BaseActivity implements IMainView{

    @BindView(R.id.progress_bar)
    ProgressBar progressBar;

    private PreloadPresenter presenter ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_load);
        setUnbinder(ButterKnife.bind(this));
        setUp();
    }

    @Override
    protected void setUp() {
        PreLoadInteractor interactor = new PreLoadInteractor(new PreferencesHelper(this), this);
        presenter = new PreloadPresenter(interactor, new CompositeDisposable());
        presenter.onAttach(this);
        presenter.preloadDictionaries();
    }

    @Override
    public void publishProgress(Integer progressValue) {
        progressBar.setProgress(progressValue);
    }

    @Override
    public void onPreLoadCompleted() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
