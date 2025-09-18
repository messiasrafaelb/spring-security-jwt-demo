package br.com.mrb.application.controller;

import br.com.mrb.application.dto.AuthRequest;
import br.com.mrb.application.dto.AuthResponse;
import br.com.mrb.application.dto.RegisterRequest;
import br.com.mrb.application.dto.RegisterResponse;
import br.com.mrb.application.service.security.AuthService;
import br.com.mrb.application.service.security.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final RegisterService registerService;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.userLogin(request));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(CREATED).body(registerService.save(request));
    }
}
