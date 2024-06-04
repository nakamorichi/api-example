package dev.nakamorichi.apiexample.service;

import dev.nakamorichi.apiexample.api.request.SkuPatchRequest;
import dev.nakamorichi.apiexample.api.request.SkuPostRequest;
import dev.nakamorichi.apiexample.api.request.SkuPutRequest;
import dev.nakamorichi.apiexample.api.request.SkuSearchRequest;
import dev.nakamorichi.apiexample.repository.SkuRepository;
import dev.nakamorichi.apiexample.repository.entity.SkuEntity;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class SkuService {

    private final SkuRepository skuRepository;

    public Mono<SkuEntity> createSku(SkuPostRequest request) {
        val newSku = SkuEntity
                .builder()
                .price(request.getPrice())
                .build();
        return skuRepository.save(newSku);
    }

    public Mono<SkuEntity> getSku(Long id) {
        return skuRepository.findById(id);
    }

    public Flux<SkuEntity> searchSku(SkuSearchRequest request) {
        return skuRepository.findByPriceRange(
                request.getPriceMin(),
                request.getPriceMax());
    }

    public Mono<SkuEntity> updateSku(Long id, SkuPutRequest request) {
        return skuRepository
                .findById(id)
                .map(sku -> sku
                        .toBuilder()
                        .price(request.getPrice()) // update all fields of fetched db record
                        .build()
                )
                .flatMap(skuRepository::save);
    }

    public Mono<SkuEntity> patchSku(Long id, SkuPatchRequest request) {
        return skuRepository
                .findById(id)
                .map(sku -> {
                    val updatedSku = sku.toBuilder();
                    // only update fields that have been changed
                    if (request.getPrice() != null) {
                        updatedSku.price(request.getPrice());
                    }
                    return updatedSku.build();
                })
                .flatMap(skuRepository::save);
    }

    public Mono<Void> deleteSku(Long id) {
        return skuRepository.deleteById(id);
    }
}
