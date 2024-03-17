import java.time.LocalDateTime;

public class CommercialFlight extends Flight{
    private String airlineName;
    public CommercialFlight(String flightNumber, String sourceAirportCode, String destinationAirportCode, Aircraft aircraft, LocalDateTime actualDepartureTime, LocalDateTime scheduledDepartureTime, LocalDateTime actualArrivalTime, LocalDateTime scheduledArrivalTime, String airlineName) {
        super(flightNumber, sourceAirportCode, destinationAirportCode, aircraft, actualDepartureTime, scheduledDepartureTime, actualArrivalTime, scheduledArrivalTime);
        this.airlineName = airlineName;
    }

    public String getAirlineName() {
        return airlineName;
    }
}
