package users.database.problem.terminal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import users.database.problem.entities.Town;
import users.database.problem.entities.User;
import users.database.problem.services.TownService;
import users.database.problem.services.UserService;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class Terminal implements CommandLineRunner {

    @Autowired
    private UserService userService;

    @Autowired
    private TownService townService;

    @Override
    public void run(String... strings) throws Exception {
        Town town = new Town();
        town.setName("Sofia");
        town.setCountry("Bulgaria");

        Town townTwo = new Town();
        townTwo.setName("Moscow");
        townTwo.setCountry("Russia");

        User gosho = new User();
        gosho.setUserName("Gosho");
        gosho.setEmail("gosho@abv.bg");
        gosho.setPassword("Aa#242dsa34");
        gosho.setRegisterOn(new GregorianCalendar(2015, Calendar.JANUARY, 20).getTime());
        gosho.setLastTimeLoggedIn(new  GregorianCalendar(2014, Calendar.JUNE, 15).getTime());
        gosho.setIsDeleted(false);
        gosho.setAge(22);

        User pesho = new User();
        pesho.setUserName("Pesho");
        pesho.setEmail("pesho@abv.bg");
        pesho.setPassword("Aa#242dsa34");
        pesho.setRegisterOn(new GregorianCalendar(2015, Calendar.JANUARY, 20).getTime());
        pesho.setLastTimeLoggedIn(new  GregorianCalendar(2014, Calendar.JUNE, 15).getTime());
        pesho.setIsDeleted(true);
        pesho.setAge(25);

        gosho.setTown(town);
        town.setUser(gosho);

        pesho.setTown(townTwo);
        townTwo.setUser(pesho);

        this.userService.registerUser(gosho);
        this.userService.registerUser(pesho);

        /*
        We can register only users or towns, because of using
        CascadeType.ALL in User && Town classes. It isn't necessary
        to register both objects.
         */
        //this.townService.registerTown(town);
        //this.townService.registerTown(townTwo);
    }
}
