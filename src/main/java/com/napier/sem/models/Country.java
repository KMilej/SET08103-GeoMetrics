package com.napier.sem.models;

import java.math.BigDecimal;

public class Country {
    private String code;
    private String name;
    private String continent;
    private String region;
    private BigDecimal surfaceArea;
    private Short indepYear;
    private int population;
    private BigDecimal lifeExpectancy;
    private BigDecimal gnp;
    private BigDecimal gnpOld;
    private String localName;
    private String governmentForm;
    private String headOfState;
    private Integer capital;
    private String code2;

    public Country(String code, String name, String continent, String region, BigDecimal surfaceArea, Short indepYear, int population, BigDecimal lifeExpectancy, BigDecimal gnp, BigDecimal gnpOld, String localName, String governmentForm, String headOfState, Integer capital, String code2) {
        this.code = code;
        this.name = name;
        this.continent = continent;
        this.region = region;
        this.surfaceArea = surfaceArea;
        this.indepYear = indepYear;
        this.population = population;
        this.lifeExpectancy = lifeExpectancy;
        this.gnp = gnp;
        this.gnpOld = gnpOld;
        this.localName = localName;
        this.governmentForm = governmentForm;
        this.headOfState = headOfState;
        this.capital = capital;
        this.code2 = code2;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getContinent() {
        return continent;
    }

    public String getRegion() {
        return region;
    }

    public BigDecimal getSurfaceArea() {
        return surfaceArea;
    }

    public Short getIndepYear() {
        return indepYear;
    }

    public int getPopulation() {
        return population;
    }

    public BigDecimal getLifeExpectancy() {
        return lifeExpectancy;
    }

    public BigDecimal getGnp() {
        return gnp;
    }

    public BigDecimal getGnpOld() {
        return gnpOld;
    }

    public String getLocalName() {
        return localName;
    }

    public String getGovernmentForm() {
        return governmentForm;
    }

    public String getHeadOfState() {
        return headOfState;
    }

    public Integer getCapital() {
        return capital;
    }

    public String getCode2() {
        return code2;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", continent='" + continent + '\'' +
                ", region='" + region + '\'' +
                ", surfaceArea=" + surfaceArea +
                ", indepYear=" + indepYear +
                ", population=" + population +
                ", lifeExpectancy=" + lifeExpectancy +
                ", gnp=" + gnp +
                ", gnpOld=" + gnpOld +
                ", localName='" + localName + '\'' +
                ", governmentForm='" + governmentForm + '\'' +
                ", headOfState='" + headOfState + '\'' +
                ", capital=" + capital +
                ", code2='" + code2 + '\'' +
                '}';
    }
}
