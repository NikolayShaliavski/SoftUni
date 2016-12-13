package photography.validator;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

@Component
public class CustomValidator {

    private ValidatorFactory validatorFactory;

    private Validator validator;

    public CustomValidator() {
        this.validatorFactory = Validation.buildDefaultValidatorFactory();
        this.validator = this.validatorFactory.getValidator();
    }

    public <T> boolean isValid(T object) {
        boolean isValid = true;
        Set<ConstraintViolation<T>> errors = this.validator.validate(object);
        if (errors.size() > 0) {
            isValid = false;
        }
        return isValid;
    }
}
