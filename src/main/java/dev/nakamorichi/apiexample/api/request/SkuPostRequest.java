package dev.nakamorichi.apiexample.api.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class SkuPostRequest {
    @NotNull // example of request validation (will fail if price not specified)
    private final Long price;
    // TODO: add other fields that are to be set during creation
}
