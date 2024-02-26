package online.shop.online_shop.dataLoader;

import online.shop.online_shop.entity.Role;
import online.shop.online_shop.entity.User;
import online.shop.online_shop.entity.enums.RoleName;
import online.shop.online_shop.repository.RoleRepository;
import online.shop.online_shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String initMode;

    @Override
    public void run(String... args){
        if (initMode.equals("create-drop") || initMode.equals("create")) {
            roleRepository.save(Role.builder().roleName(RoleName.ROLE_USER).build());
            roleRepository.save(Role.builder().roleName(RoleName.ROLE_ADMIN).build());
            User build = User.builder()
                    .firstName("Elnur")
                    .lastName("Nur")
                    .phoneNumber("992827734")
                    .email("elnur@gmail.com")
                    .role(roleRepository.getByRoleName(RoleName.ROLE_ADMIN))
                    .password(passwordEncoder.encode("123"))
                    .build();
            userRepository.save(build);
        }

    }
}

