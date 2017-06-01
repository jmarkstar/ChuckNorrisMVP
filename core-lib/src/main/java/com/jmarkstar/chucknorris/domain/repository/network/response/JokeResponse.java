package com.jmarkstar.chucknorris.domain.repository.network.response;

import com.jmarkstar.chucknorris.domain.model.JokeModel;
import java.util.ArrayList;

/**
 * Created by jmarkstar on 30/05/2017.
 */

public class JokeResponse {

    private String type;
    private ArrayList<JokeModel> value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<JokeModel> getValue() {
        return value;
    }

    public void setValue(ArrayList<JokeModel> value) {
        this.value = value;
    }
}
