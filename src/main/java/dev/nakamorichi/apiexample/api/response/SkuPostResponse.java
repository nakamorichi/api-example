package dev.nakamorichi.apiexample.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * API response when creating an SKU
 */
@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
public class SkuPostResponse {
    private final Long id;
    private final Long price;
}
