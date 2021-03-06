package com.example.qna.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public void save(User user) {
        String rawPassword = user.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        user.setPassword(encPassword);
        userRepository.save(user);
    }

    @Transactional
    public void update(User user) {
        User persistence = userRepository.findById(user.getId()).orElseThrow(() -> new IllegalArgumentException("해당하는 유저가 존재하지 않습니다."));
        String rawPassword = user.getPassword();
        String encPassword = passwordEncoder.encode(rawPassword);
        persistence.setPassword(encPassword);
        persistence.setNickname(user.getNickname());
    }
}
