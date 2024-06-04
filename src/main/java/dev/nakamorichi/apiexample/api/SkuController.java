package dev.nakamorichi.apiexample.api;

import dev.nakamorichi.apiexample.api.request.SkuPatchRequest;
import dev.nakamorichi.apiexample.api.request.SkuPostRequest;
import dev.nakamorichi.apiexample.api.request.SkuPutRequest;
import dev.nakamorichi.apiexample.api.request.SkuSearchRequest;
import dev.nakamorichi.apiexample.api.response.SkuGetResponse;
import dev.nakamorichi.apiexample.api.response.SkuPatchResponse;
import dev.nakamorichi.apiexample.api.response.SkuPostResponse;
import dev.nakamorichi.apiexample.api.response.SkuPutResponse;
import dev.nakamorichi.apiexample.api.response.SkuSearchResponse;
import dev.nakamorichi.apiexample.service.SkuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@Slf4j
public class SkuController {

    private final SkuService skuService;

    /**
     * API for creating an SKU
     */
    @PostMapping("/api/sku")
    public Mono<SkuPostResponse> postSku(@RequestBody SkuPostRequest request) {
        return skuService
                .createSku(request)
                .map(sku -> SkuPostResponse
                        .builder()
                        .id(sku.getId())
                        .price(sku.getPrice())
                        .build()
                )
                // example of logging requests and responses
                .doOnSubscribe((x) -> log.info("Request: {}", request))
                .doOnSuccess((response) -> log.info("Response: {}", response));
    }

    /**
     * API for fetching an SKU
     */
    @GetMapping("/api/sku/{id}")
    public Mono<ResponseEntity<SkuGetResponse>> getSku(@PathVariable Long id) {
        return skuService.getSku(id)
                .map(sku -> SkuGetResponse
                        .builder()
                        .id(sku.getId())
                        .price(sku.getPrice())
                        .build())
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
    }

    /**
     * API for searching SKUs in specified price range
     */
    @GetMapping("/api/sku")
    public Flux<SkuSearchResponse> searchSku(@ModelAttribute SkuSearchRequest request) {
        return skuService
                .searchSku(request)
                .map(sku -> SkuSearchResponse
                        .builder()
                        .id(sku.getId())
                        .price(sku.getPrice())
                        .build());
    }

    /**
     * API for updating a whole SKU
     */
    @PutMapping("/api/sku/{id}")
    public Mono<SkuPutResponse> putSku(@PathVariable Long id, @RequestBody SkuPutRequest request) {
        return skuService
                .updateSku(id, request)
                .map(sku -> SkuPutResponse
                        .builder()
                        .id(sku.getId())
                        .price(sku.getPrice())
                        .build());
    }

    /**
     * API for partially updating an SKU
     */
    @PatchMapping("/api/sku/{id}")
    public Mono<SkuPatchResponse> patchSku(@PathVariable Long id, @RequestBody SkuPatchRequest request) {
        return skuService
                .patchSku(id, request)
                .map(sku -> SkuPatchResponse
                        .builder()
                        .id(sku.getId())
                        .price(sku.getPrice())
                        .build()
                );
    }

    /**
     * API for deleting an SKU
     */
    @DeleteMapping("/api/sku/{id}")
    public Mono<Void> deleteSku(@PathVariable Long id) {
        return skuService.deleteSku(id);
    }
}
