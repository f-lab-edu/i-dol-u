package com.flab.idolu.domain.fixture;

import com.flab.idolu.domain.product.entity.Product;

public class ProductFixture {

	public static Product DEFAULT_PRODUCT = Product.builder()
		.id(1L)
		.categoryId(1L)
		.iDolId(1L)
		.name("productA")
		.stock(3)
		.build();

	public static Product DEFAULT_PRODUCT_FOR_ORDER = Product.builder()
		.id(1L)
		.categoryId(1L)
		.iDolId(1L)
		.name("productA")
		.stock(3)
		.build();
}
