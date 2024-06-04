package dev.nakamorichi.apiexample.api.request;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder(toBuilder = true)
@Jacksonized
public class SkuPutRequest {
    private final Long price;
    // TODO: add other fields that are allowed to be updated
}
