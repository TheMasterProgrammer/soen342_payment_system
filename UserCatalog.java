import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserCatalog {

    private static UserCatalog userCatalog = null;

    private List<User> users;
    private UserCatalog() {
        users = new ArrayList<>();
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

