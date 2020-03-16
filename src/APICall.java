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
        	//initialize HttpClient to call api call
            response = client.send(request, BodyHandlers.ofString());
            
            //time variables
            int current;
            int predicted;
            int scheduled;
            
            //
            JSONParser parser = new JSONParser();
            JSONObject result = (JSONObject)parser.parse(response.body());
            current = Integer.parseInt(result.get("currentTime").toString()) / 60000;
            result = (JSONObject) result.get("data");
            result = (JSONObject) result.get("entry");
            JSONArray arrivals = (JSONArray) result.get("arrivalsAndDepartures");
            
            if(arrivals.size() > 0) {
            	JSONObject arrivalElements = (JSONObject) arrivals.get(0);
            	predicted = Integer.parseInt(arrivalElements.get("predictedArrivalTime").toString()) / 60000;
            	scheduled = Integer.parseInt(arrivalElements.get("scheduledArrivalTime").toString()) / 60000;
            }
            
            //int predicted = ((int) finalObj.get("predictedArrivalTime")) / 60000;
            //int scheduled = ((int) finalObj.get("scheduledArrivalTime")) / 60000;            
            
            for (int i = 0; i < arrivals.size(); i++) {
            	
                //System.out.print(i + ": " + arrivalsAndDepartures.get(i));
            }

            //System.out.println(arrivalsAndDepartures.toString());

            //System.out.println(response.body());
        } catch (Exception ex){

            System.out.println("whoops!");
            //cry
        }
    }
}