import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class FlightCatalog {
    private static FlightCatalog flightCatalog = null;
    private List<Flight> flights;
    private FlightCatalog(){
        flights = new ArrayList<>();
    }
    public static FlightCatalog getFlightCatalogInstance(){
        if(flightCatalog==null){
            flightCatalog = new FlightCatalog();
        }
        return flightCatalog;
    }

    //Adds a flight to the database
    public void addFlight(Flight flight){
        flights.add(flight);
    }

    //Returns the existing flights in the database
    public List<Flight> getFlights() {
        return flights;
    }

}



