package online.shop.online_shop.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AuthLoginDto {
    private String phoneNumber;
    private String password;
}
