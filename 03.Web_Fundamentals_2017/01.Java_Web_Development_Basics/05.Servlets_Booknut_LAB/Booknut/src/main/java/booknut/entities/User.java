package booknut.entities;

public class User {

    private long id;

    private String name;

    private String password;

    public User() {
    }

    public User(String name,String password) {
        this.name = name;
        this.password = password;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
