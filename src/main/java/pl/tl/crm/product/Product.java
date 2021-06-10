package pl.tl.crm.product;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "products")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    private String name;

    private String description;

    @Positive(message = "Price must not be less than 0")
    private Integer price;

}
