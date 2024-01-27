package gr.welead.spring.showcase.deliveryapp.controller;

import gr.welead.spring.showcase.deliveryapp.mapper.BaseMapper;
import gr.welead.spring.showcase.deliveryapp.mapper.ProductOfferMapper;
import gr.welead.spring.showcase.deliveryapp.model.ProductOffer;
import gr.welead.spring.showcase.deliveryapp.service.BaseService;
import gr.welead.spring.showcase.deliveryapp.service.OfferService;
import gr.welead.spring.showcase.deliveryapp.service.ProductOfferService;
import gr.welead.spring.showcase.deliveryapp.service.ProductService;
import gr.welead.spring.showcase.deliveryapp.transfer.ApiResponse;
import gr.welead.spring.showcase.deliveryapp.transfer.ProductOfferDetails;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.ProductOfferResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("offers")
public class ProductOfferController extends BaseController<ProductOffer, ProductOfferResource> {
    final ProductOfferService productOfferService;
    final ProductOfferMapper productOfferMapper;
    final ProductService productService;
    final OfferService offerService;

    @Override
    protected BaseService<ProductOffer, Long> getBaseService() {
        return productOfferService;
    }

    @Override
    protected BaseMapper<ProductOffer, ProductOfferResource> getMapper() {
        return productOfferMapper;
    }

    @GetMapping("/products-with-offers")
    public ResponseEntity<ApiResponse<List<ProductOfferDetails[]>>> getProductsHavingOffers() {
        List<ProductOfferDetails[]> productOffersDetails = productOfferService.findProductOffersDetails();
        logger.info("productOffersDetails {}", productOffersDetails);
        return ResponseEntity.ok(
                ApiResponse.<List<ProductOfferDetails[]>>builder()
                        .data(productOffersDetails)
                        .build()
        );
    }
}
