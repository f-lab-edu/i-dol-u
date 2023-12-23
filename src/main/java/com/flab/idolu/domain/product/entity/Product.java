package com.flab.idolu.domain.product.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Product {

	private Long id;
	private Long categoryId;
	private Long iDolId;
	private String name;
	private BigDecimal price;
	private String imageUrl;
	private String description;
	private boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
