import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCatalog {

    private static UserCatalog userCatalog = null;

    private List<User> users;
    private UserCatalog() {
        users = new ArrayList<>();
        //Some hardcoded data


        //Registering a system admin
        users.add(new SystemAdministrator("SystemEmployee","123"));
        //Registering an airline admin for Air Canada
        users.add(new AirlineAdministrator("AirCanadaEmployee","123",AirlineCatalog.getAirlineCatalogInstance().getAirlines().get(0)));
        //Registering an airline admin for Fedex
        users.add(new AirlineAdministrator("FedexEmployee","123",AirlineCatalog.getAirlineCatalogInstance().getAirlines().get(1)));
        //Registering an airport admin for Montreal
        users.add(new AirportAdministrator("MontrealAdmin","123", AirportCatalog.getAirportCatalogInstance().getAirports().get(0)));
        //Registering an airport admin for Toronto
        users.add(new AirportAdministrator("TorontoAdmin","123", AirportCatalog.getAirportCatalogInstance().getAirports().get(1)));
        //Registering an airport admin for Ottawa
        users.add(new AirportAdministrator("OttawaAdmin","123", AirportCatalog.getAirportCatalogInstance().getAirports().get(2)));
        //Registering an airport admin for Austin
        users.add(new AirportAdministrator("AustinAdmin","123", AirportCatalog.getAirportCatalogInstance().getAirports().get(3)));
        //Registering a normal user
        users.add(new NormalUser("NormalUser","normal"));
    }
    public static UserCatalog getCredentialsCatalogInstance(){
        if(userCatalog==null){
            userCatalog = new UserCatalog();
        }
        return userCatalog;
    }

    //Adds a user to the database
    public void addUser(User user) {
        users.add(user);
    }

    //Returns the existing users in the database
    public List<User> getUsers(){
        return users;
    }


}

