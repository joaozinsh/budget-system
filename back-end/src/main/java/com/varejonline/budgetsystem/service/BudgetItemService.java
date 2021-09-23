package com.varejonline.budgetsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varejonline.budgetsystem.model.Budget;
import com.varejonline.budgetsystem.model.BudgetItem;
import com.varejonline.budgetsystem.repository.BudgetItemRepository;
import com.varejonline.budgetsystem.repository.BudgetRepository;

@Service
public class BudgetItemService {

	@Autowired
	private BudgetItemRepository budgetItemRepository;
	
	@Autowired
	private BudgetRepository budgetRepository;
	
	/**
	 * Method to fetch all budgets items in the database
	 * @return Object list with all budgets items
	 */
	public List<BudgetItem> findAll() {
		return budgetItemRepository.findAll();
	}
	
	/**
	 * Method to fetch a budget item by ID in the database
	 * @param id
	 * @return Optional with a budget item
	 */
	public Optional<BudgetItem> findById(Long id) {
		return budgetItemRepository.findById(id);
	}
	
	/**
	 * Method to save a budgets items in the database
	 * @param budgetItem
	 * @return Optional with a budget item created
	 */
	public Optional<BudgetItem> save(BudgetItem budgetItem) {
		Optional<Budget> existingBudget = budgetRepository.findById(budgetItem.getBudget().getId());
		
		if (existingBudget.isPresent()) {
			return Optional.of(budgetItemRepository.save(budgetItem));
		} else {
			return Optional.empty();
		}
	}
	
	/**
	 * Method to update a budget item in the database
	 * @param budgetItemUpdate
	 * @return Optional with a budget item updated
	 */
	public BudgetItem update(BudgetItem budgetItemUpdate) {
		return budgetItemRepository.save(budgetItemUpdate);
	}
	
	/**
	 * Method to delete a budget item in the database
	 * @param id
	 * @return Optional empty or null
	 */
	public Optional<?> delete(Long id) {
		if (budgetItemRepository.findById(id).isPresent()) {
			budgetItemRepository.deleteById(id);
		} else {
			return Optional.empty();
		}
		return null;
 	}
}