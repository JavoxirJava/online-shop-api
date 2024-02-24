package online.shop.online_shop.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {

    private Long id;

    @NotNull(message = "firstName cannot be null")
    private String firstName;

    private String lastName;

    @NotNull(message = "email cannot be null")
    private String email;

    @NotNull(message = "phoneNumber cannot be null")
    private String phoneNumber;

    @NotNull(message = "password cannot be null")
    private String password;
}
