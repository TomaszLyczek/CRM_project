package pl.tl.crm;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
class Company {
    @NonNull
    String name;
    @NonNull
    String address;
    Integer numberNIP;

}
