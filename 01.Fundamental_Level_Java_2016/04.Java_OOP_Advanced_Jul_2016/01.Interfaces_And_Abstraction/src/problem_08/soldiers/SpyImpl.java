package problem_08.soldiers;

import problem_08.interfaces.Spy;

public class SpyImpl extends SoldierImpl implements Spy {

    private String codeNumber;

    public SpyImpl(String firstName,
            String lastName,
            String id,
            String codeNumber) {
        super(firstName, lastName, id);
        this.setCodeNumber(codeNumber);
    }

    @Override
    public String getCodeNumber() {
        return this.codeNumber;
    }

    private void setCodeNumber(String codeNumber) {
        this.codeNumber = codeNumber;
    }

    @Override
    public String toString() {
        return String.format("%nCode Number: %s%n", super.toString(), this.codeNumber);
    }
}
