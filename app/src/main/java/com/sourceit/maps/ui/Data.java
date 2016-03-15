package com.sourceit.maps.ui;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.sourceit.maps.App;
import com.sourceit.maps.R;
import com.sourceit.maps.ui.Json.Bars;
import com.sourceit.maps.utils.L;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by User on 13.03.2016.
 */
public class Data {

    public static Bars barsList = new Bars();

    private static Context context = App.getApp();
    private final static int ID = 0;
    private final static int ID2 = 1;

    private static boolean select;

    public static void retrofit(Location location) {
        Retrofit.getBars(location.getLatitude(), location.getLongitude(), new Callback<Bars>() {
            @Override
            public void success(Bars bars, Response response) {

                if (barsList.results.size() == bars.results.size()) {
                    L.d("containsAll " + barsList.results.equals(bars.results));
                    if (!barsList.results.containsAll(bars.results)) {
                        select = false;
                    }
                } else {
                    select = false;
                }
                if (!select) {
                    barsList = bars;
                    notification();

                    select = true;
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(context, "something went wrong :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private static void notification() {
        Intent notificationIntent = new Intent(context, ListActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context,
                ID, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationManager nm = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Бары в радиусе 300 метров")
                .setContentText("найдено " + barsList.results.size() + " баров");
        Notification n = builder.build();
        nm.notify(ID, n);

        if (barsList.results.size() != 0) {
            notificationIntent = new Intent(context, MapActivity.class);
            contentIntent = PendingIntent.getActivity(context,
                    ID2, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);
            builder.setContentIntent(contentIntent)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("Бары на карте")
                    .setContentText("посмотреть");
            n = builder.build();
            nm.notify(ID2, n);
        }
    }
}
