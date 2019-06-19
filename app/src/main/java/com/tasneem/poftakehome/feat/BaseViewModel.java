package com.tasneem.poftakehome.feat;

import android.arch.lifecycle.ViewModel;

import com.tasneem.poftakehome.dependencies.component.DaggerAppComponent;
import com.tasneem.poftakehome.dependencies.modules.RepositoryModule;
import com.tasneem.poftakehome.repo.Repository;

import javax.inject.Inject;

public class BaseViewModel extends ViewModel {

    @Inject
    public Repository repo;

    public BaseViewModel() {
        DaggerAppComponent.builder()
                .repositoryModule(new RepositoryModule())
                .build().inject(this);
    }
}
