package com.flab.idolu.domain.category.entity;

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
public class Category {

	private Long id;
	private String group1;
	private String group2;
	private boolean isDeleted;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}
