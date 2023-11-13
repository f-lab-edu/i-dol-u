package com.flab.idolu.domain.category.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.flab.idolu.domain.category.entity.Category;
import com.flab.idolu.domain.category.entity.Group1;
import com.flab.idolu.domain.category.entity.Group2;

@Mapper
@Repository
public interface CategoryRepository {

	Optional<Category> findCategoryByGroup1AndGroup2(@Param("group1") Group1 group1, @Param("group2") Group2 group2);
}
