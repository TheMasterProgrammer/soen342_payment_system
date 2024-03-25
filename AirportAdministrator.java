public class AirportAdministrator extends User {
    private Airport airport; // Unique identifier for the airport

    public AirportAdministrator(String username, String password, Airport airport) {
        this.id = String.valueOf(counter++);
        this.username = username;
        this.password = password;
        this.isRegistered = true;
        this.airport = airport;
    }

    public Airport getAirport() {
        return airport;
    }

}
