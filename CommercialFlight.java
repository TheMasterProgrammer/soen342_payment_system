import java.time.LocalDateTime;

public class CommercialFlight extends Flight{
    private Airline airline;
    public CommercialFlight(String flightNumber, Airport sourceAirport, Airport destinationAirport, Aircraft aircraft, LocalDateTime actualDepartureTime, LocalDateTime scheduledDepartureTime, LocalDateTime actualArrivalTime, LocalDateTime scheduledArrivalTime, Airline airline) {
        super(flightNumber, sourceAirport, destinationAirport, aircraft, actualDepartureTime, scheduledDepartureTime, actualArrivalTime, scheduledArrivalTime);
        this.airline = airline;
    }

    //Returns the airline that manages the commercial flight
    public Airline getAirline() {
        return airline;
    }


    //Displays flight info, with company name
    public String displayFlightInfo(){
        return super.displayFlightInfo() + ", Airline name: " + airline.getAirlineName();
    }
}
