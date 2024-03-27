import java.util.Scanner;

public class City {
    private int temperature;
    private String name;
    private String country;
    public City(String name, String country, int temperature){
        this.country = country;
        this.name = name;
        this.temperature = temperature;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public int getTemperature() {
        return temperature;
    }


    //Displays info about all the cities in the database
    public static void displayCitiesInfo(CityCatalog cityCatalog){
        for(City city: cityCatalog.getCities()){
            System.out.println("City name: " + city.getName() + ", Country: " + city.getCountry() + ", Temperature: " + city.getTemperature());
        }

    }

    //Checks if a city exists based on its name and country
    public static boolean verifyCityExistance(String cityName, String cityCountry, CityCatalog cityCatalog){
        for(City city :  cityCatalog.getCities()){
            if(cityName.equals(city.getName()) && cityCountry.equals(city.getCountry())){
                return true;
            }
        }
        return false;
    }


    //Returns a city object by searching its name and country
    public static City getCity(String cityName, String cityCountry, CityCatalog cityCatalog){
        for(City city : cityCatalog.getCities()){
            if(cityName.equals(city.getName()) && cityCountry.equals(city.getCountry())){
                return city;
            }
        }
        return null;
    }


    //Adds a city to the database
    public static void addCity(User user, Scanner scanner, CityCatalog cityCatalog){
        if(user instanceof SystemAdministrator){
            boolean correctCity = false;
            while(!correctCity){
                System.out.println("Enter the name of the city: ");
                String cityName = scanner.nextLine();
                System.out.println("Now enter the country where the city is situated: ");
                String cityCountry = scanner.nextLine();
                //We verify if the city already exists
                if(!(verifyCityExistance(cityName,cityCountry, cityCatalog))){
                    boolean correctTemperature = false;
                    while(!correctTemperature){
                        System.out.println("Enter the city's temperature");
                        try{
                            //Ensure that the user enters a correct temperature
                            int temperature = Integer.parseInt(scanner.nextLine());
                            correctTemperature = true;
                            correctCity = true;
                            cityCatalog.addCity(new City(cityName,cityCountry,temperature));
                            System.out.println("City was successfully added");
                        }catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid integer temperature.");
                        }
                    }
                }else{
                    System.out.println("A city with the same name in the same country already exists. Please provide a new city");
                }
            }
        }else{
            System.out.println("You do not have the necessary privileges to add a city.");
        }
    }




}
