package ru.asayke.jwtappdemo.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorsUtil {
    public static void returnErrorsToClient(BindingResult bindingResult){
        StringBuilder errorsMessage = new StringBuilder();

        List<FieldError> errorList = bindingResult.getFieldErrors();

        for (FieldError error : errorList) {
            errorsMessage.append(error.getField())
                    .append(" - ")
                    .append(error.getDefaultMessage() == null ? error.getCode() : error.getDefaultMessage())
                    .append(";");
        }

        throw new UserException(errorsMessage.toString());
    }
}