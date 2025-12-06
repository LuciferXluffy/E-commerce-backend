    package com.lucifer.shoes.service;

    import com.lucifer.shoes.dto.LoginRequest;
    import com.lucifer.shoes.model.User;
    import com.lucifer.shoes.repository.UserRepository;
    import lombok.RequiredArgsConstructor;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.stereotype.Service;

    @Service
    @RequiredArgsConstructor
    public class LoginService {

        private final UserRepository userRepository;
        private final BCryptPasswordEncoder bCryptPasswordEncoder;

        public boolean isValid(LoginRequest loginRequest) {
            User user = userRepository.findByEmail(loginRequest.getEmail());

            if (user == null) {
                return false;
            }

            return bCryptPasswordEncoder.matches(
                    loginRequest.getPassword(),
                    user.getPassword()
            );
        }

    }
