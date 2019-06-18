package com.tasneem.poftakehome.dependencies.component;

import com.tasneem.poftakehome.POFApplication;
import com.tasneem.poftakehome.dependencies.modules.RepositoryModule;

import dagger.Component;

@Component(modules = { RepositoryModule.class } )
public interface AppComponent {
    void inject(POFApplication application);
}
