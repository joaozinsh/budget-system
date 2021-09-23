package com.varejonline.budgetsystem.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_budget_item")
public class BudgetItem implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank
	private String name;
	@NotNull
	private Integer quantityParts;
	@NotNull
	private Double unitaryValue;
	@NotNull
	private Double percentDiscount;
	
	@NotNull
	@ManyToOne
	@JsonIgnoreProperties(value = "items")
	private Budget budget;
	
	public BudgetItem() {
	}
	
	public BudgetItem(Long id, String name, Integer quantityParts, Double unitaryValue, Double percentDiscount,
			Budget budget) {
		this.id = id;
		this.name = name;
		this.quantityParts = quantityParts;
		this.unitaryValue = unitaryValue;
		this.percentDiscount = percentDiscount;
		this.budget = budget;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantityParts() {
		return quantityParts;
	}

	public void setQuantityParts(Integer quantityParts) {
		this.quantityParts = quantityParts;
	}

	public Double getUnitaryValue() {
		return unitaryValue;
	}

	public void setUnitaryValue(Double unitaryValue) {
		this.unitaryValue = unitaryValue;
	}

	public Double getPercentDiscount() {
		return percentDiscount;
	}

	public void setPercentDiscount(Double percentDiscount) {
		this.percentDiscount = percentDiscount;
	}

	public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}
	
	public double getSubTotal() {
		double total = unitaryValue * quantityParts;
		return total - (total * (percentDiscount / 100));
	}
}
