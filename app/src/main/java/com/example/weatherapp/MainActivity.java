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
import android.widget.ImageButton;
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
    TextView txtCity, txtTime, txtValueFeelLike, txtValueHumidity, txtVision, txtTemp,txtMinTemp,txtMaxTemp;
    String city, temp;
    ImageView imageView;
    SearchActivity searchActivity;
    public void Back(View view){
        ImageButton backBtn = findViewById(R.id.backBtn);
        Intent intent = getIntent();
        String newCity = (String) intent.getExtras().get("city");
        String newTemp = (String) intent.getExtras().get("temp");
        String newMaxTemp = (String) intent.getExtras().get("maxTemp");
        String newMinTemp = (String) intent.getExtras().get("minTemp");
        String newVision = (String) intent.getExtras().get("vision");
        String newHumid = (String) intent.getExtras().get("humid");
        String newTime = (String) intent.getExtras().get("time");
        String newFeelLike = (String) intent.getExtras().get("feel_like");
        Bitmap newBitmap = (Bitmap) intent.getExtras().get("image");
        String newCountry = (String) intent.getExtras().get("country");
        Intent intent1 = new Intent(this, ListActivity.class);
        intent1.putExtra("city",newCity);
        intent1.putExtra("temp",newTemp);
        intent1.putExtra("maxTemp",newMaxTemp);
        intent1.putExtra("minTemp",newMinTemp);
        intent1.putExtra("time", newTime);
        intent1.putExtra("feel_like", newFeelLike);
        intent1.putExtra("humid", newHumid);
        intent1.putExtra("vision", newVision);
        intent1.putExtra("image", newBitmap);
        intent1.putExtra("country", newCountry);
//        setResult(Activity.RESULT_OK, intent);
        startActivity(intent1);
        finish();
    }

    void setData(){
        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        city = bundle.getString("city");
        temp = bundle.getString("temp");
        txtCity.setText(bundle.getString("city"));
        txtTemp.setText(bundle.getString("temp")+"°");
        txtMaxTemp.setText(bundle.getString("maxTemp")+"°");
        txtMinTemp.setText(bundle.getString("minTemp")+"°");
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
        txtMaxTemp=findViewById(R.id.txtMaxTemp);
        txtMinTemp=findViewById(R.id.txtMinTemp);
        imageView=findViewById(R.id.imgIcon);
        setData();
    }
}