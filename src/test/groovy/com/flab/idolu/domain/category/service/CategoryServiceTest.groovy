package com.flab.idolu.domain.category.service


import com.flab.idolu.domain.category.entity.Group1
import com.flab.idolu.domain.category.entity.Group2
import com.flab.idolu.domain.category.exception.CategoryNotFoundException
import com.flab.idolu.domain.category.repository.CategoryRepository
import spock.lang.Specification

import static com.flab.idolu.domain.fixture.CategoryFixture.DEFAULT_CATEGORY

class CategoryServiceTest extends Specification {

    CategoryService categoryService = Mock()
    CategoryRepository categoryRepository = Mock()

    def setup() {
        categoryService = new CategoryService(categoryRepository)
    }

    def "카테고리 조회 성공 테스트"() {
        given:
        categoryRepository.findCategoryByGroup1AndGroup2(Group1.CLOTH, Group2.TOP) >> Optional.of(DEFAULT_CATEGORY)

        when:
        def category = categoryService.findCategoryByGroup1AndGroup2("CLOTH", "TOP")

        then:
        category.group1 == Group1.CLOTH
        category.group2 == Group2.TOP
    }

    def "카테고리 유효성 실패 테스트: #exceptionMessage"() {
        when:
        categoryService.findCategoryByGroup1AndGroup2(group1, group2)

        then:
        def e = thrown(IllegalArgumentException)
        e.message == exceptionMessage

        where:
        group1      | group2    | exceptionMessage
        "ACCESSORY" | "KEYRING" | "ACCESSORY에 해당하는 group1이 없습니다."
        "CLOTH"     | "BOTTOM"  | "BOTTOM에 해당하는 group2이 없습니다."
        "CLOTH"     | "KEYRING" | "KEYRING에 해당하는 group2이 없습니다."
    }

    def "카테고리 조회 실패 테스트"() {
        given:
        categoryRepository.findCategoryByGroup1AndGroup2(Group1.CLOTH, Group2.TOP) >> Optional.empty()

        when:
        categoryService.findCategoryByGroup1AndGroup2("CLOTH", "TOP")

        then:
        def e = thrown(CategoryNotFoundException)
        e.message == "CLOTH, TOP에 해당하는 카테고리가 없습니다"
    }
}
