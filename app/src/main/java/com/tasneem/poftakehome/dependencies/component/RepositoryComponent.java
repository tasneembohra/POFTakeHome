package com.tasneem.poftakehome.dependencies.component;

import com.tasneem.poftakehome.dependencies.modules.ApiModule;
import com.tasneem.poftakehome.repo.Repository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = { ApiModule.class } )
public interface RepositoryComponent {
    void inject(Repository repository);
}
