import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class FlightCatalog {
    private static List<Flight> flights = null;
    private FlightCatalog(){
    }
    public static List<Flight> getFlightCatalogInstance(){
        if(flights==null){
            flights = new ArrayList<Flight>();
        }
        return flights;
    }

    public static void addFlight(Flight flight){
        flights.add(flight);
        checkStatus(flight.getAircraft());
    }

    public static void checkStatus(Aircraft aircraft){
        LocalDateTime currentTime = LocalDateTime.now();
        for (Flight flight : flights){
            if(flight.getAircraft().getAircraftId().equals(aircraft.getAircraftId())){
                if(currentTime.isBefore(flight.getActualArrivalTime()) && currentTime.isAfter(flight.getActualDepartureTime())){
                    flight.getAircraft().setAircraftStatus(Aircraft.AircraftStatus.IN_TRANSIT);
                }else{
                    flight.getAircraft().setAircraftStatus(Aircraft.AircraftStatus.STATIONED);
                }
            }
        }
    }
}



