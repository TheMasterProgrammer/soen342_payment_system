import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlightSearchController {
    private List<Flight> flights;

    public FlightSearchController() {
        flights = new ArrayList<>();
        // Hardcoded flight data
        //flights.add(new Flight("AC 864", "Montreal", "London", true,"Air Canada", "Boeing 787"));
       // flights.add(new Flight("UA 923", "New York", "London", false,"United Airlines", "Boeing 777"));
    }

    /*
    public List<Flight> searchFlights(String source, String destination, User user) {
        return flights.stream()
                .filter(flight -> flight.getSource().equalsIgnoreCase(source) && flight.getDestination().equalsIgnoreCase(destination))
                .collect(Collectors.toList());
    }*/

    /*
    public List<Flight> searchFlights(String source, String destination, User user) {
        Stream<Flight> filteredFlights = flights.stream()
                .filter(flight -> flight.getSource().equalsIgnoreCase(source) && flight.getDestination().equalsIgnoreCase(destination));

        if (user.isRegistered()) {
            // Registered users can see all relevant flights.
            return filteredFlights.collect(Collectors.toList());
        } else {
            // Non-registered users see only non-private flights.
            return filteredFlights
                    .filter(flight -> !flight.isPrivate())
                    .collect(Collectors.toList());
        }
    }
     */

}
