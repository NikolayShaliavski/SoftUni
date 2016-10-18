package pr04_WorkForce;

import pr04_WorkForce.contracts.Employee;
import pr04_WorkForce.contracts.JobObservable;
import pr04_WorkForce.models.Job;
import pr04_WorkForce.models.JobsBg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws IOException, ReflectiveOperationException {

        String employeesPackage = "pr04_WorkForce.models.employees.";
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Employee> employeesByName = new HashMap<>();
        JobsBg jobsBg = new JobsBg();

        String command = reader.readLine();
        while (!command.equals("End")) {

            String[] params = command.split(" ");
            switch (params[0]) {
                case "Job":
                    String jobName = params[1];
                    int hours = Integer.valueOf(params[2]);
                    Employee employee = employeesByName.get(params[3]);
                    JobObservable job = new Job(jobName, hours, employee);
                    job.setListener(jobsBg);
                    jobsBg.add(job);
                    break;
                case "Pass":
                    JobObservable[] jobs = new JobObservable[jobsBg.size()];
                    jobsBg.toArray(jobs);
                    for (JobObservable jobObservable : jobs) {
                        String resultFromUpdate = jobObservable.update();
                        if (resultFromUpdate.length() > 0) {
                            System.out.println(resultFromUpdate);
                        }
                    }
                    break;
                case "Status":
                    for (JobObservable jobObservable : jobsBg) {
                        System.out.println(jobObservable.reportStatus());
                    }
                    break;
                default:
                    Class employeeClass = Class.forName(employeesPackage + params[0]);
                    Constructor employeeCtor = employeeClass.getConstructor(String.class);
                    Employee newEmployee = (Employee) employeeCtor.newInstance(params[1]);
                    employeesByName.put(params[1], newEmployee);
                    break;
            }

            command = reader.readLine();
        }

    }
}
