package com.example.carlostraductorapp;

import static com.example.carlostraductorapp.MainActivity.inputText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    public static String parse(String json_text) {
        JsonElement jelement = new JsonParser().parse(json_text);
        JsonObject  jobject = jelement.getAsJsonObject();
        jobject = jobject.getAsJsonObject("translations");
        JsonArray jarray = jobject.getAsJsonArray("text");
        jobject = jarray.get(0).getAsJsonObject();
        String result = jobject.get("text").getAsString();
        return result;
    }

    public static String jsonArray(String json_text) throws JSONException {
        JSONArray arr = new JSONArray(json_text);
        String a = "translations";
        //JSONObject responseObject = new JSONObject(json_text);
        JSONObject obj = arr.getJSONObject(0);
        JSONArray jsonArray = (JSONArray) obj.get("translations");
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        String saida = jsonObject.getString("text");

        return saida;
    }


}