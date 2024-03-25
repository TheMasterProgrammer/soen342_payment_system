import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Airline {
    private String AirlineName;

    private List<Aircraft> fleet;

    //we need to add at least 2 aircrafts in order for a company to be considered an airline
    public Airline(String AirlineName, Aircraft aircraft1, Aircraft aircraft2){
        this.AirlineName = AirlineName;
        fleet = new ArrayList<>();
        addAircraft(aircraft1);
        addAircraft(aircraft2);
    }

    public String getAirlineName() {
        return AirlineName;
    }

    //Adds an aircraft to the fleet of an airline
    public void addAircraft(Aircraft aircraft){
        fleet.add(aircraft);
    }

    //Returns the collection of aircraft that are owned by an airline
    public List<Aircraft> getFleet() {
        return fleet;
    }


    //Displays info of the airline and its fleet
    public void displayAirlineInfo(FlightCatalog flightCatalog){
        System.out.println("Airline name: " + AirlineName + ", fleet is composed of: ");
        for(Aircraft aircraft: fleet){
            System.out.println(aircraft.displayAircraftInfo(flightCatalog));
        }
    }

    //Displays info on all airlines
    public static void displayAirlinesInfo(FlightCatalog flightCatalog, AirlineCatalog airlineCatalog){
        for (Airline airline : airlineCatalog.getAirlines()) {
            airline.displayAirlineInfo(flightCatalog);
        }
    }

    //Checks if an airline existing by entering its name
    public static boolean airlineExists(String airlineName, AirlineCatalog airlineCatalog){
        for(Airline airline1: airlineCatalog.getAirlines()){
            if(airline1.getAirlineName().equals(airlineName)){
                return true;
            }
        }
        return false;
    }


    //Finds an airline based on its name
    public static Airline findAirline(String airlineName, AirlineCatalog airlineCatalog){
        for(Airline airline1: airlineCatalog.getAirlines()){
            if(airline1.getAirlineName().equals(airlineName)){
                return airline1;
            }
        }
        return null;
    }

    //Registers an airline, only the system admin has access to this feature
    public static void addAirline(User user, Scanner scanner, AirlineCatalog airlineCatalog, AircraftCatalog aircraftCatalog, FlightCatalog flightCatalog){
        if(user instanceof SystemAdministrator){
            //We store the available aircrafts
            List<Aircraft> availableAircrafts = new ArrayList<>();
            for(Aircraft aircraft: aircraftCatalog.getAircrafts()){
                if(!(aircraft.hadAnyFlight(flightCatalog))){
                    if(aircraft.getAirline(airlineCatalog)==null){
                        availableAircrafts.add(aircraft);
                    }
                }
            }
            if(availableAircrafts.size()<2){
                System.out.println("There are currently less than 2 aircrafts available, you need to register more aircrafts before you can register as an airline.");
            }else{
                //We display the available aircraft
                System.out.println("Here are the available aircraft");
                for(Aircraft aircraft: availableAircrafts){
                    System.out.println(aircraft.displayAircraftInfo(flightCatalog));
                }
                Aircraft aircraft1 = null;
                boolean firstAircraft = false;
                //Make sure the user registers an available aircraft under the company's name
                while(!firstAircraft){
                    System.out.println("Write the id of the first aircraft that you want to register under the company's name");
                    try{
                        //Ensure that the user enters a correct id
                        int id = Integer.parseInt(scanner.nextLine());
                        for(Aircraft aircraft: availableAircrafts){
                            if(aircraft.getAircraftId()==id){
                                aircraft1 = aircraft;
                                firstAircraft = true;
                                break;
                            }
                        }if(!firstAircraft){
                            System.out.println("Error: the id you've entered does not correspond to any aircrafts available");
                        }else{
                            availableAircrafts.remove(aircraft1);
                        }
                    }catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid integer id.");
                    }
                }
                Aircraft aircraft2 = null;
                //Make sure the user registers a second available aircraft under the company's name
                boolean secondAircraft = false;
                while(!secondAircraft){
                    System.out.println("Write the id of the second aircraft that you want to register under the company's name: ");
                    try{
                        //Ensure that the user enters a correct id
                        int id = Integer.parseInt(scanner.nextLine());
                        for(Aircraft aircraft: availableAircrafts){
                            if(aircraft.getAircraftId()==id){
                                aircraft2 = aircraft;
                                secondAircraft = true;
                                break;
                            }
                        }if(!secondAircraft){
                            System.out.println("Error: the id you've entered does not correspond to any aircrafts available");
                        }
                    }catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid integer id.");
                    }
                }
                boolean uniqueAirline = false;
                String airlineName = null;
                //Make sure the user enters a unique airline name
                while(!uniqueAirline){
                    System.out.println("Enter a unique airline name: ");
                    airlineName = scanner.nextLine();
                    boolean unique = true;
                    for(Airline airline: airlineCatalog.getAirlines()){
                        if(airline.getAirlineName().equals(airlineName)){
                            unique = false;
                            System.out.println("Error: The entered airline name already corresponds to an existing airline in the database");
                            break;
                        }
                    }if(unique){
                        uniqueAirline = true;
                    }
                }
                airlineCatalog.addAirline(new Airline(airlineName,aircraft1,aircraft2));
                System.out.println("Success, airline was created!");
            }
        }else{
            System.out.println("You do not have the necessary privileges to add an airline.");
        }

    }


    //Adds an aircraft to the fleet of an airline
    public static void addAircraftToFleet(User user, Scanner scanner, AirlineCatalog airlineCatalog, AircraftCatalog aircraftCatalog, FlightCatalog flightCatalog){
        if(user instanceof SystemAdministrator || user instanceof AirlineAdministrator){
            //We store the available aircrafts
            List<Aircraft> availableAircrafts = new ArrayList<>();
            for(Aircraft aircraft: aircraftCatalog.getAircrafts()){
                if(!(aircraft.hadAnyFlight(flightCatalog))){
                    if(aircraft.getAirline(airlineCatalog)==null){
                        availableAircrafts.add(aircraft);
                    }
                }
            }
            if(availableAircrafts.isEmpty()){
                System.out.println("There are currently no available aircrafts");
            }else{
                //We display the available aircraft
                System.out.println("Here are the available aircraft");
                for(Aircraft aircraft: availableAircrafts){
                    System.out.println(aircraft.displayAircraftInfo(flightCatalog));
                }
                if(user instanceof AirlineAdministrator){
                    Aircraft aircraft1 = null;
                    boolean existingAircraft = false;
                    //Make sure the user registers an available aircraft under the airline's name
                    while(!existingAircraft){
                        System.out.println("Write the id of the aircraft that you want to register under the your airline's name");
                        System.out.println("You work for " + ((AirlineAdministrator)user).getAirline().getAirlineName());
                        try{
                            //Ensure that the user enters a correct id
                            int id = Integer.parseInt(scanner.nextLine());
                            for(Aircraft aircraft: availableAircrafts){
                                if(aircraft.getAircraftId()==id){
                                    aircraft1 = aircraft;
                                    existingAircraft = true;
                                    break;
                                }
                            }if(!existingAircraft){
                                System.out.println("Error: the id you've entered does not correspond to any aircrafts available");
                            }else{
                                //Adding the aircraft to the fleet of the airline
                                ((AirlineAdministrator)user).getAirline().addAircraft(aircraft1);
                            }
                        }catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid integer id.");
                        }
                    }
                }else if (user instanceof SystemAdministrator){
                    //We check if there are first of all airlines in the database
                    if(airlineCatalog.getAirlines().isEmpty()){
                        System.out.println("There are no airlines in the database, so you cannot add an aircraft the fleet of an airline");
                    }else{
                        //We display to the user the existing airlines information and let them choose which one they want to add an aircraft to its fleet
                        Airline airlineDesired = null;
                        Airline.displayAirlinesInfo(flightCatalog, airlineCatalog);
                        boolean correctAirline = false;
                        while(!correctAirline){
                            System.out.println("Write the airline name for which you desire registering an aircraft to its fleet");
                            String airlineName = scanner.nextLine();
                            if(Airline.airlineExists(airlineName, airlineCatalog)){
                                airlineDesired = Airline.findAirline(airlineName, airlineCatalog);
                                correctAirline = true;
                            }
                            if(!correctAirline){
                                System.out.println("Error: the airline name that you entered does not correspond to any airline in the database");
                            }
                        }
                        Aircraft aircraft1 = null;
                        boolean existingAircraft = false;
                        //Make sure the user registers an available aircraft under the airline's name
                        while(!existingAircraft){
                            System.out.println("Write the id of the aircraft that you want to register under the your airline's name");
                            System.out.println("You will add an aircraft to " + airlineDesired.getAirlineName());
                            try{
                                //Ensure that the user enters a correct id
                                int id = Integer.parseInt(scanner.nextLine());
                                for(Aircraft aircraft: availableAircrafts){
                                    if(aircraft.getAircraftId()==id){
                                        aircraft1 = aircraft;
                                        existingAircraft = true;
                                        break;
                                    }
                                }if(!existingAircraft){
                                    System.out.println("Error: the id you've entered does not correspond to any aircrafts available");
                                }else{
                                    //Adding the aircraft to the fleet of the airline
                                    airlineDesired.addAircraft(aircraft1);
                                    System.out.println("Aircraft was added");
                                }
                            }catch (NumberFormatException e) {
                                System.out.println("Invalid input. Please enter a valid integer id.");
                            }
                        }

                    }
                }
            }
        }else{
            System.out.println("You do not have the necessary privileges to add an aircraft to an airline's fleet.");
        }

    }


}
