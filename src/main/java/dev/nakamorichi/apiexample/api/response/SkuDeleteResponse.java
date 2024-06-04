package dev.nakamorichi.apiexample.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

/**
 * API response when SKU deleted (most likely unnecessary, as DELETE responses often don't have body)
 */
@Value
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Jacksonized
public class SkuDeleteResponse {
    // TODO: define structure in case the class is needed
}
