import java.util.Scanner;

public class User {
    protected String id;
    protected String username;
    protected boolean isRegistered;
    protected String password;
    protected static int counter = 0;

    public User() {
        this.isRegistered = false;
        this.id = String.valueOf(counter++);
        this.username = null;
        this.password = null;
    }

    // Common methods for all users
    public boolean isRegistered() {
        return isRegistered;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword() {
        return password;
    }



    //Check if the user correctly entered their credentials
    private static boolean authenticateUser(String username, String password, UserCatalog userCatalog) {
        // Check if the username exists and the associated password matches
        for(User user : userCatalog.getUsers()){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    //Check if a new user that wants to create an account entered a unique username
    private static boolean isUsernameRegistered(String username, UserCatalog userCatalog){
        for(User user : userCatalog.getUsers()){
            if(user.getUsername().equals(username)){
                return true;
            }
        }
        return false;
    }

    //Returns a user in the database based on its username
    private static User findUser(String username, UserCatalog userCatalog){
        for(User user : userCatalog.getUsers()){
            if(user.getUsername().equals(username)){
                return user;
            }
        }
        return null;
    }


    //Allows a user to log in, whether they be registered or unregistered
    public static User login(Scanner scanner, UserCatalog userCatalog){
        int correspondingNumber;
        while(true){
            System.out.println("Access the application by writing the number corresponding to the type of user you wish to be");
            System.out.println("1. Unregistered user");
            System.out.println("2. Registered user");
            if (scanner.hasNextInt()) {
                correspondingNumber = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                if(correspondingNumber>=1 && correspondingNumber<=2){
                    if(correspondingNumber==1) {
                        return new User();
                    }else {
                        //Ensures that the user enters an existing username and its corresponding password
                        while(true){
                            String username= null;
                            //Ensures that the user enters an existing username
                            while(true) {
                                System.out.println("Enter your username:");
                                username = scanner.nextLine();
                                //checks if the username exists in the database
                                if (User.isUsernameRegistered(username,userCatalog)) {
                                    break;
                                } else {
                                    System.out.println("This username does not exist, enter an existing username");
                                }
                            }
                            System.out.println("Enter your password:");
                            String password = scanner.nextLine();
                            //checks if the user entered the correct password to his account.
                            //If yes, the user logs into their account
                            if(User.authenticateUser(username,password, userCatalog)){
                                System.out.println("You have been authenticated");
                                User user = User.findUser(username,userCatalog);
                                return user;
                            }else{
                                System.out.println("You entered the wrong password");
                            }
                        }
                    }
                }else{
                    System.out.println("Write a number between 1 and 2");
                }
            }else{
                System.out.println("Write an integer");
                scanner.nextLine(); // Consume the invalid input
            }
        }
    }

    //Allows a person to create an account
    public static void createUser(Scanner scanner,UserCatalog userCatalog, AirlineCatalog airlineCatalog, AirportCatalog airportCatalog){
        int correspondingNumber = 0;
        System.out.println("Create an account by writing the number corresponding to the type of user you wish to be");
        System.out.println("1. System Administrator");
        System.out.println("2. Airline Administrator");
        System.out.println("3. Airport Administrator");
        System.out.println("4. Normal User");
        if(scanner.hasNextInt()){
            correspondingNumber = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            if(correspondingNumber>=1 && correspondingNumber<=4){
                while(true){
                    System.out.println("Enter a new username");
                    String username = scanner.nextLine();
                    //check if the username is unique
                    if(!(User.isUsernameRegistered(username,userCatalog))){
                        System.out.println("Enter a new password");
                        String password = scanner.nextLine();
                        boolean created = false;
                        while(!created){
                            switch(correspondingNumber){
                                case 1:
                                    userCatalog.addUser(new SystemAdministrator(username, password));
                                    created = true;
                                    break;
                                case 2:
                                    System.out.println("Enter the name of the airline company you are working for");
                                    String airlineName = scanner.nextLine();
                                    //make sure the user enters an existing airline in the database, if yes, we create an airline administrator
                                    for(Airline airline : airlineCatalog.getAirlines()){
                                        if(airlineName.equals(airline.getAirlineName())){
                                            userCatalog.addUser(new AirlineAdministrator(username, password, airline));
                                            created = true;
                                            break;
                                        }
                                    }
                                    if(!created){
                                        System.out.println("the name of the airline you entered does not exist");
                                    }
                                    break;
                                case 3:
                                    System.out.println("Enter the code of the airport you are working in");
                                    String airportCode = scanner.next();
                                    scanner.nextLine(); // consume newline character
                                    //make sure the user enters an existing airport in the database, if yes, we create an airport administrator
                                    for(Airport airport : airportCatalog.getAirports()){
                                        if(airport.getAirportCode().equals(airportCode)){
                                            userCatalog.addUser(new AirportAdministrator(username, password, airport));
                                            created = true;
                                            break;
                                        }
                                    }
                                    if(!created){
                                        System.out.println("the code of the airport you entered does not exist");
                                    }
                                    break;
                                case 4:
                                    userCatalog.addUser(new NormalUser(username, password));
                                    created = true;
                                    break;
                            }
                        }
                        System.out.println("User has been created and added to the database");
                        break;
                    }else{
                        System.out.println("this username already exists, enter a new one");
                    }
                }
            }else{
                System.out.println("Enter an integer between 1 and 4");
            }
        }else{
            System.out.println("Please enter an integer");
            scanner.nextLine(); // Consume the invalid input
        }
    }

}
