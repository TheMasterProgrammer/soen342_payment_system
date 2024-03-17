import java.util.ArrayList;
import java.util.List;


public class AirlineCatalog {
    private static List<Airline> airlines = null;
    private AirlineCatalog(){
    }
    public static List<Airline> getAirlineCatalogInstance(){
        if(airlines==null){
            airlines = new ArrayList<Airline>();
        }
        return airlines;
    }

    public static void addAirline(Airline airline){
        airlines.add(airline);
    }
}
