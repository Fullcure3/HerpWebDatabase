package com.SotoPorfolio.HerpWebDatabase;

public class Herp {
    private String classes;
    private String commonName;
    private String genus;
    private String species;
    private String activity;

    public Herp(String classes, String commonName, String genus, String species, String activity) {
        this.classes = classes;
        this.commonName = commonName;
        this.genus = genus;
        this.species = species;
        this.activity = activity;
    }

    public String getClasses() {
        return classes;
    }

    public String getCommonName() {
        return commonName;
    }

    public String getGenus() {
        return genus;
    }

    public String getSpecies() {
        return species;
    }

    public String getActivity() {
        return activity;
    }
}
