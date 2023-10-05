package com.borrow.borrowsecurity.auth;

import com.borrow.borrowsecurity.config.JwtService;
import com.borrow.borrowsecurity.user.Role;
import com.borrow.borrowsecurity.user.User;
import com.borrow.borrowsecurity.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    /**
     * Register User - Registrar Usuário
     * @param request
     * @return
     */
    public AuthenticationResponse register(RegisterRequest request) {
        // here I can put variable to determine which is the role of user.
        // also could insert that variable in the class RegisterRequest
        var user = User.builder()
                .name(request.getName())
                .documentNumber(request.getDocumentNumber())
                .segment(request.getSegment())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getSegment()))
                .build();

        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUser(),
                        request.getPassword()
                )
        );
        var user = repository.findByDocumentNumber(request.getUser())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
