package com.sourceit.maps.ui;

import com.sourceit.maps.ui.Json.Bars;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by User on 13.03.2016.
 */
public class Retrofit {
    private static final String ENDPOINT = "https://maps.googleapis.com/maps/api/place/search/";
    private static ApiInterface apiInterface;

    static {
        initialize();
    }

    interface ApiInterface {
        @GET("/{request}")
        void getBars(@Path(value = "request", encode = false) String name, Callback<Bars> callback);
    }

    public static void initialize() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(ENDPOINT)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .build();
        apiInterface = restAdapter.create(ApiInterface.class);
    }

    public static void getBars(double lat, double lng, Callback<Bars> callback) {
        apiInterface.getBars("json?location=" + String.valueOf(lat) + "," + String.valueOf(lng) + "&radius=300&types=bar&key=AIzaSyCkiSAwsdlDRiyg8IOJeNXtgMuteq2cz98", callback);
    }
}
