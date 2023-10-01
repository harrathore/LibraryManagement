package users;

public class User {
    private String userName;
    private String userId;

    public User(String userName) {
        this.userName = userName;
        this.userId = "user.id." + userName + "." + String.valueOf(Math.random());
    }

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }
}
