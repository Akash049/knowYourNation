package com.project.kyn.knowyournation.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by akashchandra on 11/23/17.
 */

public class Currencies implements Serializable {

    @SerializedName("code")
    private String code;

    @SerializedName("name")
    private String name;

    @SerializedName("symbol")
    private String symbol;

    public Currencies(String code, String name, String symbol) {
        this.code = code;
        this.name = name;
        this.symbol = symbol;
    }

    public Currencies() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
