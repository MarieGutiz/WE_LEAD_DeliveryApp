package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Offer;
import gr.welead.spring.showcase.deliveryapp.repository.OfferRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl extends BaseServiceImpl<Offer> implements OfferService{
    final OfferRepository offerRepository;
    @Override
    protected JpaRepository<Offer, Long> getRepository() {
        return offerRepository;
    }
}
