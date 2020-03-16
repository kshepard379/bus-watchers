import java.io.FileWriter;
import java.io.IOException;
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
    String stopNumber;
    String stopName;
    String APIKey;

    /**
     * 
     * @param uri - partial url for api call to Puget Sound OneBusAway
     * @param stopNumber - Stop number not including the "1_"
     * @param stopName - address/name for bus stop
     * @param APIKey - api key used for call
     */
    public APICall(String uri, String stopNumber, String stopName, String APIKey){

        client = HttpClient.newHttpClient();
        //test api call - remove
        request = HttpRequest.newBuilder()
        .uri(URI.create(uri  + stopNumber + ".json?key=" + APIKey)) //we want a .json file for our json.simple library
        .timeout(Duration.ofSeconds(15))
        .build();
        this.stopNumber = stopNumber;
        this.stopName = stopName;
    }

    public void printEntry(String[] stopData) throws IOException {
    	FileWriter pw = new FileWriter("",true);
    }
    
    public void run(){

        try{
        	//initialize HttpClient to call api call
        	int count = 0;
            do {
            	if (count > 3) {
            		break;
            	}
            	response = client.send(request, BodyHandlers.ofString());
            	count++;
            	

            } while (response.body().contentEquals(""));	//try again if timeout
            
            //time variables
            Long current;
            Long predicted;
            Long scheduled;
            
            //grab arrival information for current stop
            JSONParser parser = new JSONParser();

            JSONObject result = (JSONObject)parser.parse(response.body());
            current = Long.parseLong(result.get("currentTime").toString()) / 60000;
            result = (JSONObject) result.get("data");
            result = (JSONObject) result.get("entry");
            JSONArray arrivals = (JSONArray) result.get("arrivalsAndDepartures");
            
            //only continue if there are any routes returned in the first place to grab data from
            if(arrivals.size() > 0) {

            	//loop through each returned arrival
            	for (int i = 0; i < arrivals.size(); i++) {
            		
            		//get timestamp data for each arrival to check when a bus is nearby the stop
            		JSONObject arrivalElements = (JSONObject) arrivals.get(i);
            		predicted = Long.parseLong(arrivalElements.get("predictedArrivalTime").toString()) / 60000;
            		scheduled = Long.parseLong(arrivalElements.get("scheduledArrivalTime").toString()) / 60000;
            		String route = arrivalElements.get("routeShortName").toString();
            		
            		//if bus is here NOW (predicted time in minutes equals current time in minute)
            		if((predicted - current) == 0) {
            			
            			//keep track if bus was late/on time/ early, and by how much
            			Long punctuality = (predicted - scheduled);
            			String onTimeStatus;
            			
            			if (punctuality == 0) {
            				onTimeStatus = "On Time";
            			} else if (punctuality > 0) {
            				onTimeStatus = "Late";
            			} else {
            				onTimeStatus = "Early";
            			}
            			
                    	// 0-stop number, 1-stop name, 2-route number, 3-on time status, 4-how late/early
            			String[] stopData = {stopNumber, stopName, route, onTimeStatus, punctuality.toString()};
            			
            			//TODO check if entry is duplicate
            			
            			for(String s: stopData) {
            				System.out.println(s);
            			}
            			System.out.println();
            		}
            	}
            }       
        }
        catch (ParseException pe) {
        	System.out.println("Tried calling api for stop# " + stopNumber + " 3 times with no server response.");
        }
        catch (IOException ioex) {
        	System.out.println("Failed to write to file");
        }
        catch (Exception ex){
            System.out.println("Something went wrong: " + ex.toString() + "\n");
            //cry
        }
    }
}