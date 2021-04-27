package com.example.weatherapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
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
    ImageButton button;
    WeatherData weatherData = new WeatherData();
    static List<WeatherData> items=new ArrayList<WeatherData>();
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

    void onClick(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListActivity.this,MainActivity.class);
                intent.putExtra("temp", items.get(position).getTemp())
                        .putExtra("maxTemp",items.get(position).getMaxTemp())
                        .putExtra("minTemp",items.get(position).getMinTemp())
                        .putExtra("city",items.get(position).getCity())
                        .putExtra("feel_like",items.get(position).getFeel_like())
                        .putExtra("humid",items.get(position).getHumid())
                        .putExtra("time",items.get(position).getTime())
                        .putExtra("vision",items.get(position).getVision())
                        .putExtra("image", items.get(position).getBitmap())
                        .putExtra("country",items.get(position).getCountry())
                        .putExtra("key",2);
                startActivity(intent);
                Toast.makeText(ListActivity.this,"you clicked", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void onLongClick(){
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int which_item = position;
                new AlertDialog.Builder(ListActivity.this)
                        .setIcon(android.R.drawable.ic_delete)
                        .setTitle("")
                        .setMessage("Do you want to delete this city")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                items.remove(which_item);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("Home");
        setContentView(R.layout.activity_list);
        button = findViewById(R.id.addBtn);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        onClick();
        onLongClick();
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
                String newMaxTemp= (String) bundle.get("maxTemp");
                String newMinTemp= (String) bundle.get("minTemp");
                String newVision = (String) bundle.get("vision");
                String newHumid = (String) bundle.get("humid");
                String newTime = (String) bundle.get("time");
                String newFeelLike = (String) bundle.get("feel_like");
                Bitmap newBitmap = (Bitmap) bundle.get("image");
                String newCountry = (String) bundle.get("country");
                items.add(new WeatherData(newCity, newTemp,newMaxTemp,newMinTemp, newTime, newVision, newHumid, newFeelLike,newBitmap,newCountry));
                adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                Log.e("Items 's size", items.size()+"");
            }
        }
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if(requestCode == REQUEST_FROM_MAIN){
//            if(resultCode == Activity.RESULT_OK) {
//                String newCity = (String) data.getExtras().get("city");
//                if(checkSameCity(newCity)==false) {
//                    String newTemp = (String) data.getExtras().get("temp");
//                    String newVision = (String) data.getExtras().get("vision");
//                    String newHumid = (String) data.getExtras().get("humid");
//                    String newTime = (String) data.getExtras().get("time");
//                    String newFeelLike = (String) data.getExtras().get("feel_like");
//                    Bitmap newBitmap = (Bitmap) data.getExtras().get("image");
//                    items.add(new WeatherData(newCity, newTemp, newTime, newVision, newHumid, newFeelLike,newBitmap));
//                    adapter.notifyDataSetChanged();
//                    listView.setAdapter(adapter);
//                    onClick();
//                }
//            }
//        }
//    }

}