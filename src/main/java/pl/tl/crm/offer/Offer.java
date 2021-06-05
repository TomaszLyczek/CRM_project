package pl.tl.crm.offer;

import lombok.*;
import pl.tl.crm.company.Company;

import javax.persistence.*;

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

            @Column(name = "company_id")
    Integer company;

    Integer discount;
}
