package com.sai.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sai.entity.CountryList;

public interface CountryListRepo extends JpaRepository<CountryList, Serializable> {

}
