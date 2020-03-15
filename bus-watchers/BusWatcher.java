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
import java.util.concurrent.ScheduledExecutorService;

public class BusWatcher{
    public static void main(String[] args){

        //loop
        String APIKey = "TEST";
        int[] stops = {68004, 67655, 67652, 68007, 640, 690, 700, 81755, 58114, 361, 80432, 620, 2114};

        while(true){ // may be changed to when time limit is up

            for(int i : stops){
                Runnable runnable = new APICall("http://api.pugetsound.onebusaway.org/api/where/arrivals-and-departures-for-stop/1_" + i + ".json?key=" + APIKey);
                runnable.run();
            }
            break;
        }
    }
}