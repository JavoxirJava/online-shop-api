package online.shop.online_shop.entity;

import jakarta.persistence.Entity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import online.shop.online_shop.entity.template.AbsNameEntity;

@Getter
@Setter
@NoArgsConstructor
@Builder
@Entity
public class Category extends AbsNameEntity {

//    public Category(Long id, String name) {
//        super(id, name);
//    }
}
