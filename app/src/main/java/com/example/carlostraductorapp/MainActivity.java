package com.example.carlostraductorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class MainActivity extends AppCompatActivity {

    // credenciais para acessar o recurso ( não explodam com a quantidade limite de acesso pls )
    String key = "aafd8a4ae721411cae7c6ee7ae0cc444";
    String location = "global";
    // variaveis da GUI
    TextView textView;
    // variavel do input text
    EditText inputText;


    // criando recurso de istancia para abrir uma requiziçao com a API do bill gates
    OkHttpClient client = new OkHttpClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        inputText = (EditText) findViewById(R.id.inputText);


    }

    public void updateText(View view){
        textView.setText("Teste" + inputText.getText());
        System.out.println("Botão clicado");
    }
    // criando método post
    public String Post() throws IOException{
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,
                "[{\"Text\": \"" + inputText +"\"}]");
        Request request = new Request.Builder()
                .url("https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=en&to=fr&to=pt")
    }
}