package pr07_1984.models;

import pr07_1984.contracts.Event;
import pr07_1984.contracts.Observer;

import java.util.LinkedList;
import java.util.List;

public class Institution implements Observer {

    private String id;
    private String name;
    private int registeredChanges;
    private String[] monitoredChanges;
    private List<Event> events;

    public Institution(String id,
                       String name,
                       String[] monitoredChanges) {
        this.id = id;
        this.name = name;
        this.monitoredChanges = monitoredChanges;
        this.registeredChanges = 0;
        this.events = new LinkedList<>();
    }

    @Override
    public void handleChangeEvent(Event changeEvent) {
        for (String monitoredChange : this.monitoredChanges) {
            String test = monitoredChange;
            String fieldStrName = changeEvent.getFieldName();
            if (test.equals(fieldStrName)) {
                this.events.add(changeEvent);
                this.registeredChanges++;
                break;
            }
        }
    }

    @Override
    public String reportSavedChanges() throws IllegalAccessException {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format(
                "%s: %d changes registered", this.name, this.registeredChanges)).
                append(System.lineSeparator());
        for (Event event : this.events) {
            builder.append(event.getMessage()).
                    append(System.lineSeparator());
        }
        return builder.toString().trim();
    }
}
