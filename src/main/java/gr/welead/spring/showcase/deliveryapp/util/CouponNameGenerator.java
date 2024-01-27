package gr.welead.spring.showcase.deliveryapp.util;

import java.security.SecureRandom;
import java.util.Random;

public class CouponNameGenerator {

    private static final String PREFIX = "WELEAD";
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int COUPON_NAME_LENGTH = 12;

    public static String generateCouponName() {
        StringBuilder couponName = new StringBuilder(PREFIX);

        Random random = new SecureRandom();

        for (int i = PREFIX.length(); i < COUPON_NAME_LENGTH; i++) {
            int randomIndex = random.nextInt(ALPHABET.length());
            couponName.append(ALPHABET.charAt(randomIndex));
        }

        return couponName.toString();
    }

    public static void main(String[] args) {
        // Example usage
        String couponName = generateCouponName();
        System.out.println("Generated Coupon Name: " + couponName);
    }
}
