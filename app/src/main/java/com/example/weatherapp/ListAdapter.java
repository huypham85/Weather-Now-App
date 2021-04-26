package com.example.weatherapp;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    private List<WeatherData> items;
    private Activity activity;
    public ListAdapter(List<WeatherData> items, Activity activity) {
        this.items = items;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.item_name,null);
        TextView txtCity = (TextView)view.findViewById(R.id.txtCity);
        TextView txtTemp = view.findViewById(R.id.txtTemp);
        ImageView imgView = view.findViewById(R.id.imgIcon);
        imgView.setImageBitmap(items.get(position).getBitmap());
        txtCity.setText(items.get(position).getCity());
        txtTemp.setText(items.get(position).getTemp()+"°");
        return view;
    }
}
