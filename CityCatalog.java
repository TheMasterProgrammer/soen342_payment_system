import java.util.ArrayList;
import java.util.List;

public class CityCatalog {
    private static CityCatalog cityCatalog = null;
    private List<City> cities;
    private CityCatalog(){
        cities = new ArrayList<>();
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
