package pr07_1984.core;

import pr07_1984.contracts.*;
import pr07_1984.contracts.Runnable;
import pr07_1984.models.Company;
import pr07_1984.models.Employee;
import pr07_1984.models.Institution;

import java.io.IOException;

public class Engine implements Runnable {

    private Data dataRepository;
    private Reader reader;
    private Writer writer;

    public Engine(Data dataRepository,
                  Reader reader,
                  Writer writer) {
        this.dataRepository = dataRepository;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void run() throws IOException, IllegalAccessException {
        String[] params = this.reader.read().split(" ");
        int numberOfSubjects = Integer.valueOf(params[0]);
        int numberOfInstitutions = Integer.valueOf(params[1]);
        int numberOfChanges = Integer.valueOf(params[2]);

        for (int i = 0; i < numberOfSubjects; i++) {
            String subjectLine = this.reader.read();
            this.parseSubjectInput(subjectLine);
        }

        for (int i = 0; i < numberOfInstitutions; i++) {
            String institutionLine = this.reader.read();
            this.parseInstitutionInput(institutionLine);
        }

        this.dataRepository.addInstitutionToSubjects();

        for (int i = 0; i < numberOfChanges; i++) {
            String changeLine = reader.read();
            this.parseChanges(changeLine);
        }

        this.writer.write(this.dataRepository.reportInfo());
    }

    private void parseChanges(String changeLine) throws IllegalAccessException {
        String[] changeParams = changeLine.split(" ");

        String id = changeParams[0];
        String fieldName = changeParams[1];
        String newValue = changeParams[2];

        this.dataRepository.createChangeEvent(id, fieldName, newValue);
    }

    private void parseSubjectInput(String subjectLine) {
        String[] subjectParams = subjectLine.split(" ");

        String subjectType = subjectParams[0];
        String id = subjectParams[1];
        String name = subjectParams[2];

        Observable subject = null;
        if (subjectType.equals("Employee")) {
            int income = Integer.valueOf(subjectParams[3]);
            subject = new Employee(id, name, income);
        } else if (subjectType.equals("Company")) {
            int turnover = Integer.valueOf(subjectParams[3]);
            int revenue = Integer.valueOf(subjectParams[4]);
            subject = new Company(id, name, turnover, revenue);
        }
        this.dataRepository.addSubject(subject);
    }

    private void parseInstitutionInput(String institutionLine) {
        String[] institutionParams = institutionLine.split(" ");
        String[] monitoredFields = new String[institutionParams.length - 3];

        String id = institutionParams[1];
        String name = institutionParams[2];

        for (int i = 0; i < monitoredFields.length; i++) {
            monitoredFields[i] = institutionParams[i + 3];
        }
        Observer institution = new Institution(id, name, monitoredFields);
        this.dataRepository.addInstitution(institution);
    }
}
