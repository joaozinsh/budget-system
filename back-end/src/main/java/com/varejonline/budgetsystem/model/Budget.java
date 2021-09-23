package com.varejonline.budgetsystem.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_budget")
public class Budget implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Integer validity;
	@NotBlank
	private String clientName;
	@NotBlank
	private String clientAddress;
	@NotNull
	private String comments;
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime creationDate = LocalDateTime.now();
	
	@NotNull
	@ManyToOne
	@JsonIgnoreProperties(value = {"password", "loggedIn"})
	private User user;
	
	@OneToMany(mappedBy = "budget", cascade = CascadeType.ALL)
	@JsonIgnoreProperties(value = "budget")
	private List<BudgetItem> items = new ArrayList<>();
	
	public Budget() {
	}

	public Budget(Long id, Integer validity, String clientName, String clientAddress, String comments, User user) {
		this.id = id;
		this.validity = validity;
		this.clientName = clientName;
		this.clientAddress = clientAddress;
		this.comments = comments;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientAddress() {
		return clientAddress;
	}

	public void setClientAddress(String clientAddress) {
		this.clientAddress = clientAddress;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<BudgetItem> getItems() {
		return items;
	}

	public double getTotal() {
		double total = 0.0;
		for (BudgetItem item : items) {
			total += item.getSubTotal();
		}
		return total;
	}
	
	public int getQuantityItems() {
		return items.size();
	}
	
	public int getQuantityParts() {
		int total = 0;
		for (BudgetItem item : items) {
			total += item.getQuantityParts();
		}
		return total;
	}
	
	public int getDaysUntilExpires() {
		LocalDate presentDay = LocalDate.now();
		int expirationDay =  creationDate.getDayOfMonth() + (int) validity;
		return expirationDay - presentDay.getDayOfMonth();
	}
}
