package com.varejonline.budgetsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.varejonline.budgetsystem.model.BudgetItem;

public interface BudgetItemRepository extends JpaRepository<BudgetItem, Long>{

}
