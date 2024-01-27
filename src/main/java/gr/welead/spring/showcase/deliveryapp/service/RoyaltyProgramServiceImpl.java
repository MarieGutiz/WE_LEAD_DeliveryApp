package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.RoyaltyProgram;
import gr.welead.spring.showcase.deliveryapp.repository.RoyaltyProgramRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoyaltyProgramServiceImpl extends BaseServiceImpl<RoyaltyProgram> implements RoyaltyProgamService {
    final RoyaltyProgramRepository royaltyProgramRepository;

    @Override
    protected JpaRepository<RoyaltyProgram, Long> getRepository() {
        return royaltyProgramRepository;
    }

}
