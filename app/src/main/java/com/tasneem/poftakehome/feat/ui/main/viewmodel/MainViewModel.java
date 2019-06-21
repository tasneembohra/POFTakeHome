package com.tasneem.poftakehome.feat.ui.main.viewmodel;

import android.arch.core.util.Function;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.Transformations;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;

import com.tasneem.poftakehome.feat.BaseViewModel;
import com.tasneem.poftakehome.repo.model.CommitRootDataModel;
import com.tasneem.poftakehome.repo.model.Resource;

import java.util.ArrayList;

// To be set error messages as per the error state - For now hard coded will do the job
public class MainViewModel extends BaseViewModel {

    public ObservableList<Object> list = new ObservableArrayList<>();
    public ObservableBoolean showLoadingScr = new ObservableBoolean(true);
    public ObservableBoolean showErrorSrc = new ObservableBoolean(false);
    public ObservableBoolean showEmptyScr = new ObservableBoolean(false);
    public ObservableBoolean showDataScr = new ObservableBoolean(false);
    public MutableLiveData<Integer> selectedItem = new MutableLiveData<>();

    public LiveData<Void> loadLD = Transformations.map(repo.commitLiveData, new Function<Resource<ArrayList<CommitRootDataModel>>, Void>() {
        @Override
        public Void apply(Resource<ArrayList<CommitRootDataModel>> resource) {
            if (resource == null) return null;

            showLoadingScr.set(false);

            if (resource.isEmpty) {
                showErrorSrc.set(false);
                showEmptyScr.set(true);
                showDataScr.set(false);
            } else if (resource.isError) {
                showErrorSrc.set(true);
                showEmptyScr.set(false);
                showDataScr.set(false);
            } else {
                // Render list
                showErrorSrc.set(false);
                showEmptyScr.set(false);
                int i = 0;
                for (CommitRootDataModel data: resource.data) {
                    // Format date
                    CommitItemViewModel viewModel = new CommitItemViewModel(i, data.author.userName, data.author.avatarUrl, data.commit.message, data.commit.author.date.date);
                    viewModel.selectedItemLD = selectedItem;
                    viewModel.webUrl =  data.htmlUrl;
                    viewModel.sha = data.sha;
                    list.add(viewModel);
                    i++;
                }
                showDataScr.set(true);
            }
            return null;
        }
    });

    public void loadData() {
        showLoadingScr.set(true);
        showEmptyScr.set(false);
        showErrorSrc.set(false);
        showDataScr.set(false);

        repo.fetchCommits();
    }

    public CommitItemViewModel getSelectedItemViewModel(int position) {
        for (Object o: list) {
            if (o instanceof CommitItemViewModel) {
                CommitItemViewModel vm = (CommitItemViewModel)o;
                if (vm.position == position) return vm;
            }
        }
        return null;
    }

}
