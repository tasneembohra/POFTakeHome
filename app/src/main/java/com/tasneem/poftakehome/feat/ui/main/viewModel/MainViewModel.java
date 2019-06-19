package com.tasneem.poftakehome.feat.ui.main.viewModel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Transformations;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import com.tasneem.poftakehome.feat.BaseViewModel;
import com.tasneem.poftakehome.repo.model.CommitDataModel;
import com.tasneem.poftakehome.repo.model.Resource;

import java.util.ArrayList;

// To be set error messages as per the error state - For now hard coded will do the job
public class MainViewModel extends BaseViewModel {

    public ObservableList<Object> list = new ObservableArrayList<>();
    public ObservableBoolean isLoading = new ObservableBoolean(true);
    public ObservableBoolean isError = new ObservableBoolean(false);
    public ObservableBoolean isEmpty = new ObservableBoolean(false);

    public LiveData<Void> loadLD = Transformations.map(repo.commitLiveData, new Function<Resource<ArrayList<CommitDataModel>>, Void>() {
        @Override
        public Void apply(Resource<ArrayList<CommitDataModel>> resource) {
            if (resource == null) return null;

            isLoading.set(false);

            if (resource.isEmpty) {
                isError.set(false);
                isEmpty.set(true);
            } else if (resource.isError) {
                isError.set(true);
                isEmpty.set(false);
            } else {
                // Render list
                isError.set(false);
                isEmpty.set(false);
                list.add(new CommitItemViewModel("Tasneem"));
                list.add(new CommitItemViewModel("Tasneem"));
                list.add(new CommitItemViewModel("Tasneem"));
                list.add(new CommitItemViewModel("Tasneem"));
                list.add(new CommitItemViewModel("Tasneem"));
                list.add(new CommitItemViewModel("Tasneem"));
                list.add(new CommitItemViewModel("Tasneem"));
                list.add(new CommitItemViewModel("Tasneem"));
            }
            return null;
        }
    });

    public void loadData() {
        isLoading.set(true);
        isEmpty.set(false);
        isError.set(false);

        repo.fetchCommits();
    }

}
