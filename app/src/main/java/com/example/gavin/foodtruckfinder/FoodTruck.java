package com.example.gavin.foodtruckfinder;

/**
 * Created by Yui on 8/1/15.
 */
public class FoodTruck {
    private String name;
    private String cuisineType;
    private String latitude;
    private String longitude;
    // Need variable for location

    public FoodTruck( String name, String cuisineType) {
        this.name = name;
        this.cuisineType = cuisineType;
    }

    public String getName() {
        return name;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }
}
