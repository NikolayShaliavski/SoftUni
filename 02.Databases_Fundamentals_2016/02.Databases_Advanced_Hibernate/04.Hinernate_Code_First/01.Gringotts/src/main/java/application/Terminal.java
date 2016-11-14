package application;

import application.entities.WizzardDeposit;
import application.services.WizzardDepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;


@Component
public class Terminal implements CommandLineRunner {

    private final WizzardDepositService wizzardDepositService;

    @Autowired
    public Terminal(WizzardDepositService wizzardDepositService) {
        this.wizzardDepositService = wizzardDepositService;
    }

    @Override
    public void run(String... strings) throws Exception {

        WizzardDeposit wz = new WizzardDeposit();
        wz.setFirstName("Nikolay");
        wz.setLastName("Dimitrov");
        wz.setAge(26);
        wz.setMagicWandCreator("Tatko");
        wz.setMagicWandSize(26);
        wz.setDepositAmount(0.0);
        wz.setDepositCharge(0.0);
        wz.setDepositExpirationDate(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.set(2010,11,21);
        wz.setDepositStartDate(calendar.getTime());
        wz.setDepositExpired(true);

        this.wizzardDepositService.persist(wz);
    }
}
