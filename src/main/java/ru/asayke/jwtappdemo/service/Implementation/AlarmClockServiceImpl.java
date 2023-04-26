package ru.asayke.jwtappdemo.service.Implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asayke.jwtappdemo.dto.AlarmClockDTO;
import ru.asayke.jwtappdemo.models.AlarmClock;
import ru.asayke.jwtappdemo.models.User;
import ru.asayke.jwtappdemo.repository.AlarmClockRepository;
import ru.asayke.jwtappdemo.service.AlarmClockService;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class AlarmClockServiceImpl implements AlarmClockService {
    private final AlarmClockRepository clockRepository;

    @Override
    public Optional<AlarmClock> findByUser(User user) {
        return clockRepository.findByUser(user);
    }

    @Override
    @Transactional
    public void save(AlarmClock clock) {
        clockRepository.save(clock);
    }
    @Override
    @Transactional
    public void resetClock(AlarmClockDTO alarmClockDTO, AlarmClock clock) {
        clock.setAlarmEnabled(alarmClockDTO.getAlarmEnabled());
        clock.setAlarmVolume(alarmClockDTO.getAlarmVolume());
        clock.setBedtime(alarmClockDTO.getBedtime());
        clock.setRemindToCharge(alarmClockDTO.getRemindToCharge());
        clock.setRemindToSleep(alarmClockDTO.getRemindToSleep());
        clock.setSnoozeTime(alarmClockDTO.getSnoozeTime());
        clock.setVibrationEnabled(alarmClockDTO.getVibrationEnabled());
        clock.setWakeupTime(alarmClockDTO.getWakeupTime());

        clockRepository.save(clock);
    }

    @Override
    public boolean existsByUser(User user) {
        return clockRepository.existsByUser(user);
    }
}
