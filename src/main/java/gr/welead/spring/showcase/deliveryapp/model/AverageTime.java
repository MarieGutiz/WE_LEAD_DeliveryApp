package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@ToString(callSuper = true)
public class AverageTime extends BaseModel {//Avg time either to deliver or to prepare the meals/coffee and package for delivery
    // LocalTime averageTime = LocalTime.of(0, 20, 0);
    //  LocalTime localTime = LocalTime.parse(timeString, DateTimeFormatter.ofPattern("HH:mm:ss"));
    private String averageTime;

    public LocalTime getLocalTime() {
        return LocalTime.parse(averageTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public void setAverageTime(LocalTime averageTime) {
        // Convert LocalTime to string when setting the value
        this.averageTime = averageTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    public void setAverageTime(String averageTime) {
        // Parse the input string and convert it to the required format
        LocalTime parsedTime = LocalTime.parse(averageTime, DateTimeFormatter.ofPattern("HH:mm:ss"));
        // Format and set the value
        this.setAverageTime(parsedTime);
    }
}
