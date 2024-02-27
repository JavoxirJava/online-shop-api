package online.shop.online_shop.service;

import lombok.RequiredArgsConstructor;
import online.shop.online_shop.dto.ApiResponse;
import online.shop.online_shop.dto.AuthLoginDto;
import online.shop.online_shop.entity.User;
import online.shop.online_shop.dto.UserDto;
import online.shop.online_shop.entity.enums.RoleName;
import online.shop.online_shop.exception.GenericNotFoundException;
import online.shop.online_shop.repository.RoleRepository;
import online.shop.online_shop.repository.UserRepository;
import online.shop.online_shop.security.JwtProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    public UserDto getUserDto(User user){
    return UserDto.builder()
            .id(user.getId())
            .firstName(user.getFirstName())
            .lastName(user.getLastName())
            .email(user.getEmail())
            .phoneNumber(user.getPhoneNumber())
            .password(user.getPassword())
            .build();
    }

    public ApiResponse<?> getMe(Long userId){
        User user = userRepository.findById(userId).orElseThrow(
                () -> GenericNotFoundException.builder().statusCode(404).message("User not found").build());
        return ApiResponse.builder().body(getUserDto(user)).message("Successfully").success(true).build();
    }

    public ApiResponse<?> forRegister(UserDto user, String role) {
        if (!userRepository.existsByPhoneNumberEquals(user.getPhoneNumber())) {
            User user1 = new User();
            user1.setFirstName(user.getFirstName());
            user1.setLastName(user.getLastName());
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
            user1.setPhoneNumber(user.getPhoneNumber());
            user1.setRole(roleRepository.getByRoleName(RoleName.valueOf(role)));
            user1.setEmail(user.getEmail());
            userRepository.save(user1);
            return new ApiResponse<>("Successfully", true);
        }
        return new ApiResponse<>("Phone number already exist?", false);
    }

    public ApiResponse<?> forLogin(AuthLoginDto loginDTO) {
        User authUser = userRepository.findByPhoneNumberEquals(loginDTO.getPhoneNumber()).orElseThrow(() ->
                GenericNotFoundException.builder().message("User not found").statusCode(404).build());
        if (passwordEncoder.matches(loginDTO.getPassword(), authUser.getPassword())) {
            String token = jwtProvider.generateToken(loginDTO.getPhoneNumber());
            return new ApiResponse<>(token, authUser.getRole().getRoleName().name(), true);
        }
        return new ApiResponse<>("Password not match", false);
    }
}