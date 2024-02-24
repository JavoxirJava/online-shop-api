package online.shop.online_shop.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;
import online.shop.online_shop.entity.template.AbsNameEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Image extends AbsNameEntity {
    @Column(nullable = false)
    private String contentType;

    @Column(nullable = false)
    private Long size;
}
