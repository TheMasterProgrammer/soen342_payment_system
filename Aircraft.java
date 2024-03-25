import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Aircraft {
    private static int counter = 0;
    private int aircraftId;
    private String model;


    public Airport latestLocation;

    public Aircraft(String model, Airport initialAirport) {
        this.aircraftId = assignId();
        this.model = model;
        this.latestLocation = initialAirport;
    }

    public static int assignId(){
        return counter++;
    }

    public int getAircraftId() {
        return aircraftId;
    }
    public String getModel(){
        return model;
    }



    //Displays aircraft information
    public String displayAircraftInfo(FlightCatalog flightCatalog){
        return "Aircraft id: " + this.getAircraftId() + ", model: " + this.getModel() + ", latest location Airport: " +
                this.getLocationAtLatestFlight(flightCatalog).getName() + ", Code: " + this.getLocationAtLatestFlight(flightCatalog).getAirportCode();
    }


    //Check if the aircraft had any flight
    public boolean hadAnyFlight(FlightCatalog flightCatalog){
        for(Flight flight: flightCatalog.getFlights()){
            if(flight.getAircraft().equals(this)){
                return true;
            }
        }
        return false;
    }

    //Returns the scheduled arrival of the latest flight of an aircraft or null if it had no flights
    public LocalDateTime latestFlightScheduledArrival(FlightCatalog flightCatalog){
        if(this.hadAnyFlight(flightCatalog)){
            LocalDateTime lastScheduledArrival = null;
            for(Flight flight: flightCatalog.getFlights()){
                if(flight.getAircraft().equals(this)){
                    if(lastScheduledArrival==null || flight.getScheduledArrivalTime().isAfter(lastScheduledArrival)){
                        lastScheduledArrival = flight.getScheduledArrivalTime();
                    }
                }
            }
            return lastScheduledArrival;
        }else{
            return null;
        }
    }


    //checks if the aircraft has no future flights and is stationed at the specified time
    public boolean checkStatus(FlightCatalog flightCatalog, LocalDateTime time){
        //check if the aircraft has no future flights and is stationed at the specified time
        if(this.hadAnyFlight(flightCatalog)){
            //if the scheduled arrival time is before the specified time, it means the aircraft is stationed and is available
            if(this.latestFlightScheduledArrival(flightCatalog).isBefore(time)){
                return true;
            }
            return false;
        //if the aircraft had no flight, it means it is stationed
        }else{
            return true;
        }
    }

    //Gets the location Airport at the latest flight of the aircraft
    public Airport getLocationAtLatestFlight(FlightCatalog flightCatalog){
        //makes sure that if the aircraft had no flights it returns the location airport where the aircraft was initially registered in the database
        if(getLatestFlight(flightCatalog) == null){
            return latestLocation;
        }else{
            return getLatestFlight(flightCatalog).getDestinationAirport();
        }

    }


    //Returns the latest flight of an aircraft or null if it had none
    public Flight getLatestFlight(FlightCatalog flightCatalog){
        Flight latestFlight = null;
        for(Flight flight: flightCatalog.getFlights()){
            if(flight.getAircraft().equals(this)){
                if(latestFlight==null || flight.getScheduledArrivalTime().isAfter(latestFlight.getScheduledArrivalTime())){
                    latestFlight = flight;
                }
            }
        }
        return latestFlight;
    }

    //Returns the airline that owns the aircraft or null if it is not owned by any airline
    public Airline getAirline(AirlineCatalog airlineCatalog){
        for(Airline airline: airlineCatalog.getAirlines()){
            for(Aircraft aircraft: airline.getFleet()){
                if(aircraft.equals(this)){
                    return airline;
                }
            }
        }
        return null;
    }



    //Registers an aircraft in the database
    public static void createAircraft(User user, Scanner scanner, AircraftCatalog aircraftCatalog, AirportCatalog airportCatalog){
        //checks if the user has the permission to create an aircraft
        if (user instanceof SystemAdministrator || user instanceof AirportAdministrator || user instanceof AirlineAdministrator) {
            if(airportCatalog.getAirports().isEmpty()){
                System.out.println("There are currently no airports in the system so adding an aircraft is impossible for the moment.");
                System.out.println("Because the aircraft needs to be associated with an airport");
                System.out.println("Try to add an airport first");
                return;
            }
            System.out.println("enter the model of the aircraft: ");
            String model = scanner.nextLine();
            boolean foundAirport = false;
            Airport initialAirport = null;

            //checks if the user enters an existing airport
            while(!foundAirport){
                System.out.println("Enter the code of the airport where the aircraft will be initially stationed");
                String airportCode = scanner.next();
                scanner.nextLine();// consume newline character
                for(Airport airport : airportCatalog.getAirports()){
                    if(airport.getAirportCode().equals(airportCode)){
                        initialAirport = airport;
                        foundAirport = true;
                        break;
                    }
                }
                if(!foundAirport){
                    System.out.println("the code of the airport you entered does not exist");
                }
            }
            Aircraft aircraft = new Aircraft(model,initialAirport);
            System.out.println("The id of the Aircraft is " + aircraft.getAircraftId());
            System.out.println("Aircraft was successfully added");
            aircraftCatalog.addAircraft(aircraft);
        }else {
            System.out.println("You do not have the necessary privileges to create an aircraft.");
        }
    }


}
