package gr.welead.spring.showcase.deliveryapp.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class RoyaltyProgram extends BaseModel {//For clients
    private Long points;
    private String programDescription;
}