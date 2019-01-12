package com.susantokun.project3_kamus.ui.kamus;

import com.susantokun.project3_kamus.base.IView;
import com.susantokun.project3_kamus.data.model.Kamus;

import java.util.List;

/**
 * Created by Susantokun on 11 November 2018
 */

public interface IKamusView extends IView {
    void displayData(List<Kamus> dictionaries);
}