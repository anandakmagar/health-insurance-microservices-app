package com.securityservice.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    @Autowired
    private JWTService jwtService;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private PasswordResetService passwordResetService;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest){
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

//    @GetMapping("/validate")
//    public String validateToken(@RequestParam("jwt") String jwt) {
//        jwtService.validateToken(jwt);
//        return "Token is valid";
//    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest){
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @PostMapping("/password-reset/{username}")
    public ResponseEntity<Void> sendCode(@PathVariable String username){
        authenticationService.sendCode(username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/password-change/{resetCode}/{username}/{newPassword}")
    public ResponseEntity<String> changePassword(@PathVariable String resetCode, @PathVariable String username, @PathVariable String newPassword){
        return ResponseEntity.ok(authenticationService.changePassword(resetCode, username, newPassword));
    }
}
