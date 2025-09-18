package br.com.mrb.application.service.security;

import br.com.mrb.application.dto.AuthRequest;
import br.com.mrb.application.dto.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtService jwtService;
    private final RegisterService registerService;

    public AuthResponse userLogin(AuthRequest request) {
        var authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password()));
        var user = (UserDetails) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        return new AuthResponse(token);
    }
}
