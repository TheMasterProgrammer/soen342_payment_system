public class SystemAdministrator extends User {
    public SystemAdministrator(String username, String password) {
        this.id = String.valueOf(counter++);
        this.username = username;
        this.password = password;
        this.isRegistered = true;
    }



}
