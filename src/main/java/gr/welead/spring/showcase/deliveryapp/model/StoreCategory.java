package gr.welead.spring.showcase.deliveryapp.model;

import lombok.Getter;

@Getter
public enum StoreCategory {
    SUPERMARKET("Supermarket"), COFFEE_AND_BEVERAGES("Αναψυκτήριο"), FAST_FOOD("Fast-Food"), RESTAURANT("Εστιατόριο");
    private final String category;

    StoreCategory(String category) {
        this.category = category;
    }

}
