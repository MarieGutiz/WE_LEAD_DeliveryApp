package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.RoyaltyProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoyaltyProgramRepository extends JpaRepository<RoyaltyProgram, Long> {
}
