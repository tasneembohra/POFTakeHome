package com.tasneem.poftakehome.dependencies.modules;

import com.tasneem.poftakehome.repo.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {
    @Singleton
    @Provides
    public Repository providesRepository() {
        return new Repository();
    }
}
