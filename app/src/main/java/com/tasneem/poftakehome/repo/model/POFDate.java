package com.tasneem.poftakehome.repo.model;


import com.google.gson.annotations.JsonAdapter;
import com.tasneem.poftakehome.repo.network.DateDeserializer;

@JsonAdapter(DateDeserializer.class)
public class POFDate {
    public String date;

    public POFDate(String date) {
        this.date = date;
    }
}
