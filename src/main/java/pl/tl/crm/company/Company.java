package pl.tl.crm.company;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "companies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    private String name;

    @NonNull
    private String address;

    private Integer numberNIP;

}
