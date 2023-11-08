package com.flab.idolu.domain.address.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.flab.idolu.domain.address.entity.Address;

@Mapper
@Repository
public interface AddressRepository {

	Long insertAddress(Address address);
}
