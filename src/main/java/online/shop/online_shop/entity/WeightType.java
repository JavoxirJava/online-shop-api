package online.shop.online_shop.entity;

import jakarta.persistence.Entity;
import lombok.*;
import online.shop.online_shop.entity.template.AbsNameEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class WeightType extends AbsNameEntity {
}
