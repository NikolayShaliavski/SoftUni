package problem_02;

/**
 * Created by Nikolay Shalyavski on 28.7.2016 г..
 */
public class Person {
    
    private String name;
    private Long ID;
    
    public Person(String name,
                  Long ID) {
        this.setName(name);
        this.setID(ID);
    }

    private String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private Long getID() {
        return this.ID;
    }

    private void setID(Long ID) {
        this.ID = ID;
    }
}
