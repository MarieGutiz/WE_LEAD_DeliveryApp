package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.Offer;
import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.ProductOffer;
import gr.welead.spring.showcase.deliveryapp.transfer.ProductOfferDetails;

import java.util.List;

public interface ProductOfferService extends BaseService<ProductOffer, Long> {

    List<ProductOffer> findAllProductOffers();

    List<ProductOffer> findByProduct(Product product);

    List<ProductOffer> findByOffer(Offer offer);

    List<ProductOfferDetails[]> findProductOffersDetails();
}
