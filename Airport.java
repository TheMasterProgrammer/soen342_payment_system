import java.time.LocalDateTime;
import java.util.Scanner;

public class Airport {
    private String airportCode;
    private String name;
    private City city;
    public Airport(String airportCode, String name, City city){
        this.airportCode = airportCode;
        this.name = name;
        this.city = city;
    }

    public String getAirportCode() {
        return airportCode;
    }
    public String getName() {
        return name;
    }



    //Verify availability of the airport's runway for a given time
    public boolean verifyAvailability(LocalDateTime time, FlightCatalog flightCatalog){
        for(Flight flight: flightCatalog.getFlights()){
            if(flight.getDestinationAirport().equals(this)){
                if(flight.getScheduledArrivalTime().equals(time)){
                    return false;
                }
            }else if(flight.getSourceAirport().equals(this)){
                if(flight.getScheduledDepartureTime().equals(time)){
                    return false;
                }
            }
        }
        return true;
    }



    //Registers an airport in the database
    public static void addAirport(User user, Scanner scanner, CityCatalog cityCatalog, AirportCatalog airportCatalog){
        if(user instanceof SystemAdministrator){
            if(cityCatalog.getCities().isEmpty()){
                System.out.println("There are currently no cities in the system so adding an airport is impossible for the moment");
                System.out.println("Try to add a city first");
                return;
            }
            boolean uniqueCode = false;
            String airportCode = null;
            //make sure the user entered a unique airport code
            while(!uniqueCode){
                System.out.println("Enter the airport code, it must be unique: ");
                airportCode = scanner.nextLine();
                boolean unique = true;
                for(Airport airport: airportCatalog.getAirports()){
                    if(airportCode.equals(airport.getAirportCode())){
                        unique = false;
                        System.out.println("Error: The entered airport code already corresponds to an airport in the database.");
                        break;
                    }
                }if(unique){
                    uniqueCode = true;
                }
            }
            boolean foundCity = false;
            //make sure the user associated an existing city in the database
            System.out.println("Here are the existing cities");
            City.displayCitiesInfo(cityCatalog);
            while(!foundCity){
                System.out.println("Enter an existing city name: ");
                String cityName = scanner.nextLine();
                System.out.println("Enter the country of the city: ");
                String cityCountry = scanner.nextLine();
                if(City.verifyCityExistance(cityName, cityCountry, cityCatalog)){
                    foundCity = true;
                    City existingCity  = City.getCity(cityName,cityCountry,cityCatalog);
                    System.out.println("Now name the airport: ");
                    String airportName = scanner.nextLine();
                    airportCatalog.addAirport(new Airport(airportCode,airportName,existingCity));
                    System.out.println("Airport was successfully added");
                }else{
                    System.out.println("The entered city does not exist. Please try again.");
                }
            }
        }else{
            System.out.println("You do not have the necessary privileges to add an airport.");
        }

    }

}
