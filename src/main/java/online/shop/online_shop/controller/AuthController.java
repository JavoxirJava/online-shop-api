package online.shop.online_shop.controller;


import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.AuthLoginDto;
import online.shop.online_shop.dto.UserDto;
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
            @Parameter(name = "ROLE", description = "Role: ROLE_USER, ROLE_ADMIN", required = true,
                    schema = @Schema(type = "string",
                    allowableValues = {"ROLE_USER", "ROLE_ADMIN" }))
            @RequestParam(name = "ROLE", required = false) String role)  {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.forRegister(user, role));
    }

    @PostMapping( "/login")
    public HttpEntity<?> login(@Valid @RequestBody AuthLoginDto loginDTO) {
        ApiResponse<?> apiResponse = userService.forLogin(loginDTO);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.OK : HttpStatus.CONFLICT).body(apiResponse);
    }
}
