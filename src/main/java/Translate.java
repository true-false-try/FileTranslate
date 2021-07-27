
import java.io.*;
import java.net.*;
import java.util.*;
import com.google.gson.*;
import com.squareup.okhttp.*;


public class Translate {
    /*


    private static String subscriptionKey = "a88c4e8cfcmsh8b71b706fe65f6ap1a3b8djsn29be1481e0a4";

    // Default location is a global.
    private static String location = "global";

    HttpUrl url = new HttpUrl.Builder()
            .scheme("https")
            .host("microsoft-translator-text.p.rapidapi.com")
            .addPathSegment("/translate")
            .addQueryParameter("api-version", "3.0")
            .addQueryParameter("from", "ru")
            .addQueryParameter("to", "ru")
            .addQueryParameter("to", "uk")
            .build();

    // Instantiates the OkHttpClient.
    OkHttpClient client = new OkHttpClient();

    // This function performs a POST request.
    public String Post(String str) throws IOException {
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType,

                "[{\"Text\": \"" + str + "\"}]");
        Request request = new Request.Builder().url(url).post(body)
                .addHeader("Content-type", "application/json")
                .addHeader("x-rapidapi-key", subscriptionKey)
                .addHeader("x-rapidapi-host", "microsoft-translator-text.p.rapidapi.com")
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    // This function prettifies the json response.
    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonElement json = parser.parse(json_text);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        JsonObject jo = (JsonObject) json;

        return gson.toJson(json);
    }

    //This function parse JSON and return Translate string
    *//*public static String translateString(String request){

    }*//*
    public static void main(String[] args) {
        Translate translate = new Translate();
        System.out.println(translate.url);
    }

    */
    private final static String keyAPI = "a88c4e8cfcmsh8b71b706fe65f6ap1a3b8djsn29be1481e0a4";

    public static String getKeyAPI() {
        return keyAPI;
    }

    OkHttpClient client = new OkHttpClient();

    public String Post(String post) throws IOException{
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "[\r\n    {\r\n        \"Text\": \""+ post +"\"\r\n    }\r\n]");
        Request request = new Request.Builder()
                .url("https://microsoft-translator-text.p.rapidapi.com/translate?api-version=3.0&to=uk&textType=plain&profanityAction=NoAction")
                .post(body)
                .addHeader("content-type", "application/json")
                .addHeader("x-rapidapi-key", getKeyAPI())
                .addHeader("x-rapidapi-host", "microsoft-translator-text.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        return response.body().string();
    }



}

