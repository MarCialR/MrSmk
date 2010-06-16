package com.mru.mrnicoquitter.beans;

public class Day implements Comparable<Day>{
private int dayNumber;
private int cigarCount;
private double previousDaySaved;
private int maxCigarsToday;
public int getDayNumber() {
	return dayNumber;
}
public void setDayNumber(int dayNumber) {
	this.dayNumber = dayNumber;
}
public int getCigarCount() {
	return cigarCount;
}
public void setCigarCount(int cigarCount) {
	this.cigarCount = cigarCount;
}

public double getPreviousDaySaved() {
	return previousDaySaved;
}
public void setPreviousDaySaved(double previousDaySaved) {
	this.previousDaySaved = previousDaySaved;
}
public int getMaxCigarsToday() {
	return maxCigarsToday;
}
public void setMaxCigarsToday(int maxCigarsToday) {
	this.maxCigarsToday = maxCigarsToday;
}
@Override
public int compareTo(Day another) {
	return this.getDayNumber()-another.getDayNumber();
}

}
