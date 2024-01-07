//package gr.welead.spring.showcase.deliveryapp.repository;
//
//import gr.welead.spring.showcase.deliveryapp.model.Order;
//import gr.welead.spring.showcase.deliveryapp.model.Product;
//import gr.welead.spring.showcase.deliveryapp.model.ProductCategory;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Objects;
//import java.util.concurrent.ConcurrentHashMap;
//import java.util.concurrent.atomic.AtomicLong;
//import java.util.stream.Collectors;
//
//@Repository
//public class ProductRepositoryImpl extends BaseRepositoryImpl<Product> implements ProductRepository {
//    private final ConcurrentHashMap<Long, Product> storage = new ConcurrentHashMap<>();
//    private final AtomicLong sequence = new AtomicLong(0);
//
//    @Override
//    protected ConcurrentHashMap<Long, Product> getStorage() {
//        return storage;
//    }
//
//    @Override
//    protected AtomicLong getSequence() {
//        return sequence;
//    }
//
//    @Override
//    public Product findBySerial(String serial) {
//        return getStorage().values()
//                .stream()
//                .filter(product -> product.getSerial().equalsIgnoreCase(serial))
//                .findFirst()
//                .orElse(null);
//    }
//
//    @Override
//    public List<Product> findByCategory(ProductCategory productCategory) {
//        return getStorage().values()
//                .stream()
//                .filter(product -> product.getCategory().equals(productCategory))
//                .toList();
//    }
//
//    @Override
//    public List<Product> findAllByIdIn(List<Long> ids) {
//        return ids.stream()
//                .map(this::get)
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());
//    }
//
//    public void setProductCategory(String categoryDescription) {
//        //To Do
//    }
//}
