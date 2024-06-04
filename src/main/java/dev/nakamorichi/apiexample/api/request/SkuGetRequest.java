package dev.nakamorichi.apiexample.api.request;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * API request for fetching an SKU (most likely unnecessary, as basic GET endpoint only needs entity ID)
 */
@Value
@Builder(toBuilder = true)
@Jacksonized
public class SkuGetRequest {
    // TODO: define structure in case the class is needed
}
