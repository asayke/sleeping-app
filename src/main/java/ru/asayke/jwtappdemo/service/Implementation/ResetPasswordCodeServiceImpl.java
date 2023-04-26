package ru.asayke.jwtappdemo.service.Implementation;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.asayke.jwtappdemo.models.ResetPasswordCode;
import ru.asayke.jwtappdemo.models.User;
import ru.asayke.jwtappdemo.repository.ResetPasswordCodeRepository;
import ru.asayke.jwtappdemo.service.ResetPasswordCodeService;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class ResetPasswordCodeServiceImpl implements ResetPasswordCodeService {
    private final ResetPasswordCodeRepository codeRepository;

    @Override
    public Optional<ResetPasswordCode> findByUser(User user) {
        return codeRepository.findByUser(user);
    }

    @Override
    @Transactional
    public void save(ResetPasswordCode passwordCode) {
        codeRepository.save(passwordCode);
    }

    @Override
    @Transactional
    public void delete(ResetPasswordCode passwordCode) {
        codeRepository.delete(passwordCode);
    }

    @Override
    public boolean existByUser(User user) {
        return codeRepository.existsByUser(user);
    }

    @Override
    @Transactional
    public void deleteByUser(User user) {
        codeRepository.deleteByUser(user);
    }

    @Override
    public Optional<ResetPasswordCode> findByResetCode(int code) {
        return codeRepository.findByResetCode(code);
    }
}