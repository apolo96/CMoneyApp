package com.example.apolo.cmoneyapp;

/**
 * Created by Apolo on 07/09/2016.
 */
public class Divisa {
    private String id;
    private String name;

    public Divisa(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String toString() {
        return name;
    }
}
