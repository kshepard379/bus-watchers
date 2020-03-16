import java.util.concurrent.TimeUnit;

public class BusWatcher{
    public static void main(String[] args) throws InterruptedException{

    	boolean runEnabled = true;
        String APIKey = "TEST";
        String[] stopNumbers = {"68004", "67655", "67652", "68007", "640", "690", "700", "81755", "58114", "361", "80432", "620", "2114"};
        String[] stopNames = {"Bellevue TC Bay 4", "Bellevue TC Bay 8", "Bellevue TC Bay 9", "Bellevue TC Bay 12", "4th Ave & James Street",
        					  "4th Avenue & Union","4th Ave Pike St","Bear Creek P&R & 178th Pl NE", "Southcenter Blvd & 62nd Ave S", 
        					  "2nd Ave & James Street","Federal Way TC – Bay 2", "Avenue & S Jackson St.","Lynwood Transit Center Bay D2"};
        long timeInterval = 30000 / stopNumbers.length;

        while(runEnabled){ // may be changed to when time limit is up

            for(int i = 0; i < stopNumbers.length; i++){
            	//System.out.println(i + ": ");
                Runnable runnable = new APICall("http://api.pugetsound.onebusaway.org/api/where/arrivals-and-departures-for-stop/1_", stopNumbers[i], stopNames[i], APIKey);
                runnable.run();
                TimeUnit.MILLISECONDS.sleep(timeInterval);
                //wait
            }
        }
    }
}