package com.rbhp.geohandbook.data;

import com.google.android.gms.maps.model.LatLng;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttractionData {
    private String name;
    private String description;
    private String imageUrl;
    private LatLng coordinates;
}
