package com.example.retrofitexample2.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Meaning {

    @SerializedName("word")
    private String word;

    @SerializedName("definitions")
    private List<Definitions>definitions;

    public String getWord() {
        return word;
    }

    public List<Definitions> getDefinitions() {
        return definitions;
    }

    public Meaning(List<Definitions> definitions) {
        this.definitions = definitions;
    }

    public Meaning() {
    }
}
