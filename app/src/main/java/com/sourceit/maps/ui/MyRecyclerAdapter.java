package com.sourceit.maps.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sourceit.maps.R;

import java.util.ArrayList;

/**
 * Created by User on 14.03.2016.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<String> objects;
    private OnItemClickWatcher<String> watcher;

    public MyRecyclerAdapter(ArrayList<String> objects, OnItemClickWatcher<String> watcher) {
        this.objects = objects;
        this.watcher = watcher;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.bar, parent, false);
        return new ViewHolder(v, watcher, objects);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(objects.get(position));
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    @Override
    public void onClick(View v) {
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;

        public ViewHolder(View item, final OnItemClickWatcher<String> watcher, final ArrayList<String> objects) {
            super(item);
            name = (TextView) item.findViewById(R.id.bar_name);
            item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    watcher.onItemClick(v, getAdapterPosition(), objects.get(getAdapterPosition()));
                }
            });
        }
    }
}
