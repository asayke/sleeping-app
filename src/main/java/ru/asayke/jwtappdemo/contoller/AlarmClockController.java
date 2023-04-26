package ru.asayke.jwtappdemo.contoller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.asayke.jwtappdemo.dto.AlarmClockDTO;
import ru.asayke.jwtappdemo.dto.DefaultAlarmClock;
import ru.asayke.jwtappdemo.dto.updateDTO.UpdateBoolFieldDTO;
import ru.asayke.jwtappdemo.dto.updateDTO.UpdateDateFieldDTO;
import ru.asayke.jwtappdemo.dto.updateDTO.UpdateDoubleFieldDTO;
import ru.asayke.jwtappdemo.models.AlarmClock;
import ru.asayke.jwtappdemo.models.User;
import ru.asayke.jwtappdemo.service.AlarmClockService;
import ru.asayke.jwtappdemo.service.UserService;

import java.security.Principal;
import java.util.Optional;

@RestController
@RequestMapping(value = "/clock/")
@AllArgsConstructor
public class AlarmClockController {
    private final UserService userService;
    private final AlarmClockService clockService;
    private final ModelMapper mapper;

    @GetMapping("/get-clock")
    public ResponseEntity<AlarmClockDTO> getClockByUser(Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);

        if(!clockService.existsByUser(user)) {
            DefaultAlarmClock alarmClock = new DefaultAlarmClock();
            AlarmClock clock = convertToClockFromDefault(alarmClock);
            clock.setUser(user);
            clockService.save(clock);
        }

        Optional<AlarmClock> clock = clockService.findByUser(user);

        return clock.map(alarmClock -> ResponseEntity.ok(convertToClockDTO(alarmClock))).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
    }
    @PostMapping("/reset-clock")
    public ResponseEntity<HttpStatus> resetClock(@RequestBody AlarmClockDTO clockDTO, Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);
        Optional<AlarmClock> clock = clockService.findByUser(user);

        clockService.resetClock(clockDTO, clock.get());

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update-remind-to-charge")
    public ResponseEntity<HttpStatus> resetRemindToCharge(@RequestBody UpdateBoolFieldDTO remindDTO, Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);
        Optional<AlarmClock> clock = clockService.findByUser(user);

        if(clock.isPresent()) {
            AlarmClock presentedClock = clock.get();
            presentedClock.setRemindToCharge(remindDTO.getField());
            clockService.save(presentedClock);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update-bed-time")
    public ResponseEntity<HttpStatus> resetBedTime(@RequestBody UpdateDateFieldDTO dateDTO, Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);
        Optional<AlarmClock> clock = clockService.findByUser(user);

        if(clock.isPresent()) {
            AlarmClock presentedClock = clock.get();
            presentedClock.setBedtime(dateDTO.getField());
            clockService.save(presentedClock);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update-wake-up-time")
    public ResponseEntity<HttpStatus> resetWakeUpTime(@RequestBody UpdateDateFieldDTO dateDTO, Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);
        Optional<AlarmClock> clock = clockService.findByUser(user);

        if(clock.isPresent()) {
            AlarmClock presentedClock = clock.get();
            presentedClock.setWakeupTime(dateDTO.getField());
            clockService.save(presentedClock);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update-remind-to-sleep")
    public ResponseEntity<HttpStatus> resetRemindToSleep(@RequestBody UpdateBoolFieldDTO remindDTO, Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);
        Optional<AlarmClock> clock = clockService.findByUser(user);

        if(clock.isPresent()) {
            AlarmClock presentedClock = clock.get();
            presentedClock.setRemindToSleep(remindDTO.getField());
            clockService.save(presentedClock);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update-alarm-enabled")
    public ResponseEntity<HttpStatus> resetAlarmEnabled(@RequestBody UpdateBoolFieldDTO alarmEnabledDTO, Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);
        Optional<AlarmClock> clock = clockService.findByUser(user);

        if(clock.isPresent()) {
            AlarmClock presentedClock = clock.get();
            presentedClock.setAlarmEnabled(alarmEnabledDTO.getField());
            clockService.save(presentedClock);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update-vibration-enabled")
    public ResponseEntity<HttpStatus> resetVibrationEnabled(@RequestBody UpdateBoolFieldDTO vibrodDTO, Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);
        Optional<AlarmClock> clock = clockService.findByUser(user);

        if(clock.isPresent()) {
            AlarmClock presentedClock = clock.get();
            presentedClock.setVibrationEnabled(vibrodDTO.getField());
            clockService.save(presentedClock);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update-alarm-volume")
    public ResponseEntity<HttpStatus> resetAlarmVolume(@RequestBody UpdateDoubleFieldDTO volumeDTO, Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);
        Optional<AlarmClock> clock = clockService.findByUser(user);

        if(clock.isPresent()) {
            AlarmClock presentedClock = clock.get();
            presentedClock.setAlarmVolume(volumeDTO.getField());
            clockService.save(presentedClock);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update-snooze-time")
    public ResponseEntity<HttpStatus> resetSnoozeTime(@RequestBody UpdateDoubleFieldDTO volumeDTO, Principal principal) {
        String username = principal.getName();

        if(username == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        User user = userService.findByUsername(username);
        Optional<AlarmClock> clock = clockService.findByUser(user);

        if(clock.isPresent()) {
            AlarmClock presentedClock = clock.get();
            presentedClock.setSnoozeTime(volumeDTO.getField());
            clockService.save(presentedClock);
        }

        return ResponseEntity.ok(HttpStatus.OK);
    }

    private AlarmClockDTO convertToClockDTO(AlarmClock alarmClock) {
        return mapper.map(alarmClock, AlarmClockDTO.class);
    }

    private AlarmClock convertToClock(AlarmClockDTO alarmClock) {
        return mapper.map(alarmClock, AlarmClock.class);
    }

    private AlarmClock convertToClockFromDefault(DefaultAlarmClock alarmClock) {
        return mapper.map(alarmClock, AlarmClock.class);
    }
}