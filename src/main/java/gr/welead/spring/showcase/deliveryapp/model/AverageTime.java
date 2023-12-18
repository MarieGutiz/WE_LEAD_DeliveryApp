package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
public class AverageTime extends BaseModel {//Avg time either to deliver or to prepare the meals/coffee and package for delivery
    // LocalTime averageTime = LocalTime.of(0, 20, 0);
    private String averageTime;
}
