public class NormalUser extends User {
    public NormalUser(String id, String name) {
        this.id = id;
        this.name = name;
        this.isRegistered = true;
    }

    // Methods specific to normal users
}
