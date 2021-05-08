package pl.tl.crm;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
class Offer {
    @NonNull
    Company company;
    @NonNull
    List<Item> itemList;
    Integer discountPercentage;
}
