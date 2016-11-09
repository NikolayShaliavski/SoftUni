package entities;

import javax.persistence.*;

@Entity
@Table(name = "towns")
public class Town {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "town_id")
    private int townId;

    @Basic
    private String name;

    public Town() {
    }

    public Town(String name) {
        this.name = name;
    }

    public int getTownId() {
        return townId;
    }

    public void setTownId(int id) {
        this.townId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
