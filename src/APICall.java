import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class APICall implements Runnable{

    public HttpClient client;
    public HttpRequest request;
    HttpResponse<String> response;

    public APICall(String uri){

        client = HttpClient.newHttpClient();
        //test api call - remove
        request = HttpRequest.newBuilder()
        .uri(URI.create(uri))
        .timeout(Duration.ofSeconds(30))
        .build();
    }

    public void run(){

        try{
        
            response = client.send(request, BodyHandlers.ofString());
            JSONParser parser = new JSONParser();
            JSONObject result = (JSONObject)parser.parse(response.body());
            JSONObject data = (JSONObject) result.get("data");
            JSONObject entry = (JSONObject) data.get("entry");
            JSONArray arrivalsAndDepartures = (JSONArray) entry.get("arrivalsAndDepartures");

            System.out.println(arrivalsAndDepartures.toString());

            //System.out.println(response.body());
        } catch (Exception ex){

            System.out.println("whoops!");
            //cry
        }
    }
}