package com.weddings_planner.entities.presents;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "cash")
public class Cash extends Present implements Serializable {

	@Column(name = "amount", nullable = false)
	private BigDecimal amount;

	public Cash() {
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
