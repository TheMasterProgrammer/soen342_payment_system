import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AircraftCatalog {
    private static AircraftCatalog aircraftCatalog = null;
    private List<Aircraft> aircrafts;
    private AircraftCatalog() {
        aircrafts = new ArrayList<>();
        //Some hardcoded data

        aircrafts.add(new Aircraft("Boeing 737",AirportCatalog.getAirportCatalogInstance().getAirports().get(0))); //Registering an aircraft in Montreal Airport
        aircrafts.add(new Aircraft("Boeing 777",AirportCatalog.getAirportCatalogInstance().getAirports().get(0))); //Registering an aircraft in Montreal Airport
        aircrafts.add(new Aircraft("Boeing 787",AirportCatalog.getAirportCatalogInstance().getAirports().get(1))); //Registering an aircraft in Toronto Airport
        aircrafts.add(new Aircraft("Airbus A330",AirportCatalog.getAirportCatalogInstance().getAirports().get(1))); //Registering an aircraft in Toronto Airport
        aircrafts.add(new Aircraft("Airbus A330",AirportCatalog.getAirportCatalogInstance().getAirports().get(2))); //Registering an aircraft in Ottawa Airport
        aircrafts.add(new Aircraft("Airbus A330",AirportCatalog.getAirportCatalogInstance().getAirports().get(2))); //Registering an aircraft in Ottawa Airport
        aircrafts.add(new Aircraft("Cessna 01",AirportCatalog.getAirportCatalogInstance().getAirports().get(3))); //Registering an aircraft in Austin Airport
        aircrafts.add(new Aircraft("Cessna 01",AirportCatalog.getAirportCatalogInstance().getAirports().get(3))); //Registering an aircraft in Austin Airport
    }
    public static AircraftCatalog getAircraftCatalogInstance(){
        if(aircraftCatalog==null){
            aircraftCatalog = new AircraftCatalog();
        }
        return aircraftCatalog;
    }

    //Adds an aircraft to the database
    public void addAircraft(Aircraft aircraft) {
        aircrafts.add(aircraft);
    }


    //Returns the existing aircraft in the database
    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }









}
