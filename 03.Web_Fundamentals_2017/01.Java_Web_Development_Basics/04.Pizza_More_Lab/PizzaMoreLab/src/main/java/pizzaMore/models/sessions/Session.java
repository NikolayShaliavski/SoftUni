package pizzaMore.models.sessions;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "sessions")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<SessionData> sessionData;

    public Session() {
        this.setSessionData(new HashSet<>());
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<SessionData> getSessionData() {
        return this.sessionData;
    }

    public void setSessionData(Set<SessionData> sessionData) {
        this.sessionData = sessionData;
    }

    public void addData(String key, String value) {
        this.sessionData.add(new SessionData(key, value, this));
    }
}
