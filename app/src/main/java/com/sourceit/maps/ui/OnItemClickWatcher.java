package com.sourceit.maps.ui;

import android.view.View;

/**
 * Created by User on 14.03.2016.
 */
public abstract class OnItemClickWatcher<T> {
    public abstract void onItemClick(View v, int position, T item);
}
