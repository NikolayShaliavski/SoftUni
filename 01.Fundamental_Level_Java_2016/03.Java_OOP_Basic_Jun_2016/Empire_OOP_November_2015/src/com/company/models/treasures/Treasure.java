package com.company.models.treasures;

/**
 * Created by Nick on 8.7.2016 г..
 */
public abstract class Treasure {

    private int quantity;
    private String type;

    Treasure(int quantity, String type) {
        this.setQuantity(quantity);
        this.type = type;
    }

    private void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity cannot be less or equal to zero.");
        }
        this.quantity = quantity;
    }
}
