package dev.nakamorichi.apiexample.repository.entity;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Table("sku")
@Value
@Builder(toBuilder = true)
@RequiredArgsConstructor
public class SkuEntity {
    @Column("id")
    @Id
    private final Long id;

    @Column("price")
    @NotNull
    private final Long price;
}
