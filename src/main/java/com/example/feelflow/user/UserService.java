package com.example.feelflow.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password,
                           String nickname, String name, LocalDate birthday, String gender) {
        SiteUser user = new SiteUser();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setNickname(nickname);
        user.setName(name);
        user.setBirthday(birthday);
        user.setGender(gender);
        this.userRepository.save(user);
        return user;
    }

    public Optional<SiteUser> getByUsername(String username) {
        return userRepository.findByusername(username);
    }

    public boolean authenticate(String username, String password) {
        Optional<SiteUser> siteUser = getByUsername(username);
        return siteUser.map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }
}