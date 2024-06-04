package dev.nakamorichi.apiexample.repository;

import dev.nakamorichi.apiexample.repository.entity.SkuEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.query.Param;
import reactor.core.publisher.Flux;

public interface SkuRepository extends R2dbcRepository<SkuEntity, Long> {
    // example of custom SQL query
    @Query("SELECT * FROM sku u WHERE u.price >= :priceMin AND u.price < :priceMax")
    Flux<SkuEntity> findByPriceRange(
            @Param("priceMin") Long priceMin,
            @Param("priceMax") Long priceMax);
}
