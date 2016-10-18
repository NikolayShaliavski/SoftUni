package pr07_1984.core;

import pr07_1984.contracts.Data;
import pr07_1984.contracts.Event;
import pr07_1984.contracts.Observable;
import pr07_1984.contracts.Observer;
import pr07_1984.events.ChangeEvent;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataRepository implements Data {

    private Map<String, Observable> subjects;
    private List<Observer> institutions;

    public DataRepository() {
        this.subjects = new HashMap<>();
        this.institutions = new LinkedList<>();
    }

    @Override
    public void addInstitution(Observer observer) {
        this.institutions.add(observer);
    }

    @Override
    public void addInstitutionToSubjects() {
        for (Observable subject : subjects.values()) {
            subject.addObservers(this.institutions);
        }
    }

    @Override
    public void addSubject(Observable subject) {
        this.subjects.put(subject.getId(), subject);
    }

    @Override
    public void createChangeEvent(String id,
                                  String fieldName,
                                  String newValue) throws IllegalAccessException {
        Observable subject = this.subjects.get(id);
        Field[] subjectFields = subject.getClass().getDeclaredFields();
        Event changeEvent = null;

        for (Field subjectField : subjectFields) {
            subjectField.setAccessible(true);
            if (!subjectField.getName().equals(fieldName)) {
                continue;
            }
            String oldValue = String.valueOf(subjectField.get(subject));
            if (subjectField.getType().equals(String.class)) {
                subjectField.set(subject, newValue);
            } else if (subjectField.getType().equals(int.class)) {
                int intValue = Integer.valueOf(newValue);
                subjectField.set(subject, intValue);
            }
            String changedValue = String.valueOf(subjectField.get(subject));
            changeEvent = new ChangeEvent(subject, subjectField, oldValue, changedValue);
            break;
        }
        if (changeEvent != null) {
            subject.fireChangeEvent(changeEvent);
        }
    }

    @Override
    public String reportInfo() throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        for (Observer institution : this.institutions) {
            builder.append(institution.reportSavedChanges()).
                    append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
