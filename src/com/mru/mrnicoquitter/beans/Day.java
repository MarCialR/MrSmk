package com.mru.mrnicoquitter.beans;

public class Day implements Comparable<Day>{
private int dayNumber;
private int cigarCount;
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
@Override
public int compareTo(Day another) {
	return this.getDayNumber()-another.getDayNumber();
}

}
