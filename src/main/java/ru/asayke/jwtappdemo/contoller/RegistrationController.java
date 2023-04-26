package ru.asayke.jwtappdemo.contoller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.asayke.jwtappdemo.dto.RegistrationRequestDTO;
import ru.asayke.jwtappdemo.models.User;
import ru.asayke.jwtappdemo.service.AlarmClockService;
import ru.asayke.jwtappdemo.service.UserService;
import ru.asayke.jwtappdemo.util.ErrorsUtil;
import ru.asayke.jwtappdemo.util.UserException;
import ru.asayke.jwtappdemo.util.UserExceptionResponse;
import ru.asayke.jwtappdemo.util.UserValidator;

@RestController
@RequestMapping(value = "/api/registration/")
@AllArgsConstructor
public class RegistrationController {
    private final UserService userService;
    private final ModelMapper mapper;
    private final UserValidator userValidator;
    private final AlarmClockService clockService;

    @PostMapping
    public ResponseEntity<HttpStatus> register(@RequestBody RegistrationRequestDTO request, BindingResult bindingResult) {
        User userToAdd = convertToUser(request);
        userValidator.validate(userToAdd, bindingResult);

        if(bindingResult.hasErrors())
            ErrorsUtil.returnErrorsToClient(bindingResult);

        userService.register(userToAdd);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @ExceptionHandler
    private ResponseEntity<UserExceptionResponse> handleException(UserException e){
        UserExceptionResponse response = new UserExceptionResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private User convertToUser(RegistrationRequestDTO regDTO) {
        return mapper.map(regDTO, User.class);
    }
}