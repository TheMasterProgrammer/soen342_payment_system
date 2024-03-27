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
