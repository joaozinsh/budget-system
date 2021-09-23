package com.varejonline.budgetsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.varejonline.budgetsystem.model.Budget;

public interface BudgetRepository extends JpaRepository<Budget, Long>{

}
