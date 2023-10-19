package com.freshshare.entity;

import lombok.Data;

import java.util.List;

@Data
public class Geocode {

    private List<Result> results;

    private String status;

    public static class Result {
        private List<AddressComponent> address_components;
        private String formatted_address;
        private Geometry geometry;
        private String place_id;
        private PlusCode plus_code;
        private List<String> types;
    }

    public static class AddressComponent {
        private String long_name;
        private String short_name;
        private List<String> types;
    }

    public static class Geometry {
        private Location location;
        private String location_type;
        private Viewport viewport;
    }

    public static class Location {
        private double lat;
        private double lng;

    }

    public static class Viewport {
        private Location northeast;
        private Location southwest;

    }

    public static class PlusCode {
        private String compound_code;
        private String global_code;

    }

}
