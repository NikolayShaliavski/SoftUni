package users.database.problem.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Entity(name = "users")
public class User {

    //patterns aren't correct
    private static final String EMAIL_PATTERN =
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    private static final String PASSWORD_PATTERN =
            "^(?=[A-Za-z0-9@#$%^&+=]*[0-9])(?=[A-Za-z0-9@#$%^&+=]*[a-z])(?=[A-Za-z0-9@#$%^&+=]*[A-Z])(?=[A-Za-z0-9@#$%^&+=]*[@#$%^&+=]).{5,60}$";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long id;

    private String userName;

    private String password;

    private String email;

    @Column(columnDefinition = "LONGBLOB")
    private byte[] profilePicture;

    private Date registerOn;

    private Date lastTimeLoggedIn;

    private Integer age;

    private Boolean isDeleted;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "town_id", referencedColumnName = "id")
    private Town town;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if (userName.length() < 4 || userName.length() > 30) {
            throw new IllegalArgumentException("Invalid username.");
        }
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (!this.validatePattern(password, PASSWORD_PATTERN)) {
            throw new IllegalArgumentException("Invalid password.");
        }
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!this.validatePattern(email, EMAIL_PATTERN)) {
            throw new IllegalArgumentException("Invalid email.");
        }
        this.email = email;
    }

    public byte[] getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(byte[] profilePicture) {
        if (profilePicture.length > 1024 * 1024) {
            throw new IllegalArgumentException("Profile picture too big.");
        }
        this.profilePicture = profilePicture;
    }

    public Date getRegisterOn() {
        return registerOn;
    }

    public void setRegisterOn(Date registerOn) {
        this.registerOn = registerOn;
    }

    public Date getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public void setLastTimeLoggedIn(Date lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        if (age < 1 || age > 120) {
            throw new IllegalArgumentException("Invalid age.");
        }
        this.age = age;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Town getTown() {
        return town;
    }

    public void setTown(Town town) {
        this.town = town;
    }

    private boolean validatePattern(String item, String pattern) {
        Pattern patternValidator = Pattern.compile(pattern);
        Matcher matcher = patternValidator.matcher(item);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
}
