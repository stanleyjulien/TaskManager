package model;

public class User
{
    private String userId;
    private String password;
    private String fullName;

    public User(String userId, String password, String fullName) {
        this.userId = userId;
        this.password = password;
        this.fullName = fullName;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }
}
