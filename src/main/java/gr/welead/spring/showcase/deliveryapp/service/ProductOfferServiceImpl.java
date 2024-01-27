package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Offer;
import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.ProductOffer;
import gr.welead.spring.showcase.deliveryapp.repository.ProductOfferRepository;
import gr.welead.spring.showcase.deliveryapp.transfer.ProductOfferDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductOfferServiceImpl extends BaseServiceImpl<ProductOffer> implements ProductOfferService{

    final ProductOfferRepository productOfferRepository;
    @Override
    protected JpaRepository<ProductOffer, Long> getRepository() {
        return productOfferRepository;
    }

    @Override
    public List<ProductOffer> findAllProductOffers() {
        return productOfferRepository.findAllProductOffers();
    }

    @Override
    public List<ProductOffer> findByProduct(Product product) {
        return productOfferRepository.findByProduct(product);
    }

    @Override
    public List<ProductOffer> findByOffer(Offer offer) {
        return productOfferRepository.findByOffer(offer);
    }

    @Override
    public List<ProductOfferDetails[]> findProductOffersDetails() {
        return productOfferRepository.findProductOffersDetails();
    }
}
