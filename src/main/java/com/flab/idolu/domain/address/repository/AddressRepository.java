package com.flab.idolu.domain.address.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.flab.idolu.domain.address.dto.response.AddressInfoDto;
import com.flab.idolu.domain.address.entity.Address;

@Mapper
@Repository
public interface AddressRepository {

	Long insertAddress(Address address);

	List<AddressInfoDto> findByMemberId(Long memberId);
}
