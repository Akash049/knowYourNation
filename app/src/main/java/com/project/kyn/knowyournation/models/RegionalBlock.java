package com.project.kyn.knowyournation.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by akashchandra on 11/23/17.
 */

public class RegionalBlock implements Serializable {

    @SerializedName("acronym")
    private String acronym;

    @SerializedName("name")
    private String name;

    @SerializedName("otherAcronyms")
    private List<String> otherAcronyms;

    @SerializedName("otherNames")
    private List<String> otherNames;

    public RegionalBlock() {
    }


    public RegionalBlock(String acronym, String name, List<String> otherAcronyms, List<String> otherNames) {
        this.acronym = acronym;
        this.name = name;
        this.otherAcronyms = otherAcronyms;
        this.otherNames = otherNames;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOtherAcronyms() {
        return otherAcronyms;
    }

    public void setOtherAcronyms(List<String> otherAcronyms) {
        this.otherAcronyms = otherAcronyms;
    }

    public List<String> getOtherNames() {
        return otherNames;
    }

    public void setOtherNames(List<String> otherNames) {
        this.otherNames = otherNames;
    }
}
