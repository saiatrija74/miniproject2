package com.sai.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sai.entity.StatesList;

public interface StatesListRepo extends JpaRepository<StatesList, Serializable> {

	List<StatesList> findByCountryId(Integer countryId);

}
