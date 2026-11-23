package validators;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;
@Documented
@Constraint(validatedBy = StationValidator.class)
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidStation {
    String message() default "Invalid Station entered.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

