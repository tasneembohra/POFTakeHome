package com.tasneem.poftakehome.dependencies.component;

import com.tasneem.poftakehome.POFApplication;
import com.tasneem.poftakehome.dependencies.modules.RepositoryModule;
import com.tasneem.poftakehome.feat.BaseViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { RepositoryModule.class } )
public interface AppComponent {
    void inject(BaseViewModel baseViewModel);
}
