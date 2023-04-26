package ru.asayke.jwtappdemo.service.Implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asayke.jwtappdemo.models.Sleep;
import ru.asayke.jwtappdemo.models.User;
import ru.asayke.jwtappdemo.repository.SleepRepository;
import ru.asayke.jwtappdemo.repository.UserRepository;
import ru.asayke.jwtappdemo.service.SleepService;

import java.util.List;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class SleepServiceImpl implements SleepService {
    private final SleepRepository sleepRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public void addSleep(Sleep sleep, String username) {
        User user = userRepository.findByUsername(username);

        sleep.setUser(user);

        sleepRepository.save(sleep);
    }

    @Override
    public List<Sleep> findAllByUser(User user) {
        return sleepRepository.findAllByUser(user);
    }

    @Override
    public Sleep findLastUserSleep(User user) {
        return sleepRepository.findTopByUserOrderByIdDesc(user).orElse(null);
    }
}