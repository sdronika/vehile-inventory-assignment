package com.vehicles.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;




public class Vehicle implements Comparable {

    @JsonProperty(value = "Type")
    private String type;

    @JsonProperty(value = "VIN")
    private String VIN;

    @JsonProperty(value = "Brand")
    private String brand;

    @JsonProperty(value = "Color")
    private String color;

    @JsonProperty(value = "EngineType")
    private String engineType;

    @JsonProperty(value = "Price")
    private long price;

    @JsonProperty(value = "Year")
    private long makeYear;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVIN() {
        return VIN;
    }

    public void setVIN(String VIN) {
        this.VIN = VIN;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getMakeYear() {
        return makeYear;
    }

    public void setMakeYear(long makeYear) {
        this.makeYear = makeYear;
    }

    @Override
    public int compareTo(Object o) {
        Vehicle other = (Vehicle) o;

        if (this.price > other.getPrice()) {
            return 1;
        }

        if (this.price < other.getPrice()) {
            return -1;
        }

        if (this.price == other.getPrice()) {
            return 0;
        }
        return 0;
    }
}
