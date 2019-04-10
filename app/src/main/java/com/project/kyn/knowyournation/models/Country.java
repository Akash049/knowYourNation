package com.project.kyn.knowyournation.models;

import com.google.gson.annotations.SerializedName;


import java.io.Serializable;
import java.util.List;

/**
 * Created by akashchandra on 11/23/17.
 */

public class Country implements Serializable{

    @SerializedName("name")
    private String name;

    @SerializedName("topLevelDomain")
    private List<String> topLevelDomain;

    @SerializedName("alpha2Code")
    private String alpha2Code;

    @SerializedName("alpha3Code")
    private String alpha3Code;

    @SerializedName("callingCodes")
    private List<String> callingCodes;

    @SerializedName("capital")
    private String capital;

    @SerializedName("altSpellings")
    private List<String> altSpellings;

    @SerializedName("region")
    private String region;

    @SerializedName("subregion")
    private String subregion;

    @SerializedName("population")
    private Long population;

    @SerializedName("latlng")
    private List<Double> latlng;

    @SerializedName("demonym")
    private String demonym;

    @SerializedName("area")
    private Double area;

    @SerializedName("gini")
    private float gini;

    @SerializedName("timezones")
    private List<String> timezones;

    @SerializedName("borders")
    private List<String> borders;

    @SerializedName("nativeName")
    private String nativeName;


    @SerializedName("numericCode")
    private String numericCode;

    @SerializedName("currencies")
    private List<Currencies> currencies;

    @SerializedName("languages")
    private List<Language> languages;

    @SerializedName("translations")
    private Translation translation;

    @SerializedName("flag")
    private String flag;

    @SerializedName("regionalBlocs")
    private List<RegionalBlock> regionalBlocks;

    @SerializedName("cioc")
    private String cioc;

    public Country() {
    }


    public Country(String name, List<String> topLevelDomain, String alpha2Code, String alpha3Code, List<String> callingCodes, String capital, List<String> altSpellings, String region, String subregion, Long population, List<Double> latlng, String demonym, double area, float gini, List<String> timezones, List<String> borders, String nativeName, String numericCode, List<Currencies> currencies, List<Language> languages, Translation translation, String flag, List<RegionalBlock> regionalBlocks, String cioc) {
        this.name = name;
        this.topLevelDomain = topLevelDomain;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.callingCodes = callingCodes;
        this.capital = capital;
        this.altSpellings = altSpellings;
        this.region = region;
        this.subregion = subregion;
        this.population = population;
        this.latlng = latlng;
        this.demonym = demonym;
        this.area = area;
        this.gini = gini;
        this.timezones = timezones;
        this.borders = borders;
        this.nativeName = nativeName;
        this.numericCode = numericCode;
        this.currencies = currencies;
        this.languages = languages;
        this.translation = translation;
        this.flag = flag;
        this.regionalBlocks = regionalBlocks;
        this.cioc = cioc;
    }

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<String> getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }

    public List<Double> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<Double> latlng) {
        this.latlng = latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public float getGini() {
        return gini;
    }

    public void setGini(float gini) {
        this.gini = gini;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public List<Currencies> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<Currencies> currencies) {
        this.currencies = currencies;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public Translation getTranslation() {
        return translation;
    }

    public void setTranslation(Translation translation) {
        this.translation = translation;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public List<RegionalBlock> getRegionalBlocks() {
        return regionalBlocks;
    }

    public void setRegionalBlocks(List<RegionalBlock> regionalBlocks) {
        this.regionalBlocks = regionalBlocks;
    }

    public String getCioc() {
        return cioc;
    }

    public void setCioc(String cioc) {
        this.cioc = cioc;
    }
}
