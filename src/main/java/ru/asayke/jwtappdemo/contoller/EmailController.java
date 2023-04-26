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
import ru.asayke.jwtappdemo.models.ResetPasswordCode;
import ru.asayke.jwtappdemo.models.User;
import ru.asayke.jwtappdemo.service.EmailSenderService;
import ru.asayke.jwtappdemo.service.ResetPasswordCodeService;
import ru.asayke.jwtappdemo.service.UserService;

import java.security.Principal;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/email/")
@AllArgsConstructor
public class EmailController {
    private final EmailSenderService emailSenderService;
    private final UserService userService;
    private final ResetPasswordCodeService passwordCodeService;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/send-reset-code")
    public ResponseEntity<HttpStatus> sendResetPasswordCode(Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);

        if(passwordCodeService.existByUser(user))
            passwordCodeService.deleteByUser(user);

        int resetPasswordCode = ThreadLocalRandom.current().nextInt(100000, 1000000);

        final String subject = "Код для смены пароля";
        final String message = "Ваш код для смены пароля : " + resetPasswordCode;

        ResetPasswordCode passwordCode = new ResetPasswordCode();
        passwordCode.setUser(user);
        passwordCode.setResetCode(resetPasswordCode);

        passwordCodeService.save(passwordCode);
        emailSenderService.sendEmail(user.getEmail(), subject, message);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/confirm-reset-code")
    public ResponseEntity<HttpStatus> confirmCode(@RequestBody UpdatePasswordDTO passwordDTO, Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);
        int resetCode = passwordCodeService.findByUser(user).get().getResetCode();

        if(resetCode == passwordDTO.getResetCode()) {
            user.setPassword(passwordEncoder.encode(passwordDTO.getNewPassword()));
            passwordCodeService.deleteByUser(user);
            return ResponseEntity.ok(HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.CONFLICT);
    }
}