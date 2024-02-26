package online.shop.online_shop.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online.shop.online_shop.dto.AuthLoginDto;
import online.shop.online_shop.entity.UserDto;
import online.shop.online_shop.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping( "/register")
    public HttpEntity<?> registration(
            @Valid @RequestBody UserDto user,
            @Parameter(name = "ROLE", description = "Role:  ROLE_USER", required = true,
                    schema = @Schema(type = "string",
                    allowableValues = {"USER", "ADMIN" }))
            @RequestParam(name = "ROLE", required = false) String role)  {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.forRegister(user, role));
    }

    @PostMapping( "/login")
    public HttpEntity<?> login(@Valid @RequestBody AuthLoginDto loginDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.forLogin(loginDTO));
    }

}
