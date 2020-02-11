package com.example.quizme;

import androidx.annotation.NonNull;

public class Categories {

    public static final int SPORTS = 1;
    public static final int GK = 2;
    public static final int HISTORY = 3;

    private int id;
    private String name;

    public Categories(String name){
        this.name = name;
    }

    public Categories() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NonNull
    @Override
    public String toString() {
        return getName();
    }
}
