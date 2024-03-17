public class AirportAdministrator extends User {
    private String airportCode; // Unique identifier for the airport

    public AirportAdministrator(String id, String name, String airportCode) {
        this.id = id;
        this.name = name;
        this.isRegistered = true;
        this.airportCode = airportCode;
    }

    public String getAirportCode() {
        return airportCode;
    }

    // Additional methods specific to airport administrators
}
