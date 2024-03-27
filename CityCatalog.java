import java.util.ArrayList;
import java.util.List;

public class CityCatalog {
    private static CityCatalog cityCatalog = null;
    private List<City> cities;
    private CityCatalog(){
        cities = new ArrayList<>();
        //Some hardcoded data

        cities.add(new City("Montreal","Canada",1)); //Registering Montreal
        cities.add(new City("Toronto","Canada",13)); //Registering Toronto
        cities.add(new City("Ottawa","Canada",5)); //Registering Ottawa
        cities.add(new City("Austin","United States",25)); //Registering Austin
    }
    public static CityCatalog getCityCatalogInstance(){
        if(cityCatalog==null){
            cityCatalog = new CityCatalog();
        }
        return cityCatalog;
    }

    //Returns the existing cities in the database
    public List<City> getCities(){
        return cities;
    }

    //Adds a city to the database
    public void addCity(City city){
        cities.add(city);
    }




}
