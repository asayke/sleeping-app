package ru.asayke.jwtappdemo.contoller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.asayke.jwtappdemo.dto.SleepDTO;
import ru.asayke.jwtappdemo.models.Sleep;
import ru.asayke.jwtappdemo.service.SleepService;
import ru.asayke.jwtappdemo.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sleep/")
@AllArgsConstructor
public class SleepController {
    private final SleepService sleepService;
    private final UserService userService;
    private final ModelMapper mapper;

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> addNewSleep(@RequestBody SleepDTO sleepDTO, Principal principal) {
        String userName = principal.getName();

        if(userName == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        sleepService.addSleep(convertToSleep(sleepDTO), userName);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<SleepDTO>> findAllByUser(Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(sleepService.findAllByUser(userService.findByUsername(username)).stream().map(this::convertToSleepDTO).collect(Collectors.toList()));
    }

    @GetMapping("/last")
    public ResponseEntity<SleepDTO> findLastUserSleep(Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        return ResponseEntity.ok(convertToSleepDTO(sleepService.findLastUserSleep(userService.findByUsername(username))));
    }

    private SleepDTO convertToSleepDTO(Sleep sleep) {
        return mapper.map(sleep, SleepDTO.class);
    }

    private Sleep convertToSleep(SleepDTO sleepDTO) {
        return mapper.map(sleepDTO, Sleep.class);
    }
}