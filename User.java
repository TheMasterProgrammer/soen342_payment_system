public class User {
    protected String id;
    protected String name;
    protected boolean isRegistered;

    public User() {
        this.isRegistered = false;
        this.id = null;
        this.name = null;
    }

    // Common methods for all users
    public boolean isRegistered() {
        return isRegistered;
    }
}
