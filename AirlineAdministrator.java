public class AirlineAdministrator extends User {
    private String airlineName; // Name of the airline

    public AirlineAdministrator(String id, String name, String airlineName) {
        this.id = id;
        this.name = name;
        this.isRegistered = true;
        this.airlineName = airlineName;
    }

    public String getAirlineName() {
        return airlineName;
    }

    // Additional methods specific to airline administrators
}
