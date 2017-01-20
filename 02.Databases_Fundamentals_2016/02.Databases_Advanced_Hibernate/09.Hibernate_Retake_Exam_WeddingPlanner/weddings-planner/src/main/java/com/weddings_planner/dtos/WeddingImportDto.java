package com.weddings_planner.dtos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeddingImportDto implements Serializable {

	@Expose
	@SerializedName(value = "Bride")
	@NotNull
	private String brideFullName;
	
	@Expose
	@SerializedName(value = "Bridegroom")
	@NotNull
	private String bridegRoomFullName;
	
	@Expose
	@SerializedName(value = "Date")
	@NotNull
	private String date;
	
	@Expose
	@SerializedName(value = "Agency")
	@NotNull
	private String agencyName;
	
	@Expose
	@SerializedName(value = "Guests")
	private List<InvitationImportDto> guests;
	
	public WeddingImportDto() {
		this.guests = new ArrayList<>();
	}

	public String getBrideFullName() {
		return brideFullName;
	}

	public void setBrideFullName(String brideFullName) {
		this.brideFullName = brideFullName;
	}

	public String getBridegRoomFullName() {
		return bridegRoomFullName;
	}

	public void setBridegRoomFullName(String bridegRoomFullName) {
		this.bridegRoomFullName = bridegRoomFullName;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAgencyName() {
		return agencyName;
	}

	public void setAgencyName(String agencyName) {
		this.agencyName = agencyName;
	}

	public List<InvitationImportDto> getGuests() {
		return guests;
	}

	public void setGuests(List<InvitationImportDto> guests) {
		this.guests = guests;
	}
}
