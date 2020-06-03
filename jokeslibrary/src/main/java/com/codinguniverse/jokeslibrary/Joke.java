package com.codinguniverse.jokeslibrary;

public class Joke {
    private String description;

    public Joke(String description) {
        this.description = description;
    }

    public Joke() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}