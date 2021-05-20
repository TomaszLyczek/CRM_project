package pl.tl.crm;

import lombok.*;
import pl.tl.crm.company.Company;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
class Offer {
    @NonNull
    Company company;
    //@NonNull
    //List<Item> itemList;
    Integer discountPercentage;
}
