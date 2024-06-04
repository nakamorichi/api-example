package dev.nakamorichi.apiexample.api.request;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class SkuSearchRequest {
    private final Long priceMin;
    private final Long priceMax;
}
