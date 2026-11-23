package validators;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.HashMap;
import static services.CSVRead.readStationNamesAndCodes;
public class StationValidator implements ConstraintValidator<ValidStation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        HashMap<String, String> namesAndCodes = readStationNamesAndCodes();
        //check if existing station
        return namesAndCodes.containsKey(value);
    }
}