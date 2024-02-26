package online.shop.online_shop.controller;

import lombok.RequiredArgsConstructor;
import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.entity.User;
import online.shop.online_shop.security.CurrentUser;
import online.shop.online_shop.service.UserService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    final UserService userService;

    @GetMapping("/getMe")
    public HttpEntity<?> getMe(@CurrentUser User user) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getMe(user.getId()));
    }
}
