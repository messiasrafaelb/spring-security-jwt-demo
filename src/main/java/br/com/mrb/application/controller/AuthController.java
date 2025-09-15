package br.com.mrb.application.controller;

import br.com.mrb.application.dto.AuthRequest;
import br.com.mrb.application.dto.AuthResponse;
import br.com.mrb.application.dto.RegisterRequest;
import br.com.mrb.application.dto.RegisterResponse;
import br.com.mrb.application.service.CustomDetailsService;
import br.com.mrb.application.service.JwtService;
import br.com.mrb.application.service.RegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final RegisterService registerService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        var authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(
                                                            request.email(), request.password()));
        var user = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        var user = registerService.save(request);
        return ResponseEntity.status(CREATED).body(user);
    }
}
