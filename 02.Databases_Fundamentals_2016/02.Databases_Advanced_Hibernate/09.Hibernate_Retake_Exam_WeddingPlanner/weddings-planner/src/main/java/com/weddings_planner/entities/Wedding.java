package com.weddings_planner.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "weddings")
public class Wedding implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "bride", referencedColumnName = "id", nullable = false)
	private Person bride;

	@OneToOne
	@JoinColumn(name = "bridegroom", referencedColumnName = "id", nullable = false)
	private Person bridegroom;

	@Column(name = "date", nullable = false)
	private Date date;

	@ManyToOne
	@JoinColumn(name = "agency", referencedColumnName = "id", nullable = false)
	private Agency agency;

	public Wedding() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Person getBride() {
		return bride;
	}

	public void setBride(Person bride) {
		this.bride = bride;
	}

	public Person getBridegroom() {
		return bridegroom;
	}

	public void setBridegroom(Person bridegroom) {
		this.bridegroom = bridegroom;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Agency getAgency() {
		return agency;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}
}
