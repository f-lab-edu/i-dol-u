package com.flab.idolu.domain.fixture;

import com.flab.idolu.domain.category.entity.Category;
import com.flab.idolu.domain.category.entity.Group1;
import com.flab.idolu.domain.category.entity.Group2;

public class CategoryFixture {

	public static Category DEFAULT_CATEGORY = Category.builder()
		.id(1L)
		.group1(Group1.CLOTH)
		.group2(Group2.TOP)
		.build();
}
