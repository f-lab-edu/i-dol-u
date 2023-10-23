package com.flab.idolu.domain.member.repository;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.flab.idolu.domain.member.entity.Member;

@Mapper
@Repository
public interface MemberRepository {

	Long insertMember(Member member);

	Optional<Member> findByEmail(String email);
}
