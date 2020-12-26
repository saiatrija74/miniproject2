package com.sai.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sai.entity.CityList;

public interface CityListRepo extends JpaRepository<CityList, Serializable> {

	List<CityList> findByStateId(Integer stateId);

}
