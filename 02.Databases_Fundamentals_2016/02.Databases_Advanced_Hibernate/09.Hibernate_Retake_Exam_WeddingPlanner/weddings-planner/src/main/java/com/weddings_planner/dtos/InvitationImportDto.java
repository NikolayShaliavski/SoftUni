package com.weddings_planner.dtos;

import java.io.Serializable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.weddings_planner.enums.Family;

public class InvitationImportDto implements Serializable {

	@Expose
	@SerializedName(value = "Name")
	private String guestName;
	
	@Expose
	@SerializedName(value = "RSVP")
	private Boolean attending;
	
	@Expose
	@SerializedName(value = "Family")
	private Family family;

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}

	public Boolean getAttending() {
		return attending;
	}

	public void setAttending(Boolean attending) {
		this.attending = attending;
	}

	public Family getFamily() {
		return family;
	}

	public void setFamily(Family family) {
		this.family = family;
	}
}
