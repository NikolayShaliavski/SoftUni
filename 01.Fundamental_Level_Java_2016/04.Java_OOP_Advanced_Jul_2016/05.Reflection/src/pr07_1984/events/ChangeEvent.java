package pr07_1984.events;

import pr07_1984.contracts.Event;
import pr07_1984.contracts.Observable;

import java.lang.reflect.Field;

public class ChangeEvent implements Event {

    private Observable subject;
    private Field changedField;
    private String oldValue;
    private String newValue;

    public ChangeEvent(Observable subject,
                       Field changedField,
                       String oldValue,
                       String newValue) {
        this.subject = subject;
        this.changedField = changedField;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    @Override
    public String getFieldName() {
        this.changedField.setAccessible(true);
        return this.changedField.getName();
    }

    @Override
    public String getMessage() throws IllegalAccessException {
        return String.format(
                "--%s(ID:%s) changed %s(%s) from %s to %s",
                this.subject.getClass().getSimpleName(),
                this.subject.getId(),
                this.changedField.getName(),
                this.changedField.getType().getSimpleName(),
                this.oldValue,
                this.newValue);
    }
}
