package pl.tl.crm;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NonNull
    String name;
    @NonNull
    String address;
    Integer numberNIP;

}
