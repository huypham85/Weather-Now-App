package com.example.weatherapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    public static final int REQUEST_FROM_MAIN =1;
    private ListView listView;
    Button button;
    WeatherData weatherData = new WeatherData();
    List<WeatherData> items=new ArrayList<WeatherData>();
    ListAdapter adapter = new ListAdapter(items, this);
    public void Send(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivityForResult(intent,REQUEST_FROM_MAIN);
    }

    boolean checkSameCity(String cityName){
        cityName = cityName.toLowerCase();
        cityName = cityName.replace(" ","");
        for(WeatherData temp : items){
            String newCityName = temp.getCity().toLowerCase();
            newCityName = newCityName.replace(" ","");
            if(cityName.equals(newCityName)){
                return true;
            }
        }
        return false;
    }

    void getData(){
        Intent intent = this.getIntent();
    }

    void onClick(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this,MainActivity.class);
                intent.putExtra("temp", items.get(position).getTemp())
                        .putExtra("city",items.get(position).getCity())
                        .putExtra("feel_like",items.get(position).getFeel_like())
                        .putExtra("humid",items.get(position).getHumid())
                        .putExtra("time",items.get(position).getTime())
                        .putExtra("vision",items.get(position).getVision())
                        .putExtra("image", items.get(position).getBitmap())
                        .putExtra("key",2);
                startActivity(intent);
                Toast.makeText(ListActivity.this,"you clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        button = findViewById(R.id.addBtn);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent data = getIntent();
        Bundle bundle = data.getExtras();
        if(bundle!=null){
            String newCity = (String) bundle.get("city");
            if(newCity!=null && checkSameCity(newCity)==false) {
                String newTemp = (String) bundle.get("temp");
                String newVision = (String) bundle.get("vision");
                String newHumid = (String) bundle.get("humid");
                String newTime = (String) bundle.get("time");
                String newFeelLike = (String) bundle.get("feel_like");
                Bitmap newBitmap = (Bitmap) bundle.get("image");
                items.add(new WeatherData(newCity, newTemp, newTime, newVision, newHumid, newFeelLike,newBitmap));
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                onClick();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_FROM_MAIN){
            if(resultCode == Activity.RESULT_OK) {
                String newCity = (String) data.getExtras().get("city");
                if(checkSameCity(newCity)==false) {
                    String newTemp = (String) data.getExtras().get("temp");
                    String newVision = (String) data.getExtras().get("vision");
                    String newHumid = (String) data.getExtras().get("humid");
                    String newTime = (String) data.getExtras().get("time");
                    String newFeelLike = (String) data.getExtras().get("feel_like");
                    Bitmap newBitmap = (Bitmap) data.getExtras().get("image");
                    items.add(new WeatherData(newCity, newTemp, newTime, newVision, newHumid, newFeelLike,newBitmap));
                    adapter.notifyDataSetChanged();
                    listView.setAdapter(adapter);
                    onClick();
                }
            }
        }
    }

}