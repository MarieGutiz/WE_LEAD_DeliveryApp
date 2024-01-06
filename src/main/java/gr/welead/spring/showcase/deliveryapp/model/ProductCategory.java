package gr.welead.spring.showcase.deliveryapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class ProductCategory  {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String description;

    @OneToMany(mappedBy="productCategory")
    @JsonIgnore
    private List<Product> products;
}
