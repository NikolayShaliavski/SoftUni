package com.weddings_planner.entities.presents;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.weddings_planner.enums.Size;

@Entity
@DiscriminatorValue(value = "gift")
public class Gift extends Present implements Serializable {

	@Column(name = "name", nullable = false)
	private String name;

	@Column(name = "size")
	@Enumerated(value = EnumType.STRING)
	private Size size;

	public Gift() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Size getSize() {
		return size;
	}

	public void setSize(Size size) {
		this.size = size;
	}
}
