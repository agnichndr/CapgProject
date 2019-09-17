package com.capgemini.go.model;

import java.io.Serializable;

public class SalesRep extends User implements Serializable {

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Target == null) ? 0 : Target.hashCode());
		result = prime * result + ((currentSales == null) ? 0 : currentSales.hashCode());
		result = prime * result + ((sBonus == null) ? 0 : sBonus.hashCode());
		result = prime * result + ((targetSales == null) ? 0 : targetSales.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SalesRep other = (SalesRep) obj;
		if (Target == null) {
			if (other.Target != null)
				return false;
		} else if (!Target.equals(other.Target))
			return false;
		if (currentSales == null) {
			if (other.currentSales != null)
				return false;
		} else if (!currentSales.equals(other.currentSales))
			return false;
		if (sBonus == null) {
			if (other.sBonus != null)
				return false;
		} else if (!sBonus.equals(other.sBonus))
			return false;
		if (targetSales == null) {
			if (other.targetSales != null)
				return false;
		} else if (!targetSales.equals(other.targetSales))
			return false;
		return true;
	}

	private Double sBonus;
	private String Target;
	private Double currentSales;
	private Double targetSales;
	private static final long serialVersionUID = 7551990648936522523L;

	public SalesRep(String userId, String userName, String userMail, long userNumber, boolean activeStatus,
			String password, int userCategory, Double sBonus, String target, Double currentSales, Double targetSales) {
		super(userId, userName, userMail, userNumber, activeStatus, password, userCategory);
		this.sBonus = sBonus;
		Target = target;
		this.currentSales = currentSales;
		this.targetSales = targetSales;
	}

	public Double getsBonus() {
		return sBonus;
	}

	public void setsBonus(Double sBonus) {
		this.sBonus = sBonus;
	}

	public Double getCurrentSales() {
		return currentSales;
	}

	public void setCurrentSales(Double currentSales) {
		this.currentSales = currentSales;
	}

	public Double getTargetSales() {
		return targetSales;
	}

	public void setTargetSales(Double targetSales) {
		this.targetSales = targetSales;
	}

	public String getTarget() {
		return Target;
	}

	public void setTarget(String target) {
		Target = target;
	}

	@Override
	public String toString() {
		return "SalesRep [sBonus=" + sBonus + ", Target=" + Target + ", currentSales=" + currentSales + ", targetSales="
				+ targetSales + ", getsBonus()=" + getsBonus() + ", getCurrentSales()=" + getCurrentSales()
				+ ", getTargetSales()=" + getTargetSales() + ", getTarget()=" + getTarget() + ", getUserCategory()="
				+ getUserCategory() + ", getUserId()=" + getUserId() + ", getUserName()=" + getUserName()
				+ ", getUserMail()=" + getUserMail() + ", getUserNumber()=" + getUserNumber() + ", isActiveStatus()="
				+ isActiveStatus() + ", getPassword()=" + getPassword() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

}
