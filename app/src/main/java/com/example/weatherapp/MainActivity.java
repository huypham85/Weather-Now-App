  package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.AsyncTaskLoader;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    String City="London";
    String Key="f4091b970e2542dc7791f8719512ecb1";

    public static class DownloadJSON extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            URL url ;
            HttpURLConnection httpURLConnection;
            InputStream inputStream;
            InputStreamReader inputStreamReader;
            String result = "";
            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                inputStream=httpURLConnection.getInputStream();
                inputStreamReader=new InputStreamReader(inputStream);
                int data = inputStreamReader.read();
                while(data!=-1){
                    result += (char)data;
                    data=inputStreamReader.read();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }
    public static class DownloadIcon extends AsyncTask<String, Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            Bitmap bitmap = null;
            URL url ;
            HttpURLConnection httpURLConnection;
            InputStream inputStream;

            try {
                url = new URL(strings[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                inputStream=httpURLConnection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmap;
        }
    }
    TextView txtCity, txtTime, txtValueFeelLike, txtValueHumidity, txtVision, txtTemp;
    String city, temp;
    ImageView imageView;
//    public void Search(View view){
//        City = edt.getText().toString();
//        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + City +"&units=metric&appid="+Key;
//        edt.setVisibility((View.INVISIBLE));
//        btn.setVisibility(View.INVISIBLE);
//        rlWeather.setVisibility(View.VISIBLE);
//        DownloadJSON downloadJSON = new DownloadJSON();
//        try{
//            String result = downloadJSON.execute(url).get();
//
//            //Log.i("URL", url);
//            JSONObject jsonObject = new JSONObject(result);
//            String temp = jsonObject.getJSONObject("main").getString("temp");
//            String humidity = jsonObject.getJSONObject("main").getString("humidity");
//            String feel_Like = jsonObject.getJSONObject("main").getString("feels_like");
//            String visibility = jsonObject.getString("visibility");
//            Long time = jsonObject.getLong("dt");
//            String sTime = new SimpleDateFormat("dd-M-yyyy hh:mm:ss", Locale.ENGLISH).format(new Date(time*1000));
//            txtTime.setText(sTime);
//            txtCity.setText(City);
//            txtVision.setText(visibility+" m");
//            txtValueFeelLike.setText(feel_Like+"°");
//            txtValueHumidity.setText(humidity+"%");
//            txtTemp.setText(temp+"°");
//            imageView = findViewById(R.id.imgIcon);
//            String nameIcon = "10d";
//            nameIcon = jsonObject.getJSONArray("weather").getJSONObject(0).getString("icon");
//            String urlIcon = "https://openweathermap.org/img/wn/" +nameIcon+ "@2x.png";
//            DownloadIcon downloadIcon = new DownloadIcon();
//            Bitmap bitmap = downloadIcon.execute(urlIcon).get();
//            imageView.setImageBitmap(bitmap);
//            Log.i("JSON", result);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    SearchActivity searchActivity;
    public void Back(View view){
        Button backBtn = findViewById(R.id.backBtn);
        Intent intent = getIntent();
        String newCity = (String) intent.getExtras().get("city");
        String newTemp = (String) intent.getExtras().get("temp");
        String newVision = (String) intent.getExtras().get("vision");
        String newHumid = (String) intent.getExtras().get("humid");
        String newTime = (String) intent.getExtras().get("time");
        String newFeelLike = (String) intent.getExtras().get("feel_like");
        Bitmap newBitmap = (Bitmap) intent.getExtras().get("image");
        intent.putExtra("city",newCity);
        intent.putExtra("temp",newTemp);
        intent.putExtra("time", newTime);
        intent.putExtra("feel_like", newFeelLike);
        intent.putExtra("humid", newHumid);
        intent.putExtra("vision", newVision);
        intent.putExtra("image", newBitmap);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }

    void setData(){
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        city = bundle.getString("city");
        temp = bundle.getString("temp");
        txtCity.setText(bundle.getString("city"));
        txtTemp.setText(bundle.getString("temp")+"°");
        txtTime.setText(bundle.getString("time"));
        txtValueHumidity.setText(bundle.getString("humid")+"%");
        txtValueFeelLike.setText(bundle.getString("feel_like")+"°");
        txtVision.setText(bundle.getString("vision")+"m");
        imageView.setImageBitmap((Bitmap) intent.getExtras().get("image"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        txtCity=findViewById(R.id.txtCity);
        txtTime=findViewById(R.id.txtTime);
        txtValueFeelLike=findViewById(R.id.txtValueFeel);
        txtValueHumidity=findViewById(R.id.txtValueHumidity);
        txtVision=findViewById(R.id.txtValueView);
        txtTemp=findViewById(R.id.txtValue);
        imageView=findViewById(R.id.imgIcon);
        setData();
    }
}