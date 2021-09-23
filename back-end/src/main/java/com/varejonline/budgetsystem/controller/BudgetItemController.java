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

import com.varejonline.budgetsystem.model.BudgetItem;
import com.varejonline.budgetsystem.service.BudgetItemService;

@RestController
@RequestMapping("/budgets-items")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BudgetItemController {

	@Autowired
	private BudgetItemService budgetItemService;
	
	@GetMapping
	public ResponseEntity<List<BudgetItem>> findAll() {
		List<BudgetItem> result = budgetItemService.findAll();
		
		if (result.isEmpty()) {
			return ResponseEntity.status(204).build();
		} else {
			return ResponseEntity.status(200).body(result);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BudgetItem> findById(@PathVariable Long id) {
		Optional<BudgetItem> result = budgetItemService.findById(id);
		
		if (result.isPresent()) {
			return ResponseEntity.status(200).body(result.get());
		} else {
			return ResponseEntity.status(204).build();	
		}
	}
	
	@PostMapping
	public ResponseEntity<BudgetItem> save(@Valid @RequestBody BudgetItem budgetItem) {
		Optional<BudgetItem> result = budgetItemService.save(budgetItem);
		
		if (result.isPresent()) {
			return ResponseEntity.status(200).body(result.get());
		} else {
			return ResponseEntity.status(400).build();	
		}
	}
	
	@PutMapping
	public ResponseEntity<BudgetItem> update(@Valid @RequestBody BudgetItem budgetItem) {
		return ResponseEntity.status(200).body(budgetItemService.update(budgetItem));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		Optional<?> response = budgetItemService.delete(id);
		
		if (response == null) {
			return ResponseEntity.status(200).build();
		} else {
			return ResponseEntity.status(404).build();
		}
	}
}