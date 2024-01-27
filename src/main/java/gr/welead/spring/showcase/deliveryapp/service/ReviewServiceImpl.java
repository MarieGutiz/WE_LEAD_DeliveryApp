package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.StoreReview;
import gr.welead.spring.showcase.deliveryapp.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl extends BaseServiceImpl<StoreReview> implements ReviewService{
    final ReviewRepository reviewRepository;

    @Override
    protected JpaRepository<StoreReview, Long> getRepository() {
        return reviewRepository;
    }
}
