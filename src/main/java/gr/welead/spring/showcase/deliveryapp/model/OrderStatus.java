package gr.welead.spring.showcase.deliveryapp.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    RAISED, IN_PROGRESS, DELIVERED, CANCELLED;
}
