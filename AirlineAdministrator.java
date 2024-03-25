public class AirlineAdministrator extends User {
    private Airline airline;

    public AirlineAdministrator(String username, String password, Airline airline) {
        this.id = String.valueOf(counter++);
        this.username = username;
        this.password = password;
        this.isRegistered = true;
        this.airline = airline;
    }

    public Airline getAirline() {
        return airline;
    }

}
