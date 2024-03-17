import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

public class AirportCatalog {
    private static List<Airport> Airports = null;
    private AirportCatalog(){
    }
    public static List<Airport> getAirportCatalogInstance(){
        if(Airports==null){
            Airports = new ArrayList<Airport>();
        }
        return Airports;
    }

    public static void addAirport(Airport airport){
        Airports.add(airport);
    }

}
