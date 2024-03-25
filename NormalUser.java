public class NormalUser extends User {
    public NormalUser(String username, String password) {
        this.id = String.valueOf(counter++);
        this.username = username;
        this.isRegistered = true;
        this.password = password;

    }

}
