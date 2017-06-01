package com.jmarkstar.chucknorris.domain.model;

import java.util.ArrayList;

/**
 * Created by jmarkstar on 31/05/2017.
 */
public class JokeModel {

    private Integer id;
    private String joke;
    private ArrayList<String> categories;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public ArrayList<String> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<String> categories) {
        this.categories = categories;
    }
}
