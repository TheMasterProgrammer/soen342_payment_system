import java.util.ArrayList;
import java.util.List;

public class Airline {
    private String AirlineName;

    private List<Aircraft> fleet;

    //we need to add at least 2 aircrafts in order for a company to be considered an airline
    public Airline(String AirlineName, Aircraft aircraft1, Aircraft aircraft2){
        this.AirlineName = AirlineName;
        fleet = new ArrayList<Aircraft>();
        addAircraft(aircraft1);
        addAircraft(aircraft2);
    }

    public void addAircraft(Aircraft aircraft){
        fleet.add(aircraft);
    }
}
