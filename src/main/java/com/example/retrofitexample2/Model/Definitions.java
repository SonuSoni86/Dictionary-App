package com.example.retrofitexample2.Model;

import com.google.gson.annotations.SerializedName;

public class Definitions {

    @SerializedName("definition")
    private String definition;

    public String getDefinition() {
        return definition;
    }

    public String getPartOfSpeach() {
        return partOfSpeach;
    }

    @SerializedName("partOfSpeach")
    private String partOfSpeach;

}
