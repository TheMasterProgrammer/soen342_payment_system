import java.util.ArrayList;
import java.util.List;


public class AirlineCatalog {
    private static AirlineCatalog airlineCatalog = null;

    private List<Airline> airlines;
    private AirlineCatalog(){
        airlines = new ArrayList<>();
        //Some hardcoded data

        
        //Registering a new airline with 2 aircraft
        airlines.add(new Airline("Air Canada", AircraftCatalog.getAircraftCatalogInstance().getAircrafts().get(1), AircraftCatalog.getAircraftCatalogInstance().getAircrafts().get(4)));
        //Registering a new airline with 2 aircraft
        airlines.add(new Airline("Fedex", AircraftCatalog.getAircraftCatalogInstance().getAircrafts().get(5), AircraftCatalog.getAircraftCatalogInstance().getAircrafts().get(2)));
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
