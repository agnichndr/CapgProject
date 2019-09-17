package com.capgemini.go.model;

import java.io.Serializable;

public class Report_Revenue implements Serializable {
	private double totalMonth;
	private double totalQuarter;
	private double totalYear;
	private double prevMonth;
	private double prevQuarter;
	private double prevYear;
	private double percentGrowthMonthly;
	private double percentGrowthQuarter;
	private double percentGrowthYear;
	private static final long serialVersionUID = 7551999649936529526L;

	public double getTotalMonth() {
		return totalMonth;
	}

	public void setTotalMonth(double totalMonth) {
		this.totalMonth = totalMonth;
	}

	public double getTotalQuarter() {
		return totalQuarter;
	}

	public void setTotalQuarter(double totalQuarter) {
		this.totalQuarter = totalQuarter;
	}

	public double getTotalYear() {
		return totalYear;
	}

	public void setTotalYear(double totalYear) {
		this.totalYear = totalYear;
	}

	public double getPrevMonth() {
		return prevMonth;
	}

	public void setPrevMonth(double prevMonth) {
		this.prevMonth = prevMonth;
	}

	public double getPrevQuarter() {
		return prevQuarter;
	}

	public void setPrevQuarter(double prevQuarter) {
		this.prevQuarter = prevQuarter;
	}

	public double getPrevYear() {
		return prevYear;
	}

	public void setPrevYear(double prevYear) {
		this.prevYear = prevYear;
	}

	public double getPercentGrowthMonthly() {
		return percentGrowthMonthly;
	}

	public void setPercentGrowthMonthly(double percentGrowthMonthly) {
		this.percentGrowthMonthly = percentGrowthMonthly;
	}

	public double getPercentGrowthQuarter() {
		return percentGrowthQuarter;
	}

	public void setPercentGrowthQuarter(double percentGrowthQuarter) {
		this.percentGrowthQuarter = percentGrowthQuarter;
	}

	public double getPercentGrowthYear() {
		return percentGrowthYear;
	}

	public void setPercentGrowthYear(double percentGrowthYear) {
		this.percentGrowthYear = percentGrowthYear;
	}

	// Constructor for Revenue Reports
	public Report_Revenue(double totalMonth, double totalQuarter, double totalYear, double prevMonth,
			double prevQuarter, double prevYear, double percentGrowthMonthly, double percentGrowthQuarter,
			double percentGrowthYear) {
		super();
		this.totalMonth = totalMonth;
		this.totalQuarter = totalQuarter;
		this.totalYear = totalYear;
		this.prevMonth = prevMonth;
		this.prevQuarter = prevQuarter;
		this.prevYear = prevYear;
		this.percentGrowthMonthly = percentGrowthMonthly;
		this.percentGrowthQuarter = percentGrowthQuarter;
		this.percentGrowthYear = percentGrowthYear;
	}

}
