package Contexts;

class User {
    String username;
    String role;

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }
}

public class AuthContext {
    static User user = null;

    public static void setUser(String username, String role) {
        user = new User(username, role);
    }

    public static void removeUser() {
        user = null;
    }

    public static String getUsername() {
        if(user != null) return user.username;
        return null;
    }

    public static String getRole() {
        if(user != null) return user.role;
        return null;
    }
}
