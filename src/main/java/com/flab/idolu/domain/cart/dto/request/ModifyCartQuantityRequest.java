package com.flab.idolu.domain.cart.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Builder
public record ModifyCartQuantityRequest(
	Integer quantity
) {
}
