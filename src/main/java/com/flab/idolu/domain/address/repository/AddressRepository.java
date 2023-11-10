package com.flab.idolu.domain.address.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.flab.idolu.domain.address.dto.response.AddressInfoDto;
import com.flab.idolu.domain.address.entity.Address;

@Mapper
@Repository
public interface AddressRepository {

	Long insertAddress(Address address);

	List<AddressInfoDto> findByMemberId(Long memberId);

	Optional<AddressInfoDto> findByIdAndMemberId(@Param("id") Long id, @Param("memberId") Long memberId);

	void updateAddressByIdAndMemberId(@Param("address") Address address, @Param("id") Long id);
}
