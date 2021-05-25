package pl.tl.crm.item;

import lombok.*;
import pl.tl.crm.company.Company;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "offers")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NonNull
            @ManyToOne
    Company company;
    @ManyToOne
    @NonNull
    Item item;
    Integer discount;
}
