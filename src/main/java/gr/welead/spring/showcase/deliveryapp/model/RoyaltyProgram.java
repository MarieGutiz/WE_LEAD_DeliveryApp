package gr.welead.spring.showcase.deliveryapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "ROYALTY_PROGRAMS")
@SequenceGenerator(name = "idGenerator", sequenceName = "ROYALTY_PROGRAMS_SEQ", initialValue = 1, allocationSize = 1)
public class RoyaltyProgram extends BaseModel {//For clients
    @Column(nullable = false)
    private Long points;

    @Column(nullable = false)
    private String programDescription;
}
