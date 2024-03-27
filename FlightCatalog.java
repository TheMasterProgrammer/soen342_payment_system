import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class FlightCatalog {
    private static FlightCatalog flightCatalog = null;
    private List<Flight> flights;
    private FlightCatalog(){
        flights = new ArrayList<>();
    
        //Some hardcoded data
        flights.add(new CommercialFlight(
                "AC01",
                AirportCatalog.getAirportCatalogInstance().getAirports().get(0),  // source airport Montreal
                AirportCatalog.getAirportCatalogInstance().getAirports().get(3), // destination airport Austin
                AircraftCatalog.getAircraftCatalogInstance().getAircrafts().get(1), // Aircraft
                LocalDateTime.parse("2024-03-27T09:05:00"), // departure actual
                LocalDateTime.parse("2024-03-27T09:00:00"), // departure scheduled
                LocalDateTime.parse("2024-03-27T17:00:00"), // arrival actual
                LocalDateTime.parse("2024-03-27T17:05:00"), // arrival scheduled
                AirlineCatalog.getAirlineCatalogInstance().getAirlines().get(0) //Air Canada
        ));

        flights.add(new CommercialFlight(
                "AC02",
                AirportCatalog.getAirportCatalogInstance().getAirports().get(3), // source airport Austin
                AirportCatalog.getAirportCatalogInstance().getAirports().get(2), // destination airport Ottawa
                AircraftCatalog.getAircraftCatalogInstance().getAircrafts().get(1), //Aircraft
                LocalDateTime.parse("2024-03-27T18:05:00"), // departure actual
                LocalDateTime.parse("2024-03-27T18:00:00"), // departure scheduled
                LocalDateTime.parse("2024-03-27T21:00:00"), // arrival actual
                LocalDateTime.parse("2024-03-27T21:05:00"), // arrival scheduled
                AirlineCatalog.getAirlineCatalogInstance().getAirlines().get(0) //Air Canada
        ));

        flights.add(new CommercialFlight(
                "AC03",
                AirportCatalog.getAirportCatalogInstance().getAirports().get(2), // source airport Ottawa
                AirportCatalog.getAirportCatalogInstance().getAirports().get(1), // destination airport Toronto
                AircraftCatalog.getAircraftCatalogInstance().getAircrafts().get(1), //Aircraft
                LocalDateTime.parse("2024-03-28T18:05:00"), // departure actual
                LocalDateTime.parse("2024-03-28T18:00:00"), // departure scheduled
                LocalDateTime.parse("2024-03-28T21:00:00"), // arrival actual
                LocalDateTime.parse("2024-03-28T21:05:00"), // arrival scheduled
                AirlineCatalog.getAirlineCatalogInstance().getAirlines().get(0) //Air Canada
        ));

        flights.add(new CargoFlight(
                "FX01",
                AirportCatalog.getAirportCatalogInstance().getAirports().get(2), // source airport Ottawa
                AirportCatalog.getAirportCatalogInstance().getAirports().get(1), // destination airport Toronto
                AircraftCatalog.getAircraftCatalogInstance().getAircrafts().get(5), //Aircraft
                LocalDateTime.parse("2024-03-27T18:13:00"), // departure actual
                LocalDateTime.parse("2024-03-27T18:00:00"), // departure scheduled
                LocalDateTime.parse("2024-03-27T21:00:00"), // arrival actual
                LocalDateTime.parse("2024-03-27T21:05:00"), // arrival scheduled
                AirlineCatalog.getAirlineCatalogInstance().getAirlines().get(1) // Fedex airline
        ));

        flights.add(new PrivateFlight(
                "PRIVATE01",
                AirportCatalog.getAirportCatalogInstance().getAirports().get(3), // source airport Austin
                AirportCatalog.getAirportCatalogInstance().getAirports().get(1), // destination airport Toronto
                AircraftCatalog.getAircraftCatalogInstance().getAircrafts().get(6), //Aircraft
                LocalDateTime.parse("2024-03-27T18:30:00"), // departure actual
                LocalDateTime.parse("2024-03-27T18:20:00"), // departure scheduled
                LocalDateTime.parse("2024-03-27T21:30:00"), // arrival actual
                LocalDateTime.parse("2024-03-27T21:20:00") // arrival scheduled
        ));

        flights.add(new PrivateFlight(
                "PRIVATE02",
                AirportCatalog.getAirportCatalogInstance().getAirports().get(0), // source airport Montreal
                AirportCatalog.getAirportCatalogInstance().getAirports().get(2), // destination airport Ottawa
                AircraftCatalog.getAircraftCatalogInstance().getAircrafts().get(0), //Aircraft
                LocalDateTime.parse("2024-03-27T18:30:00"), // departure actual
                LocalDateTime.parse("2024-03-27T18:20:00"), // departure scheduled
                LocalDateTime.parse("2024-03-27T21:30:00"), // arrival actual
                LocalDateTime.parse("2024-03-27T21:20:00") // arrival scheduled
        ));
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



