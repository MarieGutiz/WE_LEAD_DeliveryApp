package gr.welead.spring.showcase.deliveryapp.model;

import lombok.Getter;

@Getter
public enum OfferType {
    FIXED_AMOUNT_DISCOUNT("Fixed Amount Discount"),
    BOGO("Buy One, Get One Free"),
    LOYALTY_POINTS("Buy one, Get 200 points"),
    DISCOUNT("Discount %");


    // Add more offer types as needed

    private final String displayName;

    OfferType(String displayName) {
        this.displayName = displayName;
    }

}
