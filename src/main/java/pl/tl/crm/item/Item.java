package pl.tl.crm.item;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import pl.tl.crm.offer.Offer;

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
    Integer id;

    @NonNull
    @NotBlank(message = "Name must not be empty")
    String name;

    @NonNull
    Integer quantity;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;
}
