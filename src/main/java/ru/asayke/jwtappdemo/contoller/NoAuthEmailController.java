package ru.asayke.jwtappdemo.contoller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.asayke.jwtappdemo.dto.updateDTO.UpdatePasswordDTO;
import ru.asayke.jwtappdemo.dto.updateDTO.UserEmailDTO;
import ru.asayke.jwtappdemo.models.ResetPasswordCode;
import ru.asayke.jwtappdemo.models.User;
import ru.asayke.jwtappdemo.service.EmailSenderService;
import ru.asayke.jwtappdemo.service.ResetPasswordCodeService;
import ru.asayke.jwtappdemo.service.UserService;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/non-auth-email/")
@AllArgsConstructor
public class NoAuthEmailController {
    private final EmailSenderService emailSenderService;
    private final UserService userService;
    private final ResetPasswordCodeService passwordCodeService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/send-non-auth-code")
    public ResponseEntity<HttpStatus> sendNotAuthResetPasswordCode(@RequestBody UserEmailDTO emailDTO) {
        if(!userService.existsByEmail(emailDTO.getEmail()))
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        User user = userService.findByEmail(emailDTO.getEmail());

        if(passwordCodeService.existByUser(user))
            passwordCodeService.deleteByUser(user);

        int resetPasswordCode = ThreadLocalRandom.current().nextInt(100000, 1000000);

        final String subject = "Восстановление доступа";
        final String message = String.format("Здравствуйте, %s! Ваш код для смены пароля : %s", user.getUsername(), resetPasswordCode);

        ResetPasswordCode passwordCode = new ResetPasswordCode();
        passwordCode.setUser(user);
        passwordCode.setResetCode(resetPasswordCode);

        passwordCodeService.save(passwordCode);
        emailSenderService.sendEmail(emailDTO.getEmail(), subject, message);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/confirm-non-auth-reset-code")
    public ResponseEntity<HttpStatus> confirmCode(@RequestBody UpdatePasswordDTO passwordDTO) {
        Optional<ResetPasswordCode> code_optional = passwordCodeService.findByResetCode(passwordDTO.getResetCode());

        if(code_optional.isEmpty())
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = code_optional.get().getUser();

        int resetCode = passwordCodeService.findByUser(user).get().getResetCode();

        if(resetCode == passwordDTO.getResetCode()) {
            user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
            passwordCodeService.deleteByUser(user);
            return ResponseEntity.ok(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}