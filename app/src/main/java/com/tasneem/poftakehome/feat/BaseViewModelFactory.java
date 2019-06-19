package com.tasneem.poftakehome.feat;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.tasneem.poftakehome.repo.Repository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.inject.Inject;

public class BaseViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @NonNull
    @Inject
    public Repository repo;

    public BaseViewModelFactory(Repository repo) {
        this.repo = repo;
    }

    @NonNull
    @Override
    public <BaseViewModel extends ViewModel> BaseViewModel create(@NonNull Class<BaseViewModel> modelClass) {
        try {
            Constructor<BaseViewModel> constructor = modelClass.getConstructor(Repository.class);
            return constructor.newInstance(repo);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return super.create(modelClass);
    }
}
