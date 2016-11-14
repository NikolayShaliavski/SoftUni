package app;

import app.entities.*;
import app.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private VisitationService visitationService;

    @Autowired
    private DiagnoseService diagnoseService;

    @Autowired
    private MedicamentService medicamentService;

    @Override
    public void run(String... strings) throws Exception {

        //get picture bytes, it will be use for all patients
        byte[] pictureBytes = this.getPictureInBytes();

        //patient objects
        Patient patientOne = new Patient();
        patientOne.setFirstName("John");
        patientOne.setLastName("Steward");
        patientOne.setAddress("Hill Street");
        patientOne.setDateOfBirth(new Date());
        patientOne.setEmail("john_steward@gmail.com");
        patientOne.setHasInsurance(true);
        patientOne.setPicture(pictureBytes);

        Patient patientTwo = new Patient();
        patientTwo.setFirstName("Alex");
        patientTwo.setLastName("Novak");
        patientTwo.setAddress("Road Avenue");
        patientTwo.setDateOfBirth(new Date());
        patientTwo.setEmail("alex_novak@gmail.com");
        patientTwo.setHasInsurance(false);
        patientTwo.setPicture(pictureBytes);

        //doctors objects
        Doctor firstDoctor = new Doctor();
        firstDoctor.setName("Ivan Ivanov");
        firstDoctor.setSpeciality("Professor");

        Doctor secondDoctor = new Doctor();
        secondDoctor.setName("Pesho Petrov");
        secondDoctor.setSpeciality("Docent");

        Doctor thirdDoctor = new Doctor();
        thirdDoctor.setName("Andrei Andreev");
        thirdDoctor.setSpeciality("Professor");

        //visitation objects
        Visitation visitationOne = new Visitation();
        visitationOne.setVisitationDate(new Date());
        visitationOne.setComments("John is ill.");

        Visitation visiationTwo = new Visitation();
        visiationTwo.setVisitationDate(new Date());
        visiationTwo.setComments("John is already fine.");

        Visitation visitationThree = new Visitation();
        visitationThree.setVisitationDate(new Date());
        visitationThree.setComments("Alex is ill.");

        Visitation visitationFour = new Visitation();
        visitationFour.setVisitationDate(new Date());
        visitationFour.setComments("Alex is already fine.");

        //add patients to visitations
        visitationOne.setPatient(patientOne);
        visiationTwo.setPatient(patientOne);
        visitationThree.setPatient(patientTwo);
        visitationFour.setPatient(patientTwo);

        //add doctors to visitations
        visitationOne.setDoctor(firstDoctor);
        visiationTwo.setDoctor(secondDoctor);
        visitationThree.setDoctor(secondDoctor);
        visitationFour.setDoctor(thirdDoctor);


        //diagnose objects
        Diagnose firstDiagnose = new Diagnose();
        firstDiagnose.setName("Headache");
        firstDiagnose.setComments("Need a rest.");

        Diagnose secondDiagnose = new Diagnose();
        secondDiagnose.setName("Broken arm");
        secondDiagnose.setComments("Stay in hospital.");

        Diagnose thirdDiagnose = new Diagnose();
        thirdDiagnose.setName("Toothache");
        thirdDiagnose.setComments("Go to dentist");

        //add diagnoses to patients
        patientOne.addDiagnose(firstDiagnose);
        patientOne.addDiagnose(secondDiagnose);
        patientOne.addDiagnose(thirdDiagnose);

        patientTwo.addDiagnose(firstDiagnose);
        patientTwo.addDiagnose(thirdDiagnose);

        //add patients to diagnoses
        firstDiagnose.addPatient(patientOne);
        secondDiagnose.addPatient(patientOne);
        thirdDiagnose.addPatient(patientOne);

        firstDiagnose.addPatient(patientTwo);
        thirdDiagnose.addPatient(patientTwo);

        //medicament objects
        Medicament medicamentOne = new Medicament();
        medicamentOne.setName("Paracetamol");
        Medicament medicamentTwo = new Medicament();
        medicamentTwo.setName("Aspirin");
        Medicament medicamentThree = new Medicament();
        medicamentThree.setName("Analgin");

        //add medicaments to patients
        patientOne.addMedicament(medicamentOne);
        patientOne.addMedicament(medicamentTwo);

        patientTwo.addMedicament(medicamentOne);
        patientTwo.addMedicament(medicamentTwo);
        patientTwo.addMedicament(medicamentThree);

        //add patients to medicaments
        medicamentOne.addPatient(patientOne);
        medicamentOne.addPatient(patientTwo);

        medicamentTwo.addPatient(patientOne);
        medicamentTwo.addPatient(patientTwo);

        medicamentThree.addPatient(patientTwo);

        //persist objects in database
        this.diagnoseService.registerDiagnose(firstDiagnose);
        this.diagnoseService.registerDiagnose(secondDiagnose);
        this.diagnoseService.registerDiagnose(thirdDiagnose);

        this.medicamentService.registerMedicament(medicamentOne);
        this.medicamentService.registerMedicament(medicamentTwo);
        this.medicamentService.registerMedicament(medicamentThree);

        /*
        first register patients && doctors to avoid exception when register visitations,
        because in visitation table we have patient_id && doctor_id columns.
        When we save visitation we have to insert into these columns already saved in DB object.
         */
        this.patientService.registerPatient(patientOne);
        this.patientService.registerPatient(patientTwo);

        this.doctorService.registerDoctor(firstDoctor);
        this.doctorService.registerDoctor(secondDoctor);
        this.doctorService.registerDoctor(thirdDoctor);

        this.visitationService.registerVisitation(visitationOne);
        this.visitationService.registerVisitation(visiationTwo);
        this.visitationService.registerVisitation(visitationThree);
        this.visitationService.registerVisitation(visitationFour);

    }

    public byte[] getPictureInBytes() throws IOException {
        File picture = new File("resources/hospital.png");
        byte[] bytes = new byte[(int)picture.length()];
        FileInputStream fis = new FileInputStream(picture);
        fis.read(bytes);
        fis.close();
        return bytes;
    }
}
