package photography.validator;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PhoneValidator {

    private static final String REGEX = "^(\\+[0-9]{1,3}\\/[0-9]{8,10})$";

    private Pattern pattern;

    private Matcher matcher;

    public PhoneValidator() {
        this.pattern = Pattern.compile(REGEX);
    }

    public Boolean isValid(String phone) {
        this.matcher = this.pattern.matcher(phone);
        if (matcher.find()) {
            return true;
        }
        return false;
    }
}
