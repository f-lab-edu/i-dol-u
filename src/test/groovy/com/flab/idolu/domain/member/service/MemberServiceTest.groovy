package com.flab.idolu.domain.member.service

import com.flab.idolu.domain.fixture.MemberFixture
import com.flab.idolu.domain.member.exception.EmailDuplicateException
import com.flab.idolu.domain.member.exception.MemberNotFoundException
import com.flab.idolu.domain.member.exception.PasswordNotMatchException
import com.flab.idolu.domain.member.repository.MemberRepository
import org.springframework.security.crypto.password.PasswordEncoder
import spock.lang.Specification

import static com.flab.idolu.domain.fixture.MemberFixture.*

class MemberServiceTest extends Specification {

    MemberService memberService
    MemberRepository memberRepository = Mock()
    PasswordEncoder passwordEncoder = Mock()

    def setup() {
        memberService = new MemberService(memberRepository, passwordEncoder)
    }

    def "회원가입 성공 테스트"() {
        given:
        memberRepository.findByEmail(_) >> Optional.empty()
        memberRepository.insertMember(_) >> 1L

        expect:
        memberService.signUp(DEFAULT_SIGNUP_MEMBER) == 1L
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
        INVALID_PHONE_MEMBER          | "휴대전화 양식에 맞춰야 합니다."
    }

    def "로그인 성공 테스트"() {
        given:
        memberRepository.findByEmail(_) >> Optional.ofNullable(DEFAULT_MEMBER)
        passwordEncoder.matches(_, _) >> true

        when:
        def member = memberService.login(MemberFixture.DEFAULT_LOGIN_MEMBER)

        then:
        member.id == 1L
    }

    def "로그인 이메일 실패 테스트"() {
        given:
        memberRepository.findByEmail(_) >> Optional.empty()

        when:
        memberService.login(DEFAULT_LOGIN_MEMBER)

        then:
        def exception = thrown(MemberNotFoundException)
        exception.message == "존재하지 않는 이메일입니다."
    }

    def "로그인 비밀번호 실패 테스트"() {
        given:
        memberRepository.findByEmail(_) >> Optional.ofNullable(DEFAULT_MEMBER)
        passwordEncoder.matches(_, _) >> false

        when:
        memberService.login(DEFAULT_LOGIN_MEMBER)

        then:
        def exception = thrown(PasswordNotMatchException)
        exception.message == "비밀번호가 틀렸습니다."
    }

    def "로그인 유효성 검사 테스트: #exceptionMessage"() {
        when:
        memberService.login(member)

        then:
        def exception = thrown(IllegalArgumentException)
        exception.message == exceptionMessage

        where:
        member                        | exceptionMessage
        BLANK_EMAIL_LOGIN_MEMBER      | "이메일을 입력해야 합니다."
        BLANK_PASSWORD_LOGIN_MEMBER   | "비밀번호를 입력해야 합니다."
        INVALID_EMAIL_LOGIN_MEMBER    | "이메일 양식에 맞춰야 합니다."
        INVALID_PASSWORD_LOGIN_MEMBER | "비밀번호는 영문과 숫자 조합으로 8 ~ 16자리까지 가능합니다."
    }

    def "회원정보 조회 테스트"() {
        given:
        memberRepository.findById(_) >> Optional.ofNullable(DEFAULT_MEMBER)

        when:
        def member = memberService.getMemberInfo(1L)

        then:
        member.email == "testUser@email.com"
        member.name == "testUser1"
        member.phone == "01011111111"
    }

    def "회원정보 실패 테스트"() {
        given:
        memberRepository.findById(_) >> Optional.empty()

        when:
        memberService.getMemberInfo(1L)

        then:
        def exception = thrown(MemberNotFoundException)
        exception.message == "존재하지 않는 회원입니다."
    }

    def "회원정보 수정 성공 테스트"() {
        when:
        memberService.modifyMemberInfo(DEFAULT_MODIFY_MEMBER, 1L)

        then:
        1 * memberRepository.updateMember(DEFAULT_MEMBER)
    }

    def "회원정보 수정 실패 테스트"() {
        when:
        memberService.modifyMemberInfo(member, 1L)

        then:
        def exception = thrown(IllegalArgumentException)
        exception.message == exceptionMessage

        where:
        member                      | exceptionMessage
        BLANK_NAME_MODIFY_MEMBER    | "이름을 입력해야 합니다."
        BLANK_PHONE_MODIFY_MEMBER   | "휴대전화를 입력해야 합니다."
        INVALID_PHONE_MODIFY_MEMBER | "휴대전화 양식에 맞춰야 합니다."
    }

    def "회원 탈퇴 성공 테스트"() {
        when:
        memberService.withdrawMember(1L)

        then:
        1 * memberRepository.updateIsDeleted(1L)
    }
}
