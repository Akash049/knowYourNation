package com.project.kyn.knowyournation.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by akashchandra on 11/23/17.
 */

public class Language implements Serializable {

    @SerializedName("iso639_1")
    private String iso639_1;

    @SerializedName("iso639_2")
    private String iso639_2;

    @SerializedName("name")
    private String name;

    @SerializedName("nativeName")
    private String nativeName;

    public Language() {
    }

    public Language(String iso639_1, String iso639_2, String name, String nativeName) {
        this.iso639_1 = iso639_1;
        this.iso639_2 = iso639_2;
        this.name = name;
        this.nativeName = nativeName;
    }

    public String getIso639_1() {
        return iso639_1;
    }

    public void setIso639_1(String iso639_1) {
        this.iso639_1 = iso639_1;
    }

    public String getIso639_2() {
        return iso639_2;
    }

    public void setIso639_2(String iso639_2) {
        this.iso639_2 = iso639_2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }
}
