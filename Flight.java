import org.w3c.dom.ls.LSOutput;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Flight {
    protected String flightNumber;
    protected Airport sourceAirport;
    protected Airport destinationAirport;
    protected LocalDateTime actualDepartureTime;
    protected LocalDateTime scheduledDepartureTime;
    protected LocalDateTime actualArrivalTime;
    protected LocalDateTime scheduledArrivalTime;
    protected Aircraft aircraft;

    public Flight(String flightNumber, Airport sourceAirport, Airport destinationAirport,
                  Aircraft aircraft,
                  LocalDateTime actualDepartureTime, LocalDateTime scheduledDepartureTime,
                  LocalDateTime actualArrivalTime, LocalDateTime scheduledArrivalTime) {
        this.flightNumber = flightNumber;
        this.sourceAirport = sourceAirport;
        this.destinationAirport = destinationAirport;
        this.aircraft = aircraft;
        this.actualDepartureTime = actualDepartureTime;
        this.actualArrivalTime = actualArrivalTime;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.scheduledDepartureTime = scheduledDepartureTime;
    }

    // Getters
    public String getFlightNumber() {
        return flightNumber;
    }
    public Airport getSourceAirport(){
        return sourceAirport;
    }
    public Airport getDestinationAirport(){
        return destinationAirport;
    }
    public Aircraft getAircraft(){
        return aircraft;
    }
    public LocalDateTime getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }
    public LocalDateTime getScheduledDepartureTime() {
        return scheduledDepartureTime;
    }


    //Displays info on a flight if the user is unregistered
    public String displayBasicFlightInfo(){
        return "Flight number: " + flightNumber + ", Source airport: " + sourceAirport.getName() + ", Airport code: " + sourceAirport.getAirportCode() +
                ", Destination airport: " + destinationAirport.getName() + ", Airport code: " + destinationAirport.getAirportCode();
    }

    //Displays additional info compared to the displayBasicFlightInfo() function right above
    //This is used in subclasses cargo, commercial and private flight for registered users
    public String displayFlightInfo(){
        return displayBasicFlightInfo() + ",\nAircraft model: " + aircraft.getModel() + ", Aircraft id: "  + aircraft.getAircraftId();
    }


    //Searches for a flight
    public static void searchFlights(User user, Scanner scanner, FlightCatalog flightCatalog, AirportCatalog airportCatalog){
        //if the user is an airport administrator we display info for private flights, and info for non-private flights,
        // but only if the flight has the same source or destination airport as the airport administrator's airport
        if(user instanceof AirportAdministrator){
            Airport AirportAdmin = ((AirportAdministrator)user).getAirport();

            //make sure the user enters an existing source airport
            boolean foundSourceAirport = false;
            Airport sourceAirport = null;
            while(!foundSourceAirport){
                System.out.println("Enter the source airport code");
                String sourceAirportCode = scanner.nextLine();
                for(Airport airport: airportCatalog.getAirports()){
                    if(airport.getAirportCode().equals(sourceAirportCode)){
                        sourceAirport = airport;
                        foundSourceAirport = true;
                        break;
                    }
                }if(!foundSourceAirport){
                    System.out.println("Error: the code that you entered does not correspond to any existing airport.");
                }
            }

            //make sure the user enters an existing destination airport and that it is not the same as the source airport
            boolean foundDestinationAirport = false;
            Airport destinationAirport = null;
            while(!foundDestinationAirport){
                System.out.println("Enter the destination airport code");
                String destinationAirportCode = scanner.nextLine();
                //make sure the destination airport code is not the same as the source airport code
                if(!(destinationAirportCode.equals(AirportAdmin.getAirportCode()))){
                    for(Airport airport: airportCatalog.getAirports()){
                        //Make sure the user entered an existing destination airport in the database
                        if(airport.getAirportCode().equals(destinationAirportCode)){
                            destinationAirport = airport;
                            foundDestinationAirport = true;
                            break;
                        }
                    }if(!foundDestinationAirport){
                        System.out.println("Error: the code that you entered does not correspond to any existing airport.");
                    }
                }else{
                    System.out.println("Error: you've entered the same airport for the destination airport.");
                }
            }
            //Retrieve all the private flights interacting with the airport of the airport admin in case the source or destination airport is where he works
            List<PrivateFlight> privateFlights = new ArrayList<>();
            if(sourceAirport.equals(AirportAdmin) || destinationAirport.equals(AirportAdmin)) {
                for (Flight flight : flightCatalog.getFlights()) {
                    if (flight.getSourceAirport().equals(sourceAirport) && flight.getDestinationAirport().equals(destinationAirport)) {
                        if (flight instanceof PrivateFlight) {
                            privateFlights.add((PrivateFlight) flight);
                        }
                    }
                }
            }
            //If there are private flights interacting with the airport of the airport admin, we display them,
            // otherwise we indicate that there is none
            if(!(privateFlights.isEmpty())){
                System.out.println("Here is information about private flights incoming to your airport or taking off from it");
                for(PrivateFlight privateFlight: privateFlights ){
                    System.out.println(privateFlight.displayFlightInfo());
                }
            }else{
                System.out.println("There is no private flight passing through your airport");
            }

            //Retrieving all non-private flights interacting with the source and destination airport
            List<Flight> nonPrivateFlightsOfAirportAdmin = new ArrayList<>();
            for(Flight flight: flightCatalog.getFlights()){
                if(flight.getSourceAirport().equals(sourceAirport) && flight.getDestinationAirport().equals(destinationAirport)){
                    if(flight instanceof CargoFlight || flight instanceof CommercialFlight){
                        nonPrivateFlightsOfAirportAdmin.add(flight);
                    }
                }
            }

            //If there are non-private flights interacting with the airport of the airport admin, we display them,
            // otherwise we indicate that there is none
            if(!(nonPrivateFlightsOfAirportAdmin.isEmpty())){
                System.out.println("Here is information about non private flights incoming to your airport or taking off from it");
                for(Flight flight: nonPrivateFlightsOfAirportAdmin){
                    System.out.println(flight.displayFlightInfo());
                }
            }else {
                System.out.println("There is no non private flight passing through your airport");
            }
            //if the user is not an airport admin and is unregistered, we only display info on non-private flights.
            //If the user is registered and is not an airport admin, we only display info on non-private flights but add additional info such as airline name and aircraft ID and model
        }else {
            //make sure the user enters an existing source airport
            boolean foundSourceAirport = false;
            Airport sourceAirport = null;
            while(!foundSourceAirport){
                System.out.println("Enter the source airport code");
                String sourceAirportCode = scanner.nextLine();
                for(Airport airport: airportCatalog.getAirports()){
                    if(airport.getAirportCode().equals(sourceAirportCode)){
                        sourceAirport = airport;
                        foundSourceAirport = true;
                        break;
                    }
                }if(!foundSourceAirport){
                    System.out.println("Error: the code that you entered does not correspond to any existing airport.");
                }
            }
            //make sure the user enters an existing destination airport and that it is not the same as the source airport
            boolean foundDestinationAirport = false;
            Airport destinationAirport = null;
            while(!foundDestinationAirport){
                System.out.println("Enter the destination airport code");
                String destinationAirportCode = scanner.nextLine();
                //make sure the destination airport code is not the same as the source airport code
                if(!(destinationAirportCode.equals(sourceAirport.getAirportCode()))){
                    for(Airport airport: airportCatalog.getAirports()){
                        //Make sure the user entered an existing destination airport in the database
                        if(airport.getAirportCode().equals(destinationAirportCode)){
                            destinationAirport = airport;
                            foundDestinationAirport = true;
                            break;
                        }
                    }if(!foundDestinationAirport){
                        System.out.println("Error: the code that you entered does not correspond to any existing airport.");
                    }
                }else{
                    System.out.println("Error: you've entered the same airport for the destination airport.");
                }
            }
            //Retrieve all the non-private flights that have the corresponding source airport and destination airport
            List<Flight> nonPrivateFlights = new ArrayList<>();
            for(Flight flight: flightCatalog.getFlights()){
                if(flight.getSourceAirport().equals(sourceAirport) && flight.getDestinationAirport().equals(destinationAirport)){
                    if(flight instanceof CargoFlight || flight instanceof CommercialFlight){
                        nonPrivateFlights.add(flight);
                    }
                }
            }
            //If there are no non-private flights with the corresponding source and destination airport, we display a message inquiring about that
            if(!(nonPrivateFlights.isEmpty())){
                System.out.println("Here is information about non-private flights with the corresponding source and destination airport");
                //If the user is not registered, we display basic info for non-private flights, such as flight number, source and destination airport
                if(!user.isRegistered){
                    for(Flight flight: nonPrivateFlights){
                        System.out.println(flight.displayBasicFlightInfo());
                    }
                    // if the user is registered, we display info for non-private flights, such as flight number, source and destination airport, airline name and aircraft info
                }else if(user instanceof SystemAdministrator || user instanceof AirlineAdministrator || user instanceof NormalUser){
                    for(Flight flight: nonPrivateFlights){
                        System.out.println(flight.displayFlightInfo());
                    }
                }
            }else{
                System.out.println("There are currently no flights that have the corresponding source and destination airport");
            }
        }
    }



    //This function adds a flight, only airport admins, airline admins and system admins can add flights
    //If user wants to add a private flight, they need to either be an airport or system admin
    //If user wants to add a non-private flight, they need to either be an airline or system admin
    //
    //
    //If user is an airport admin, they will have the possibility to add a private flight for aircraft that are stationed at the
    // airport admin's airport at the scheduled departure time and
    // are not owned by any airline
    //
    //If user is a system admin and wants to add a private flight, they will be able to add a private flight
    //for all stationed aircraft at the scheduled departure time that are not owned by any airline
    //
    //
    //If user is an airline admin, they will be able to assign a non-private flight to one of the airline's STATIONED aircraft
    // at the scheduled departure time of the
    //
    //If user is a system admin and wants to add a non-private flight, they will be able to add a non-private to aircraft that belong to airlines
    //And that are stationed at the scheduled departure time
    public static void addFlight(User user, Scanner scanner, FlightCatalog flightCatalog, AircraftCatalog aircraftCatalog, AirlineCatalog airlineCatalog, AirportCatalog airportCatalog) {
        if (user instanceof AirportAdministrator || user instanceof AirlineAdministrator || user instanceof SystemAdministrator) {
            String flightType=null;
            boolean hasPermission = false;
            //let the user choose the type of flight they want to add
            while(!hasPermission){
                System.out.println("Choose the number corresponding to the type of flight you wish to add");
                System.out.println("1. Private");
                System.out.println("2. Commercial");
                System.out.println("3. Cargo");
                String number = scanner.nextLine();
                switch (number){
                    case "1":
                        if(user instanceof AirportAdministrator || user instanceof SystemAdministrator){
                            //System.out.println("You can add a private flight but only if its source airport is the same as the airport you are working for");
                            flightType = "private";
                            hasPermission = true;
                        }else{
                            System.out.println("You do not have the necessary privileges to add a private flight.");
                        }
                        break;
                    case "2":
                        if(user instanceof AirlineAdministrator || user instanceof SystemAdministrator){
                            //System.out.println("You can add a commercial flight but only if you have an available aircraft in the source airport");
                            flightType = "commercial";
                            hasPermission = true;
                        }else{
                            System.out.println("You do not have the necessary privileges to add a commercial flight.");
                        }
                        break;
                    case "3":
                        if(user instanceof AirlineAdministrator || user instanceof SystemAdministrator){
                            //System.out.println("You can add a cargo flight but only if you have an available aircraft in the source airport");
                            flightType = "cargo";
                            hasPermission = true;
                        }else{
                            System.out.println("You do not have the necessary privileges to add a cargo flight.");
                        }
                        break;
                    default:
                        System.out.println("You did not choose a number between 1 and 3");
                        break;
                }
            }

            boolean okScheduledDeparture = false;
            Aircraft chosenAircraft = null;
            LocalDateTime scheduledDeparture = null;
            //We ensure that we pick an aircraft according to the user's privileges and that the scheduled and arrival time are unique to the airport
            while(!okScheduledDeparture) {
                //Enter the scheduled and actual, arrival and departure time
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                // Prompt the user to enter scheduled departure time
                boolean correctScheduledDeparture = false;
                while (!(correctScheduledDeparture)) {
                    // Prompt the user to enter scheduled departure time
                    System.out.println("Enter the scheduled departure time (yyyy-MM-dd HH:mm):");
                    try {
                        scheduledDeparture = LocalDateTime.parse(scanner.nextLine(), format);
                        correctScheduledDeparture = true;
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: Invalid date-time format. Please enter the date-time in yyyy-MM-dd HH:mm format.");
                    }
                }

                //Stationed aircrafts owned by the airline
                List<Aircraft> stationedAircraftsOwned = new ArrayList<>();
                //Stationed aircrafts stationed in the Airport adminstrator's airport
                List<Aircraft> aircraftInAirport = new ArrayList<>();
                //Create a container containing the private aircrafts in the Airport adminstrator's airport
                List<Aircraft> privateAircraftsInAirport = new ArrayList<>();
                // All the stationed aircrafts
                List<Aircraft> stationedAircrafts = new ArrayList<>();

                //check if the airline administrator has any aircrafts that are stationed at that the scheduled departure time
                // if yes, we collect them
                if (user instanceof AirlineAdministrator) {
                    //Aircrafts owned by the airline
                    List<Aircraft> aircraftsOwned = ((AirlineAdministrator) user).getAirline().getFleet();

                    //We check if the aircraft owned by the airline are available meaning they are not in transit and do not have future flights after the specified time
                    for (Aircraft aircraft : aircraftsOwned) {
                        if (aircraft.checkStatus(flightCatalog, scheduledDeparture)) {
                            stationedAircraftsOwned.add(aircraft);
                        }
                    }
                    //check if there are no aircraft available
                    if(stationedAircraftsOwned.isEmpty()){
                        System.out.println("Your airline does not have any aircraft stationed in any airport");
                        return;
                    }else{
                        //Here we display the available aircrafts according to the user type
                        // and let them choose one aircraft that will handle a flight
                        System.out.println("We have found some aircrafts stationed in some airports, here are their information");
                        chosenAircraft = selectAircraftFromList(stationedAircraftsOwned,scanner,flightCatalog);
                    }

                    //check if there are aircrafts that are not owned by an airline and are stationed in the airport where the airport administrator works
                } else if (user instanceof AirportAdministrator) {
                    List<Aircraft> privateAircrafts = new ArrayList<>();
                    //check the status of every aircraft to see if they are available and are stationed in the airport administrator's airport at the scheduled departure time
                    for (Aircraft aircraft : aircraftCatalog.getAircrafts()) {
                        if (aircraft.checkStatus(flightCatalog, scheduledDeparture)) {
                            if (aircraft.getLocationAtLatestFlight(flightCatalog).equals(((AirportAdministrator) user).getAirport())) {
                                aircraftInAirport.add(aircraft);
                            }
                        }
                    }

                    //Remove the aircrafts owned by an airline from the list by using an iterator.
                    Iterator<Aircraft> iterator = aircraftInAirport.iterator();
                    boolean aircraftBelongsToAirline;
                    while (iterator.hasNext()) {
                        Aircraft aircraft = iterator.next();
                        aircraftBelongsToAirline = false;
                        for (Airline airline : airlineCatalog.getAirlines()) {
                            for (Aircraft aircraft1 : airline.getFleet()) {
                                if (aircraft1.getAircraftId() == aircraft.getAircraftId()) {
                                    iterator.remove(); // Using iterator's remove method
                                    aircraftBelongsToAirline = true;
                                    break;
                                }
                            }
                            if (aircraftBelongsToAirline) {
                                break;
                            }
                        }// If the aircraft doesn't belong to an airline, add it to privateAircraftsInAirport
                        if (!aircraftBelongsToAirline) {
                            privateAircraftsInAirport.add(aircraft);
                        }
                    }

                    if(privateAircraftsInAirport.isEmpty()){
                        System.out.println("There are no aircrafts stationed in your airport that are not owned by any airline");
                        return;
                    }else{
                        System.out.println("We have found some aircrafts stationed in your airport, here are their information");
                        chosenAircraft = selectAircraftFromList(privateAircraftsInAirport,scanner,flightCatalog);
                    }
                } else if (user instanceof SystemAdministrator) {
                    //the system administrator can manage records on airports, meaning it can
                    //assign a flight to any aircrafts that's stationed
                    //Here we are retrieving all the stationed aircrafts
                    //Depending on the flight type the system administrator wants to add
                    List<Aircraft> ownedAircraftsStationed = new ArrayList<>();
                    List<Aircraft> aircraftsStationed = new ArrayList<>();

                    //Retrieving all the aircrafts that belong to airlines that are currently stationed
                    for (Airline airline : airlineCatalog.getAirlines()) {
                        for (Aircraft aircraft : airline.getFleet()) {
                            if (aircraft.checkStatus(flightCatalog, scheduledDeparture)) {
                                ownedAircraftsStationed.add(aircraft);
                            }
                        }
                    }
                    //Retrieving all the aircrafts that are currently stationed
                    for (Aircraft aircraft : aircraftCatalog.getAircrafts()) {
                        if (aircraft.checkStatus(flightCatalog, scheduledDeparture)) {
                            aircraftsStationed.add(aircraft);
                        }
                    }
                    //Checking whether System Administrator entered a private or non private flight
                    //This will determine what aircrafts we will retrieve
                    //If the System Administrator wants to add a non private flight
                    //this will only retrieve stationed aircrafts that belong to some airlines
                    //However if the system administrator wants to add a private flight
                    //this will only retrieve stationed aircrafts that do not belong to any airline
                    for (Aircraft aircraft : aircraftsStationed) {
                        boolean aircraftBelongsToAirline = false;
                        for (Aircraft aircraft1 : ownedAircraftsStationed) {
                            if (aircraft.equals(aircraft1)) {
                                aircraftBelongsToAirline = true;
                                if (flightType.equals("commercial") || flightType.equals("cargo")) {
                                    stationedAircrafts.add(aircraft);
                                }
                                break;
                            }
                        }
                        if (!aircraftBelongsToAirline && flightType.equals("private")) {
                            stationedAircrafts.add(aircraft);
                        }
                    }
                    if(stationedAircrafts.isEmpty()){
                        System.out.println("There are no stationed aircrafts that can be assigned a flight at the moment");
                        return;
                    }else{
                        System.out.println("We have found some stationed aircrafts, here are their information");
                        chosenAircraft = selectAircraftFromList(stationedAircrafts,scanner,flightCatalog);
                    }
                }

                //Check if there is a conflict with another aircraft attempting to land or take off from that airport at the specified time
                if(chosenAircraft.getLocationAtLatestFlight(flightCatalog).verifyAvailability(scheduledDeparture, flightCatalog)){
                    okScheduledDeparture = true;
                }else{
                    System.out.println("Error: the scheduled departure time is in conflict with another aircraft attempting to land or take off from that airport");
                }

            }


            boolean uniqueFlightNumber = false;
            String flightNumber=null;
            //check if the entered flight number is unique
            while (!uniqueFlightNumber) {
                System.out.println("Enter a new flight number:");
                flightNumber = scanner.nextLine();
                boolean unique = true; // Assume the flight number is unique initially

                // Check if the entered flight number is unique
                for (Flight flight : flightCatalog.getFlights()) {
                    if (flight.getFlightNumber().equals(flightNumber)) {
                        System.out.println("This flight number already exists.");
                        unique = false; // Flight number is not unique
                        break;
                    }
                }
                // If the flight number is unique, exit the loop
                if (unique) {
                    uniqueFlightNumber = true;
                }
            }



            boolean destinationAirportFound = false;
            Airport destinationAirport = null;


            //Make sure the source and destination airport are different and the destination airport exists in the database
            while (!(destinationAirportFound)){
                System.out.println("Enter the destination airport code");
                String destinationAirportCode = scanner.nextLine();

                //check if the user did not  put the destination airport the same as the source airport of the chosen aircraft
                if(chosenAircraft.getLocationAtLatestFlight(flightCatalog).getAirportCode().equals(destinationAirportCode)){
                    System.out.println("The source and destination airport code are the same");
                    continue;
                }
                //check if the destination airport exists
                for(Airport airport : airportCatalog.getAirports()){
                    if(airport.getAirportCode().equals(destinationAirportCode)){
                        System.out.println("The destination airport code you entered corresponds to an actual airport in the database");
                        destinationAirport = airport;
                        destinationAirportFound = true;
                        break;
                    }
                }
                if(!destinationAirportFound){
                    System.out.println("The destination airport code does not correspond to any airport");
                }
            }


            //Enter the scheduled and actual, arrival and departure time
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime scheduledArrival = null;

            boolean correctScheduledArrival = false;
            while(!(correctScheduledArrival)){
                // Prompt the user to enter scheduled arrival time
                System.out.println("Enter the scheduled arrival time (yyyy-MM-dd HH:mm):");
                try{
                    scheduledArrival = LocalDateTime.parse(scanner.nextLine(), formatter);
                    if(scheduledDeparture.isAfter(scheduledArrival)){
                        System.out.println("Error: Scheduled arrival time cannot be before scheduled departure time.");

                        // Verify if the user did not enter the same scheduled departure and arrival time
                    }else if(scheduledDeparture.isEqual(scheduledArrival)){
                        System.out.println("Error: Scheduled departure and arrival time cannot be equal");
                    }
                    // Verify if there is no other aircraft attempting to land or take off from the destination airport at that time
                    else if(!(destinationAirport.verifyAvailability(scheduledArrival,flightCatalog))){
                        System.out.println("Error: the scheduled arrival time is in conflict with another aircraft attempting to land or take off from that airport");
                    }else{
                        correctScheduledArrival = true;
                    }
                }catch (DateTimeParseException e) {
                    System.out.println("Error: Invalid date-time format. Please enter the date-time in yyyy-MM-dd HH:mm format.");
                }
            }

            boolean correctActualDeparture = false;
            LocalDateTime actualDeparture = null;
            // Prompt the user to enter actual departure time, if he uses the wrong format, it will prompt him again
            while(!correctActualDeparture){
                System.out.println("Enter actual departure time (yyyy-MM-dd HH:mm):");
                try {
                    actualDeparture = LocalDateTime.parse(scanner.nextLine(), formatter);
                    correctActualDeparture = true;
                }catch (DateTimeParseException e) {
                    System.out.println("Error: Invalid date-time format. Please enter the date-time in yyyy-MM-dd HH:mm format.");
                }
            }
            LocalDateTime actualArrival = null;
            while(actualArrival==null || actualDeparture.isAfter(actualArrival) || actualDeparture.isEqual(actualArrival)){
                // Prompt the user to enter the actual arrival time
                System.out.println("Enter actual arrival time (yyyy-MM-dd HH:mm):");
                try{
                    actualArrival = LocalDateTime.parse(scanner.nextLine(), formatter);
                    if(actualDeparture.isEqual(actualArrival)){
                        System.out.println("Error: the actual arrival and departure time cannot be equal");
                    }else if(actualDeparture.isAfter(actualArrival)){
                        System.out.println("Error: the actual arrival time cannot be before the actual departure time");
                    }
                }catch (DateTimeParseException e) {
                    System.out.println("Error: Invalid date-time format. Please enter the date-time in yyyy-MM-dd HH:mm format.");
                }
            }

            switch (flightType){
                case "private":
                    flightCatalog.addFlight(new PrivateFlight(flightNumber, chosenAircraft.getLocationAtLatestFlight(flightCatalog), destinationAirport, chosenAircraft,
                            actualDeparture, scheduledDeparture, actualArrival, scheduledArrival));
                    System.out.println("Private flight was successfully added");
                    break;
                case "commercial":
                    if(user instanceof AirlineAdministrator){
                        flightCatalog.addFlight(new CommercialFlight(flightNumber, chosenAircraft.getLocationAtLatestFlight(flightCatalog), destinationAirport, chosenAircraft,
                                actualDeparture, scheduledDeparture, actualArrival, scheduledArrival, ((AirlineAdministrator)user).getAirline()));
                        System.out.println("Commercial flight was successfully added");
                    }else if(user instanceof SystemAdministrator){
                        if(chosenAircraft.getAirline(airlineCatalog)!= null){
                            flightCatalog.addFlight(new CommercialFlight(flightNumber, chosenAircraft.getLocationAtLatestFlight(flightCatalog), destinationAirport, chosenAircraft,
                                    actualDeparture, scheduledDeparture, actualArrival, scheduledArrival, chosenAircraft.getAirline(airlineCatalog)));
                            System.out.println("Commercial flight was successfully added");
                        }else {
                            System.out.println("The aircraft apparently does not have an airline. There must be a problem in the code");
                        }
                    }
                    break;
                case "cargo":
                    if(user instanceof AirlineAdministrator){
                        flightCatalog.addFlight(new CargoFlight(flightNumber, chosenAircraft.getLocationAtLatestFlight(flightCatalog), destinationAirport, chosenAircraft,
                                actualDeparture, scheduledDeparture, actualArrival, scheduledArrival, ((AirlineAdministrator)user).getAirline()));
                        System.out.println("Cargo flight was successfully added");

                    }
                    else if(user instanceof SystemAdministrator){
                        if(chosenAircraft.getAirline(airlineCatalog)!=null){
                            flightCatalog.addFlight(new CargoFlight(flightNumber, chosenAircraft.getLocationAtLatestFlight(flightCatalog), destinationAirport, chosenAircraft,
                                    actualDeparture, scheduledDeparture, actualArrival, scheduledArrival, chosenAircraft.getAirline(airlineCatalog)));
                            System.out.println("Cargo flight was successfully added");
                        }else{
                            System.out.println("The aircraft apparently does not have an airline. The flight was not added. There must be a problem in the code");
                        }
                    }
                    break;
            }
        }else {
            System.out.println("You do not have the necessary privileges to add a flight.");
        }
    }


    //Selects one aircraft from a list and returns it
    private static Aircraft selectAircraftFromList(List<Aircraft> aircraftList, Scanner scanner, FlightCatalog flightCatalog) {
        while (true) {
            System.out.println("The existing aircraft:");
            for (Aircraft aircraft : aircraftList) {
                System.out.println(aircraft.displayAircraftInfo(flightCatalog));
            }
            System.out.println("Enter the ID of the aircraft you want to assign a flight:");
            try {
                int aircraftId = Integer.parseInt(scanner.nextLine());
                for (Aircraft aircraft : aircraftList) {
                    if (aircraft.getAircraftId() == aircraftId) {
                        System.out.println("Aircraft selected successfully.");
                        return aircraft;
                    }
                }
                System.out.println("Enter an existing id of those mentioned.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer ID.");
            }
        }
    }





}
