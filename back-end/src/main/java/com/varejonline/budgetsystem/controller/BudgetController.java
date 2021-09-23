package com.varejonline.budgetsystem.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.varejonline.budgetsystem.model.Budget;
import com.varejonline.budgetsystem.service.BudgetService;

@RestController
@RequestMapping("/budgets")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BudgetController {

	@Autowired
	private BudgetService budgetService;
	
	@GetMapping
	public ResponseEntity<List<Budget>> findAll() {
		List<Budget> result = budgetService.findAll();
		
		if (result.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(result);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Budget> findById(@PathVariable Long id) {
		Optional<Budget> result = budgetService.findById(id);
		
		if (result.isPresent()) {
			return ResponseEntity.status(200).body(result.get());
		} else {
			return ResponseEntity.status(204).build();	
		}
	}
	
	@PostMapping
	public ResponseEntity<Budget> save(@Valid @RequestBody Budget budget) {
		return ResponseEntity.status(200).body(budgetService.save(budget));
	}
	
	@PutMapping
	public ResponseEntity<Object> update(@Valid @RequestBody Budget budgetUpdate) {
		Optional<?> result = budgetService.update(budgetUpdate);
		
		if (result.isPresent()) {
			return ResponseEntity.status(200).body(result.get());
		} else {
			return ResponseEntity.status(400).build();
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		Optional<?> response = budgetService.delete(id);
		
		if (response == null) {
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(404).build();
		}
	}
}
