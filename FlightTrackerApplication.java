import java.util.List;

public class FlightTrackerApplication {
    public static void main(String[] args) {
        FlightSearchController searchController = new FlightSearchController();
        //User registeredUser = new User("1","Kevin",true);
        //User nonRegisteredUser = new User("2","David",false);

        // Example search
        String source = "Montreal";
        String destination = "London";

        System.out.println("Registered user's view:");
        //List<Flight> resultsForRegistered = searchController.searchFlights(source, destination, registeredUser);
        //esultsForRegistered.forEach(flight -> System.out.println(flight.getFlightNumber() + " from " + flight.getSource() + " to " + flight.getDestination() + ", Airline: " + flight.getAirlineName() + ", Aircraft: " + flight.getAircraftType()));

        System.out.println("\nNon-registered user's view:");
        //List<Flight> resultsForNonRegistered = searchController.searchFlights(source, destination, nonRegisteredUser);
        //resultsForNonRegistered.forEach(flight -> System.out.println(flight.getFlightNumber() + " from " + flight.getSource() + " to " + flight.getDestination()));
    }
}
