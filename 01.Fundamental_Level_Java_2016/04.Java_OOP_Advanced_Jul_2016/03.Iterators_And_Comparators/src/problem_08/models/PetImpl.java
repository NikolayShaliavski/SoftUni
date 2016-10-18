package problem_08.models;

import problem_08.contracts.Pet;

public class PetImpl implements Pet {

    private String name;
    private int age;
    private String kind;

    public PetImpl(String nameArg,
                   int ageArg,
                   String kindArg) {
        this.setName(nameArg);
        this.setAge(ageArg);
        this.setKind(kindArg);
    }


    @Override
    public String getName() {
        return this.name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public int getAge() {
        return this.age;
    }
    private void setAge(int age) {
        this.age = age;
    }

    @Override
    public String getKind() {
        return this.kind;
    }

    private void setKind(String kind) {
        this.kind = kind;
    }
}
