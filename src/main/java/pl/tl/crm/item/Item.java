package pl.tl.crm.item;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import pl.tl.crm.offer.Offer;
import pl.tl.crm.product.Product;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "items")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NonNull
    @ManyToOne
    private Product product;

    @NonNull
    private Integer quantity;

    @NonNull
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;
}
