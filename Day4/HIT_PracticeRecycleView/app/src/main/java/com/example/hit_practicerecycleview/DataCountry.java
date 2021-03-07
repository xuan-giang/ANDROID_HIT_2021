package com.example.hit_practicerecycleview;

public class DataCountry {
    private int image;
    private String nameCountry;
    private String numberOfDeath, numberOfRecovered, numberOfConfirmed;

    public DataCountry() {
    }

    public DataCountry(int image, String nameCountry, String numberOfConfirmed, String numberOfDeath, String numberOfRecovered) {
        this.image = image;
        this.nameCountry = nameCountry;
        this.numberOfDeath = numberOfDeath;
        this.numberOfRecovered = numberOfRecovered;
        this.numberOfConfirmed = numberOfConfirmed;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getNameCountry() {
        return nameCountry;
    }

    public void setNameCountry(String nameCountry) {
        this.nameCountry = nameCountry;
    }

    public String getNumberOfDeath() {
        return numberOfDeath;
    }

    public void setNumberOfDeath(String numberOfDeath) {
        this.numberOfDeath = numberOfDeath;
    }

    public String getNumberOfRecovered() {
        return numberOfRecovered;
    }

    public void setNumberOfRecovered(String numberOfRecovered) {
        this.numberOfRecovered = numberOfRecovered;
    }

    public String getNumberOfConfirmed() {
        return numberOfConfirmed;
    }

    public void setNumberOfConfirmed(String numberOfConfirmed) {
        this.numberOfConfirmed = numberOfConfirmed;
    }
}
