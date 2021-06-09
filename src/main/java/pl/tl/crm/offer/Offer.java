package pl.tl.crm.offer;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import pl.tl.crm.company.Company;
import pl.tl.crm.item.Item;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "offers")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;

    @NonNull
    @ManyToOne
    Company company;

    Integer discount;

    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "offer")
    Set<Item> items;
}
