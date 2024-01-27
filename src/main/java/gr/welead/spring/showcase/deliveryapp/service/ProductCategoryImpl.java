package gr.welead.spring.showcase.deliveryapp.service;

import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;
import gr.welead.spring.showcase.deliveryapp.repository.ProductCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductCategoryImpl extends BaseServiceImpl<ProductCategory> implements ProductCategoryService{
    final ProductCategoryRepository productCategoryRepository;

    @Override
    protected JpaRepository<ProductCategory, Long> getRepository() {
        return productCategoryRepository;
    }
}
