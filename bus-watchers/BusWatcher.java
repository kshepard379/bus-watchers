import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpClient.Version;
import java.net.http.HttpResponse.BodyHandler;

public class BusWatcher{
    public static void main(String[] args){
        System.out.println("Hello World");

        HttpRequest request = HttpRequest.newBuilder()
        request.uri(URI.create("http://api.pugetsound.onebusaway.org/api/where/arrivals-and-departures-for-stop/1_67655.json?key=TEST"))
        .timeout(Duration.ofMinutes(2))
        .header("Content-Type", "application/json")
        .POST(BodyPublishers.ofFile(Paths.get("file.json")))
        .build();

        HttpClient client = HttpClient.newHttpClient();
        send(HttpRequest,BodyHandler);
    }
}