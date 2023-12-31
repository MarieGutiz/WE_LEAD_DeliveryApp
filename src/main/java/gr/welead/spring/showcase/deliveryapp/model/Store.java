package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor()
@ToString(callSuper = true)
public class Store extends BaseModel {
    private String name;//Store name
    private long afm;
    private String storeDescription;
    private Double ranking;
    private StoreStatus status;//Are we open or close?
    private List<String> reviews;
    private Address address;
    private StoreCategory category;
    private List<Product> menu;//The store sells?
    private AverageTime time;//Check class
    private Account account;//The owner of the store and his/her details
}
