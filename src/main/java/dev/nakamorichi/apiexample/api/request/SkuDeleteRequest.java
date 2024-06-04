package dev.nakamorichi.apiexample.api.request;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * API request for deleting an SKU (most likely unnecessary, as DELETE requests tend not to have body)
 */
@Value
@Builder(toBuilder = true)
@Jacksonized
public class SkuDeleteRequest {
    // TODO: define structure in case the class is needed
}
