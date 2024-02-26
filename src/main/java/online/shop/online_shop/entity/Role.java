package online.shop.online_shop.entity;

import jakarta.persistence.*;
import lombok.*;
import online.shop.online_shop.entity.enums.RoleName;
import online.shop.online_shop.entity.template.AbsNameEntity;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    @Override
    public String getAuthority() {
        return roleName.name();
    }
}
