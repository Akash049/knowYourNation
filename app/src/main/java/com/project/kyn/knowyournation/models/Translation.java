package com.project.kyn.knowyournation.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by akashchandra on 11/23/17.
 */

public class Translation implements Serializable {

    @SerializedName("de")
    private String de;

    @SerializedName("es")
    private String es;

    @SerializedName("fr")
    private String fr;

    @SerializedName("ja")
    private String ja;

    @SerializedName("it")
    private String it;

    @SerializedName("br")
    private String br;

    @SerializedName("pt")
    private String pt;

    @SerializedName("nl")
    private String nl;

    @SerializedName("hr")
    private String hr;

    @SerializedName("fa")
    private String fa;

    public Translation() {
    }

    public Translation(String de, String es, String fr, String ja, String it, String br, String pt, String nl, String hr, String fa) {
        this.de = de;
        this.es = es;
        this.fr = fr;
        this.ja = ja;
        this.it = it;
        this.br = br;
        this.pt = pt;
        this.nl = nl;
        this.hr = hr;
        this.fa = fa;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getEs() {
        return es;
    }

    public void setEs(String es) {
        this.es = es;
    }

    public String getFr() {
        return fr;
    }

    public void setFr(String fr) {
        this.fr = fr;
    }

    public String getJa() {
        return ja;
    }

    public void setJa(String ja) {
        this.ja = ja;
    }

    public String getIt() {
        return it;
    }

    public void setIt(String it) {
        this.it = it;
    }

    public String getBr() {
        return br;
    }

    public void setBr(String br) {
        this.br = br;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public String getNl() {
        return nl;
    }

    public void setNl(String nl) {
        this.nl = nl;
    }

    public String getHr() {
        return hr;
    }

    public void setHr(String hr) {
        this.hr = hr;
    }

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }
}
