package com.varejonline.budgetsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.varejonline.budgetsystem.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
