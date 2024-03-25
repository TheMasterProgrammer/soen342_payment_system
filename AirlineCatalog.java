import java.util.ArrayList;
import java.util.List;


public class AirlineCatalog {
    private static AirlineCatalog airlineCatalog = null;

    private List<Airline> airlines;
    private AirlineCatalog(){
        airlines = new ArrayList<>();
    }
    public static AirlineCatalog getAirlineCatalogInstance(){
        if(airlineCatalog==null){
            airlineCatalog = new AirlineCatalog();
        }
        return airlineCatalog;
    }

    //Adds an airline to the database
    public void addAirline(Airline airline){
        airlines.add(airline);
    }


    //Returns the existing airlines in the database
    public List<Airline> getAirlines() {
        return airlines;
    }



}
