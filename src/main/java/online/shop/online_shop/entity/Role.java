package online.shop.online_shop.entity;

import jakarta.persistence.Entity;
import lombok.*;
import online.shop.online_shop.entity.template.AbsNameEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Role extends AbsNameEntity {
}
