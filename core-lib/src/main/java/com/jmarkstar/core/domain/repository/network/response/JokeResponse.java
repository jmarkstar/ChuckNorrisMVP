package com.jmarkstar.core.domain.repository.network.response;

/**
 * Created by jmarkstar on 30/05/2017.
 */

public class JokeResponse<T> {

    private String type;
    private T value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
