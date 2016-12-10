package com.validation.terminal;

import com.validation.testEntities.User;
import com.validation.validator.CustomValidator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Terminal implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {

        User user = new User();
        user.setEmail("five");
        user.setPassword("L13");

        CustomValidator customValidator = new CustomValidator();

        boolean isValid = customValidator.isValid(user);

        System.out.println(isValid);
    }
}
