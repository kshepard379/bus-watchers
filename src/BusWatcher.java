import java.util.concurrent.TimeUnit;

public class BusWatcher{
    public static void main(String[] args) throws InterruptedException{

    	boolean runEnabled = true;
        String APIKey = "TEST";
        String[] stopNumbers = {"1_68004", "1_67655", "1_67652", "1_68007", "1_640", "1_690", "1_700", "1_81755", "1_58114", "1_361", "1_80432", "1_620", "29_2114"};
        String[] stopNames = {"Bellevue TC Bay 4", "Bellevue TC Bay 8", "Bellevue TC Bay 9", "Bellevue TC Bay 12", "4th Ave & James Street",
        					  "4th Avenue & Union","4th Ave Pike St","Bear Creek P&R & 178th Pl NE", "Southcenter Blvd & 62nd Ave S", 
        					  "2nd Ave & James Street","Federal Way TC – Bay 2", "Avenue S & S Jackson St.","Lynwood Transit Center Bay D2"};
        long timeInterval = 30000 / stopNumbers.length;

        while(runEnabled){ // may be changed to when time limit is up

            for(int i = 0; i < stopNumbers.length; i++){
            	//System.out.println(i + ": ");
                Runnable runnable = new APICall("http://api.pugetsound.onebusaway.org/api/where/arrivals-and-departures-for-stop/", stopNumbers[i], stopNames[i], APIKey);
                runnable.run();
                TimeUnit.MILLISECONDS.sleep(timeInterval);
                //wait
            }
        }
    }
}