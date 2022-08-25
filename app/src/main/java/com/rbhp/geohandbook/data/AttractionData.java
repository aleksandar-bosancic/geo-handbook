package com.rbhp.geohandbook.data;

import com.google.android.gms.maps.model.LatLng;

public class AttractionData {
    private String name;
    private String description;
    private String imageUrl;
    private LatLng coordinates;
    private boolean favourite;

    public AttractionData() {
    }

    public AttractionData(String name, String description, String imageUrl, LatLng coordinates) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LatLng coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isFavourite() {
        return favourite;
    }

    public void setFavourite(boolean favourite) {
        this.favourite = favourite;
    }
}
