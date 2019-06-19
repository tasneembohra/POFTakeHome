package com.tasneem.poftakehome.repo.model;

/**
 * Common response from api for all the api , mainly you will get data or error from api.
 * Data could be of any time. You will define while calling the api.
 * @param <T>
 */
public class Resource<T> {
    public T data;
    public ErrorModel error;
    public boolean isEmpty;
    public boolean isError;

    public Resource(T data) {
        isError = false;
        isEmpty = false;
        this.data = data;
    }

    public Resource(ErrorModel error) {
        isError = true;
        isEmpty = false;
        this.error = error;
    }

    public Resource() {
       isEmpty = true;
       isError = false;
    }
}
