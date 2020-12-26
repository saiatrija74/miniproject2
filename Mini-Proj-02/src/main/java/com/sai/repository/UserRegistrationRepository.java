package com.sai.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sai.entity.UserRegistrationEntity;

public interface UserRegistrationRepository extends JpaRepository<UserRegistrationEntity, Serializable> {

	Optional<UserRegistrationEntity> findByEmail(String email);

}
