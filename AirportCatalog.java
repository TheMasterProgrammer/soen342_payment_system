import java.util.ArrayList;
import java.util.List;

public class AirportCatalog {
    private static AirportCatalog airportCatalog = null;
    private List<Airport> airports;
    private AirportCatalog(){
        airports = new ArrayList<>();
        //Some hardcoded data

        airports.add(new Airport("YUL","Montréal-Pierre Elliott Trudeau International Airport", CityCatalog.getCityCatalogInstance().getCities().get(0))); // Registering an airport in Montreal
        airports.add(new Airport("YYZ","Toronto Pearson International Airport", CityCatalog.getCityCatalogInstance().getCities().get(1))); // Registering an airport in Toronto
        airports.add(new Airport("YOW","Ottawa International Airport", CityCatalog.getCityCatalogInstance().getCities().get(2))); // Registering an airport in Ottawa
        airports.add(new Airport("AUS","Austin-Bergstrom International Airport", CityCatalog.getCityCatalogInstance().getCities().get(3))); // Registering an airport in Austin
    }
    public static AirportCatalog getAirportCatalogInstance(){
        if(airportCatalog==null){
            airportCatalog = new AirportCatalog();
        }
        return airportCatalog;
    }

    //Returns the existing airports in the database
    public List<Airport> getAirports(){
        return airports;
    }

    //Adds an airport to the database
    public void addAirport(Airport airport){
        airports.add(airport);
    }

}
