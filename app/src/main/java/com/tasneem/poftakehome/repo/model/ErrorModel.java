package com.tasneem.poftakehome.repo.model;

/**
 * Idea is to have error object from sever in case of 400 code
 * And set the status as per the retrofit failure, for now just setting as SERVER or NETWORK error.
 */
public class ErrorModel {
    public enum STATE { NETWORK, TIMEOUT, SERVER, INTERNAL_CRASH, INACTIVE_SESSION }

    public final STATE type;

    public ErrorModel(STATE state) {
        type = state;
    }

}
