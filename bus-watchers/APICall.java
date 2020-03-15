import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

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
            System.out.println(response.body());
        } catch (Exception ex){
            System.out.println("whoops!");
            //cry
        }
    }
}