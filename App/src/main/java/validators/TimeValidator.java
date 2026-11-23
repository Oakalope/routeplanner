package validators;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class TimeValidator implements ConstraintValidator<ValidTime, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        String regex = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$";
        System.out.println(value.matches(regex));
        return value.matches(regex);
    }
}
