import java.util.ArrayList;
import java.util.List;

public class AirportCatalog {
    private static AirportCatalog airportCatalog = null;
    private List<Airport> airports;
    private AirportCatalog(){
        airports = new ArrayList<>();
    }
    public static AirportCatalog getAirportCatalogInstance(){
        if(airportCatalog==null){
            airportCatalog = new AirportCatalog();
        }
        return airportCatalog;
    }

    //Returns the existing airports in the database
    public List<Airport> getAirports(){
        return airports;
    }

    //Adds an airport to the database
    public void addAirport(Airport airport){
        airports.add(airport);
    }

}
