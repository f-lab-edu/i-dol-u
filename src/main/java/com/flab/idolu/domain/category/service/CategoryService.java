package com.flab.idolu.domain.category.service;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.flab.idolu.domain.category.entity.Category;
import com.flab.idolu.domain.category.entity.Group1;
import com.flab.idolu.domain.category.entity.Group2;
import com.flab.idolu.domain.category.exception.CategoryNotFoundException;
import com.flab.idolu.domain.category.repository.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {

	private final CategoryRepository categoryRepository;

	public Category findCategoryByGroup1AndGroup2(String group1, String group2) {
		return categoryRepository.findCategoryByGroup1AndGroup2(toGroup1(group1), toGroup2(group2))
			.orElseThrow(
				() -> new CategoryNotFoundException("%s, %s에 해당하는 카테고리가 없습니다".formatted(group1, group2)));
	}

	private Group1 toGroup1(String group) {
		return Arrays.stream(Group1.values())
			.filter(group1 -> group1.name().equals(group))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("%s에 해당하는 group1이 없습니다.".formatted(group)));
	}

	private Group2 toGroup2(String group) {
		return Arrays.stream(Group2.values())
			.filter(group2 -> group2.name().equals(group))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException("%s에 해당하는 group2이 없습니다.".formatted(group)));
	}
}
