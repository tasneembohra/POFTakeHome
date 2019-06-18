package com.tasneem.poftakehome.repo.model;

public class ErrorModel {
    public enum STATE { NETWORK, TIMEOUT, SERVER, INTERNAL_CRASH, INACTIVE_SESSION }

    public STATE type = STATE.NETWORK;

    public ErrorModel(STATE state) {
        type = state;
    }

}
