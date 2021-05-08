package pl.tl.crm;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
class Item {
    @NonNull
    String name;
    @NonNull
    Integer unitPrice;
    Integer quantity;
}
