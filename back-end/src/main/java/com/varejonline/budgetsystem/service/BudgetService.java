package com.varejonline.budgetsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.varejonline.budgetsystem.model.Budget;
import com.varejonline.budgetsystem.repository.BudgetRepository;

@Service
public class BudgetService {

	@Autowired
	private BudgetRepository budgetRepository;
	
	/**
	 * Method to fetch all budgets in the database
	 * @return Object list with all budgets
	 */
	public List<Budget> findAll() {
		return budgetRepository.findAll();
	}
	
	/**
	 * Method to fetch a budget by ID in the database
	 * @param id
	 * @return Optional with a budget
	 */
	public Optional<Budget> findById(Long id) {
		return budgetRepository.findById(id);
	}
	
	/**
	 * Method to save a budget in the database
	 * @param budget
	 * @return Optional with a budget created
	 */
	public Budget save(Budget budget) {
		return budgetRepository.save(budget);
	}
	
	/**
	 * Method to update a budget in the database
	 * @param budgetUpdate
	 * @return Optional with the saved object or an empty object
	 */
	public Optional<?> update(Budget budgetUpdate) {
		return budgetRepository.findById(budgetUpdate.getId()).map(existing -> {
			if (existing.getClientName().equals(budgetUpdate.getClientName())
					&& existing.getValidity() == budgetUpdate.getValidity()) {
				return Optional.of(budgetRepository.save(budgetUpdate));
			} else {
				return Optional.empty();
			}
		}).orElseGet(() -> {
			return Optional.empty();
		});
	}
	
	/**
	 * Method to delete a budget in the database
	 * @param id
	 * @return Optional empty or null
	 */
	public Optional<?> delete(Long id) {
		if (budgetRepository.findById(id).isPresent()) {
			budgetRepository.deleteById(id);
		} else {
			return Optional.empty();
		}
		return null;
 	}
}