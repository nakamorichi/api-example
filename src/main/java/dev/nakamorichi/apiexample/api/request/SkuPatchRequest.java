package dev.nakamorichi.apiexample.api.request;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * API request for partial updates
 */
@Value
@Builder(toBuilder = true)
@Jacksonized
public class SkuPatchRequest {
    private final Long price;
    // TODO: add other fields that are allowed to be updated
}
