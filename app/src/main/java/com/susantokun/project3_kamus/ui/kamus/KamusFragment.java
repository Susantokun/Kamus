package com.susantokun.project3_kamus.ui.kamus;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.susantokun.project3_kamus.R;
import com.susantokun.project3_kamus.base.BaseFragment;
import com.susantokun.project3_kamus.base.BaseRvAdapter;
import com.susantokun.project3_kamus.base.listener.RecyclerViewItemClickListener;
import com.susantokun.project3_kamus.data.model.Kamus;
import com.susantokun.project3_kamus.data.preferences.PreferencesHelper;
import com.susantokun.project3_kamus.ui.detail.KamusDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;

/**
 * A simple {@link Fragment} subclass.
 */

public class KamusFragment extends BaseFragment implements IKamusView, RecyclerViewItemClickListener {

    protected KamusPresenter presenter;
    @BindView(R.id.rv_kamus)
    RecyclerView recyclerView;
    @BindView(R.id.et_search_bar)
    protected EditText searchBar;

    private List<Kamus> kamus = new ArrayList<>();
    private BaseRvAdapter<Kamus, KamusView> adapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        IKamusInteractor interactor = new KamusInteractor(new PreferencesHelper(getContext()), getContext());
        presenter = new KamusPresenter(interactor, new CompositeDisposable());
        presenter.onAttach(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_kamus, container, false);
        setUnbinder(ButterKnife.bind(this, view));

        return view;
    }

    @Override
    protected void setUp(View view) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new BaseRvAdapter<Kamus, KamusView>
                (R.layout.kamus_item, KamusView.class, kamus, this) {
            @Override
            protected void bindView(KamusView holder, Kamus kamus, int position) {
                holder.bind(kamus);
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayData(List<Kamus> kamus) {
        this.kamus.clear();
        this.kamus.addAll(kamus);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(getContext(), KamusDetailActivity.class);
        intent.putExtra(KamusDetailActivity.DICTIONARY_INTENT, kamus.get(position));
        startActivity(intent);
    }
}
