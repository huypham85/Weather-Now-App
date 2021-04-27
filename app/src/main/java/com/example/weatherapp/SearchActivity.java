package com.example.weatherapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class SearchActivity extends AppCompatActivity {
    ImageButton btn;
    EditText edt;
    String City;
    String Key="f4091b970e2542dc7791f8719512ecb1";
    String txtTime, txtValueFeelLike, txtValueHumidity, txtVision, txtTemp,maxTemp,minTemp,country;
    ImageView imageView;
    Bitmap bitmap;
    public void Search(View view){
        City = edt.getText().toString();
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + City +"&units=metric&appid="+Key;
        MainActivity.DownloadJSON downloadJSON = new MainActivity.DownloadJSON();
        try{
            String result = downloadJSON.execute(url).get();

            //Log.i("URL", url);
            JSONObject jsonObject = new JSONObject(result);
            txtTemp = jsonObject.getJSONObject("main").getString("temp");
            maxTemp = jsonObject.getJSONObject("main").getString("temp_max");
            minTemp=jsonObject.getJSONObject("main").getString("temp_min");
            txtValueHumidity = jsonObject.getJSONObject("main").getString("humidity");
            txtValueFeelLike = jsonObject.getJSONObject("main").getString("feels_like");
            txtVision= jsonObject.getString("visibility");
            Long time = jsonObject.getLong("dt");
            txtTime = new SimpleDateFormat("dd-M-yyyy hh:mm:ss", Locale.ENGLISH).format(new Date(time*1000));
            country = jsonObject.getJSONObject("sys").getString("country");
            //imageView = findViewById(R.id.imgIcon);
            String nameIcon = "10d";
            nameIcon = jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon");
            String urlIcon = "https://openweathermap.org/img/wn/" +nameIcon+ "@2x.png";
            MainActivity.DownloadIcon downloadIcon = new MainActivity.DownloadIcon();
            bitmap = downloadIcon.execute(urlIcon).get();
            //imageView.setImageBitmap(bitmap);
            Log.i("JSON", result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("temp", txtTemp)
                .putExtra("city",City)
                .putExtra("feel_like",txtValueFeelLike)
                .putExtra("humid",txtValueHumidity)
                .putExtra("time",txtTime)
                .putExtra("vision",txtVision)
                .putExtra("image",bitmap)
                .putExtra("maxTemp",maxTemp)
                .putExtra("minTemp",minTemp)
                .putExtra("country",country);
                ;
         SearchActivity.this.startActivityForResult(intent, ListActivity.REQUEST_FROM_MAIN);
         finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_search);
        btn= findViewById(R.id.btn);
        edt = findViewById(R.id.edt);
        getSupportActionBar().setTitle("Search");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ListActivity.REQUEST_FROM_MAIN){
            if(resultCode==Activity.RESULT_OK) {
                Intent intent = new Intent();
                String newCity = (String) data.getExtras().get("city");
                String newTemp = (String) data.getExtras().get("temp");
                String newVision = (String) data.getExtras().get("vision");
                String newHumid = (String) data.getExtras().get("humid");
                String newTime = (String) data.getExtras().get("time");
                String newFeelLike = (String) data.getExtras().get("feel_like");
                Bitmap newBitmap = (Bitmap) data.getExtras().get("image");
                intent.putExtra("city", newCity);
                intent.putExtra("temp", newTemp);
                intent.putExtra("time", newTime);
                intent.putExtra("feel_like", newFeelLike);
                intent.putExtra("humid", newHumid);
                intent.putExtra("vision", newVision);
                intent.putExtra("image",newBitmap);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        }
    }
}