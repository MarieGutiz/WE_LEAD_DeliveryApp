package gr.welead.spring.showcase.deliveryapp.transfer.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class BaseResource implements Serializable {
    Long id;
}
