import java.time.format.DateTimeParseException;
import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Console {
    private AirlineCatalog airlineCatalog;
    private FlightCatalog flightCatalog;
    private UserCatalog userCatalog;
    private AircraftCatalog aircraftCatalog;
    private AirportCatalog airportCatalog;
    private CityCatalog cityCatalog;
    public Console(){
        airportCatalog = AirportCatalog.getAirportCatalogInstance();
        airlineCatalog = AirlineCatalog.getAirlineCatalogInstance();
        flightCatalog = FlightCatalog.getFlightCatalogInstance();
        userCatalog = UserCatalog.getCredentialsCatalogInstance();
        aircraftCatalog = AircraftCatalog.getAircraftCatalogInstance();
        cityCatalog = CityCatalog.getCityCatalogInstance();
    }

    //Returns an existing user
    public User login(Scanner scanner){
        return User.login(scanner,userCatalog);
    }

    //Creates a user
    public void createUser(Scanner scanner){
        User.createUser(scanner,userCatalog,airlineCatalog,airportCatalog);
    }

    //Registers a new aircraft in the database
    public void createAircraft(User user, Scanner scanner){
        Aircraft.createAircraft(user,scanner,aircraftCatalog,airportCatalog);
    }

    //Searches for a flight, use case 1
    public void searchFlights(User user, Scanner scanner){
        Flight.searchFlights(user,scanner,flightCatalog,airportCatalog);
    }

    //Adds a flight, use case 2
    public void addFlight(User user, Scanner scanner) {
        Flight.addFlight(user,scanner,flightCatalog,aircraftCatalog,airlineCatalog,airportCatalog);
    }

    //Adds an aircraft to an airline's fleet
    public void addAircraftToFleet(User user, Scanner scanner){
        Airline.addAircraftToFleet(user,scanner,airlineCatalog,aircraftCatalog,flightCatalog);
    }

    //Registers a new airline in the database
    public void addAirline(User user, Scanner scanner){
        Airline.addAirline(user,scanner,airlineCatalog,aircraftCatalog,flightCatalog);
    }

    //Registers a new airport in the database, use case 3
    public void addAirport(User user, Scanner scanner){
        Airport.addAirport(user, scanner, cityCatalog, airportCatalog);
    }

    //Registers a new city in the database
    public void addCity(User user, Scanner scanner){
        City.addCity(user, scanner, cityCatalog);
    }



    //Displays to a person the menu of the app
    public void launchApplication() {
        System.out.println("Welcome to the flight tracker application!");
        Scanner scanner = new Scanner(System.in);
        boolean applicationRunning = true;

        while (applicationRunning) {
            System.out.println("Would you like to login with an existing account or would you like to create an account?");
            System.out.println("1. Log in with an existing account or access the app without an account");
            System.out.println("2. Create an account");
            System.out.println("3. Quit the application");

            int initialChoice = getUserChoice(scanner, 1, 3);
            User currentUser;

            if (initialChoice == 1) {
                currentUser = login(scanner);
            } else if (initialChoice == 2) {
                createUser(scanner);
                System.out.println("Now you can login!");
                currentUser = login(scanner);
            } else {
                break; // Quit the application
            }

            while (true) {
                System.out.println("Choose what option you would like to do:");
                System.out.println("1. Search for a flight");
                System.out.println("2. Add a flight");
                System.out.println("3. Add a city");
                System.out.println("4. Add an airport");
                System.out.println("5. Add an airline");
                System.out.println("6. Add an aircraft");
                System.out.println("7. Add an aircraft to an airline's fleet");
                System.out.println("8. Log out");
                System.out.println("9. Quit the application");

                int choice = getUserChoice(scanner, 1, 9);

                if(choice == 1){
                    searchFlights(currentUser,scanner);
                }else if(choice == 2){
                    addFlight(currentUser,scanner);
                }else if(choice == 3){
                    addCity(currentUser,scanner);
                }else if(choice == 4){
                    addAirport(currentUser,scanner);
                }else if(choice == 5){
                    addAirline(currentUser,scanner);
                }else if(choice == 6){
                    createAircraft(currentUser,scanner);
                }else if(choice == 7){
                    addAircraftToFleet(currentUser,scanner);
                } else if (choice == 8) {
                    System.out.println("Log out successful");
                    // Log out and show the initial screen again
                    break; // This breaks out of the inner loop and goes back to the initial screen
                } else if (choice == 9) {
                    applicationRunning = false; // This stops the application entirely
                    break; // Break out of the inner loop
                }
            }
        }
        System.out.println("Thank you for using the flight tracker application!");
    }

    //Simple function helping the user choose a number
    private int getUserChoice(Scanner scanner, int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice < min || choice > max) {
                    System.out.println("Please enter a valid choice between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
        return choice;
    }


}
