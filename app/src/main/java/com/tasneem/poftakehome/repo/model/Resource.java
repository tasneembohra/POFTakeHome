package com.tasneem.poftakehome.repo.model;

public class Resource<T> {
    public T data;
    public ErrorModel error;
    public boolean isEmpty = false;
    public boolean isError = false;

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
