import java.time.LocalDateTime;

public class PrivateFlight extends Flight{
    public PrivateFlight(String flightNumber, String sourceAirportCode, String destinationAirportCode, Aircraft aircraft, LocalDateTime actualDepartureTime, LocalDateTime scheduledDepartureTime, LocalDateTime actualArrivalTime, LocalDateTime scheduledArrivalTime) {
        super(flightNumber, sourceAirportCode, destinationAirportCode, aircraft, actualDepartureTime, scheduledDepartureTime, actualArrivalTime, scheduledArrivalTime);
    }
}
