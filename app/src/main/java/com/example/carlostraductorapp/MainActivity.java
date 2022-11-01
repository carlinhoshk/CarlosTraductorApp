package com.example.carlostraductorapp;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.text.Html;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONException;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


    // variaveis da GUI
    TextView textView;
    // variavel do input text
    static EditText inputText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(Html.fromHtml("<font color=\"green\">" + getString(R.string.app_name) + "</font>"));
        textView = (TextView) findViewById(R.id.textView);
        inputText = (EditText) findViewById(R.id.inputText);

        //System.out.println(inputText);
    }

    public void updateText(View view) throws JSONException {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        //System.out.println("porque não printa o input");
        // 30/10 percebo que so precisava puxar o input com gettext e to string
        //String textWritten = inputText.getText().toString();
        //System.out.println(textWritten);

        try {
            TranslatorText translateRequest = new TranslatorText();
            String response = translateRequest.Post();
            //System.out.println("jason nao tratado");
            //System.out.println((response));
            //System.out.println("jason tratado");
            //System.out.println(translateRequest.jsonArray(response));


            textView.setText(translateRequest.jsonArray(response));
        } catch (Exception e) {
            System.out.println(e);
        }



    }




}