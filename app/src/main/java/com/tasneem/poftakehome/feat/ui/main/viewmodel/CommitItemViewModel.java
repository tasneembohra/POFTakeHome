package com.tasneem.poftakehome.feat.ui.main.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.ObservableBoolean;

public class CommitItemViewModel {
    public int position;
    public String name;
    public String message;
    public String date;
    public String imageUrl;
    public String webUrl = "";
    public String sha = "";
    public MutableLiveData<Integer> selectedItemLD = new MutableLiveData<>();
    public ObservableBoolean isSelected = new ObservableBoolean(false);

    public CommitItemViewModel(int position, String name, String imageUrl, String message, String date) {
        this.name = name;
        this.message = message;
        this.date = date;
        this.imageUrl = imageUrl;
        this.position = position;
    }

    public void onClick() {
        if(selectedItemLD.getValue() == null) {
            selectedItemLD.setValue(position);
            isSelected.set(true);
        }
    }
}
