package gr.welead.spring.showcase.deliveryapp.controller;

import gr.welead.spring.showcase.deliveryapp.mapper.BaseMapper;
import gr.welead.spring.showcase.deliveryapp.mapper.StoreMapper;
import gr.welead.spring.showcase.deliveryapp.model.Store;
import gr.welead.spring.showcase.deliveryapp.model.StoreCategory;
import gr.welead.spring.showcase.deliveryapp.service.BaseService;
import gr.welead.spring.showcase.deliveryapp.service.StoreService;
import gr.welead.spring.showcase.deliveryapp.transfer.ApiResponse;
import gr.welead.spring.showcase.deliveryapp.transfer.resource.StoreResource;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("stores")
@RequiredArgsConstructor
public class StoreController extends BaseController<Store, StoreResource>{
    private final StoreService storeService;
    private final StoreMapper storeMapper;

    @Override
    protected BaseService<Store, Long> getBaseService() {
        return storeService;
    }

    @Override
    protected BaseMapper<Store, StoreResource> getMapper() {
        return storeMapper;
    }


@GetMapping("store")
public ResponseEntity<ApiResponse<List<StoreResource>>> searchStores(
        @RequestParam(required = false) String name,
        @RequestParam(required = false) StoreCategory category,
        @RequestParam(required = false) String city) {

    List<Store> stores;

    if (name != null) {
        Store store = storeService.findByName(name);
        stores = (store != null) ? Collections.singletonList(store) : Collections.emptyList();
    } else if (category != null) {
        stores = storeService.findByCategory(category);
    } else if (city != null) {
        stores = storeService.findByCity(city);
    } else {
        return ResponseEntity.badRequest().build();
    }
//   logger.info("Store at controller {} ", stores);
    return ResponseEntity.ok(
            ApiResponse.<List<StoreResource>>builder()
                    .data(storeMapper.toResources(stores))
                    .build()
    );
}
    @GetMapping(headers = "Famous-Stores-In-General")
    public ResponseEntity<ApiResponse<List<StoreResource>>> listFamousStores() {
        List<Store> famousStores = storeService.findMostFamousStores();

        return ResponseEntity.ok(
                ApiResponse.<List<StoreResource>>builder()
                        .data(storeMapper.toResources(famousStores))
                        .build()
        );
    }

    @GetMapping(headers = "Famous-Stores-By-Category")
    public ResponseEntity<ApiResponse<List<StoreResource>>> listFamousStoresByCategory(
            @RequestParam("category") String categoryName) {

        List<Store> famousStoresByCategory = storeService.findMostFamousStoresPerCategory(categoryName);

        return ResponseEntity.ok(
                ApiResponse.<List<StoreResource>>builder()
                        .data(storeMapper.toResources(famousStoresByCategory))
                        .build()
        );
    }
}
