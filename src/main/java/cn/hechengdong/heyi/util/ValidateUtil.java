package cn.hechengdong.heyi.util;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.stream.Collectors;

public class ValidateUtil {

    private static final Validator validator;

    private ValidateUtil() {
    }

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public static <T> String validate(T t) {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (CollectionUtils.isEmpty(constraintViolations)) {
            return null;
        }
        return StringUtils.join(
                constraintViolations.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList()),
                ",");
    }
}
