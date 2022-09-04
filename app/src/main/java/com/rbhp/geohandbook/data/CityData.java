package com.rbhp.geohandbook.data;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

public class CityData {
    private String name;
    private String description;
    private String population;
    private String size;
    private LatLng coordinates;
    private List<String> imageUrls;
    private String videoUrl;

    public CityData(String name) {
        this.name = name;
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

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LatLng coordinates) {
        this.coordinates = coordinates;
    }

    public List<String> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Override
    public String toString() {
        return "CityData{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", population=" + population +
                ", size=" + size +
                ", coordinates=" + coordinates +
                ", imageUrlList=" + imageUrls +
                ", videoUrl='" + videoUrl + '\'' +
                '}';
    }
}
