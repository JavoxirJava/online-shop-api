package online.shop.online_shop.entity;

import jakarta.persistence.*;
import lombok.*;
import online.shop.online_shop.entity.template.AbsNameEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Product extends AbsNameEntity {

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String description;

    @ManyToOne(optional = false)
    private WeightType weightType;

    @OneToOne(optional = false)
    private Image image;

    @ManyToOne(optional = false)
    private Category category;
}
