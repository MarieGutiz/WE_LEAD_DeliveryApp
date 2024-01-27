package gr.welead.spring.showcase.deliveryapp.repository;

import gr.welead.spring.showcase.deliveryapp.model.Offer;
import gr.welead.spring.showcase.deliveryapp.model.Product;
import gr.welead.spring.showcase.deliveryapp.model.ProductOffer;
import gr.welead.spring.showcase.deliveryapp.transfer.ProductOfferDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductOfferRepository extends JpaRepository<ProductOffer, Long> {
    List<ProductOffer> findByProduct(Product product);

    List<ProductOffer> findByOffer(Offer offer);

    @Query("SELECT po FROM ProductOffer po WHERE po.product = :product")
    Optional<ProductOffer> findProductOfferByProduct(@Param("product") Product product);

    @Query("SELECT po FROM ProductOffer po JOIN FETCH po.product JOIN FETCH po.offer")
    List<ProductOffer> findAllProductOffers();

    @Query("select p.price AS price, " +
                    "p.category.description AS product_category_description, " +
                    "p.id AS product_id, " +
                    "p.serial AS serial, " +
                    "p.name AS name, " +
                    "s.name AS store_name, " +
                    "o.buyQuantity AS buyquantity, " +
                    "o.discount AS discount, " +
                    "o.freeQuantity AS freequantity, " +
                    "o.endDate AS enddate, " +
                    "o.id AS offer_id, " +
                    "o.startDate AS startdate, " +
                    "o.offerType AS offertype, " +
                    "o.description AS description, " +
                    "o.name AS offer_name " +
                    "FROM Product p " +
                    "JOIN ProductOffer po ON p.id = po.product.id " +
                    "JOIN Offer o ON po.offer.id = o.id " +
                    "JOIN Store s ON o.store.id = s.id")
    List<ProductOfferDetails[]> findProductOffersDetails();

}
