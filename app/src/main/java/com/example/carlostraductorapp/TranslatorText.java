package com.example.carlostraductorapp;

import static com.example.carlostraductorapp.MainActivity.inputText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public  class TranslatorText {
    // credenciais para acessar o recurso ( não explodam com a quantidade limite de acesso pls )
    private String key = "653e7e93436144c2bf829f7436d7803e";
    private String location = "eastus";





    // criando recurso de istancia para abrir uma requiziçao com a API do bill gates
    OkHttpClient client = new OkHttpClient();

    // criando método post
    public String Post() throws IOException{
        String textWritten = inputText.getText().toString();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(
                "[{\"Text\": \"" + textWritten + "\" }]",
                mediaType);
        // "" + variavle + "
        Request request = new Request.Builder()
                .url("https://api.cognitive.microsofttranslator.com/translate?api-version=3.0&from=en&to=pt")
                .post(body)
                .addHeader("Ocp-Apim-Subscription-Key", key)
                // location required if you're using a multi-service or regional (not global) resource.
                .addHeader("Ocp-Apim-Subscription-Region", location)
                .addHeader("Content-type", "application/json")
                .build();


        Response response = client.newCall(request).execute();
        return response.body().string();

    }

    public static String prettify(String json_text){
        JsonParser parser = new JsonParser();

        JsonElement json = parser.parse(json_text);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }

}