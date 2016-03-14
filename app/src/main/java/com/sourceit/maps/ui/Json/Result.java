package com.sourceit.maps.ui.Json;

/**
 * Created by User on 12.03.2016.
 */
public class Result {
    public Geometry geometry;
    public String name;

    @Override
    public boolean equals(Object o) {
        Result p = (Result) o;
        return p.name.equals(name);
    }
}
