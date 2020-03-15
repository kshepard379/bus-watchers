import java.io.IOError;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandler;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.file.Paths;
import java.time.Duration;

public class BusWatcher{
    public static void main(String[] args) throws IOException, InterruptedException{
        //System.out.println("Hello World");

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("http://api.pugetsound.onebusaway.org/api/where/arrivals-and-departures-for-stop/1_67655.json?key=TEST"))
        .timeout(Duration.ofMinutes(2))
        .header("Content-Type", "application/json")
        //.POST(BodyPublishers.ofFile(Paths.get("file.json")))
        .build();

        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        System.out.println(response.body());
    }
}