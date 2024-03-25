import java.time.LocalDateTime;

public class PrivateFlight extends Flight{
    public PrivateFlight(String flightNumber, Airport sourceAirport, Airport destinationAirport, Aircraft aircraft, LocalDateTime actualDepartureTime, LocalDateTime scheduledDepartureTime, LocalDateTime actualArrivalTime, LocalDateTime scheduledArrivalTime) {
        super(flightNumber, sourceAirport, destinationAirport, aircraft, actualDepartureTime, scheduledDepartureTime, actualArrivalTime, scheduledArrivalTime);
    }

    //Displays flight info for a private flight
    public String displayFlightInfo(){
        return "Private " + super.displayFlightInfo();
    }
}
