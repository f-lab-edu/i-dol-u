package com.flab.idolu.domain.member.service

import com.flab.idolu.domain.member.exception.EmailDuplicateException
import com.flab.idolu.domain.member.repository.MemberRepository
import spock.lang.Specification

import static com.flab.idolu.domain.fixture.MemberFixture.*

class MemberServiceTest extends Specification {

    MemberService memberService
    MemberRepository memberRepository = Mock()

    def setup() {
        memberService = new MemberService(memberRepository)
    }

    def "이미 가입한 회원 실패 테스트"() {
        given:
        memberRepository.findByEmail(DEFAULT_SIGNUP_MEMBER.getEmail()) >> Optional.ofNullable(DEFAULT_SIGNUP_MEMBER)

        when:
        memberService.signUp(DEFAULT_SIGNUP_MEMBER)

        then:
        def exception = thrown(EmailDuplicateException)
        exception.message == "이미 가입한 이메일입니다. 입력된 이메일: testUser@email.com"
    }

    def "validation 실패 테스트: #exceptionMessage"() {
        when:
        memberService.signUp(member)

        then:
        def exception = thrown(IllegalArgumentException)
        exception.message == exceptionMessage

        where:
        member                        | exceptionMessage
        BLANK_EMAIL_MEMBER            | "이메일을 입력해야 합니다."
        BLANK_PASSWORD_MEMBER         | "비밀번호를 입력해야 합니다."
        BLANK_PASSWORD_CONFIRM_MEMBER | "비밀번호 확인을 입력해야 합니다."
        BLANK_NAME_MEMBER             | "이름을 입력해야 합니다."
        BLANK_PHONE_MEMBER            | "휴대전화를 입력해야 합니다."
        BLANK_ROLE_MEMBER             | "역할을 입력해야 합니다."
        INVALID_EMAIL_MEMBER          | "이메일 양식에 맞춰야 합니다."
        INVALID_PASSWORD_MEMBER       | "비밀번호는 영문과 숫자 조합으로 8 ~ 16자리까지 가능합니다."
        NOT_MATCHED_PASSWORD_MEMBER   | "비밀번호와 비밀번호 확인이 일치해야 합니다."
    }
}
