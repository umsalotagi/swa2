package com.swapasya.controller;

import java.util.Calendar;
import java.util.Date;

public class Utility {

	public static void main(String[] args) {
		
		
		 Date x= dayStartTime(2017,7,28);
		 Date y= dayEndTime(2017,7,28);
		 Date z= forToday();
		 Date x1= addLibDays(y, 7);
		 
		System.out.println("dayStartTime: "+x);
		System.out.println("dayEndTime: "+y);
		System.out.println("forToday: "+z);
		System.out.println("Return before: "+ x1);
		System.out.println("Diff IS "+  daysBetween(z,y));
	}

	public static Date dayStartTime(int year, int monthfrom0, int date){
	      Calendar cal = Calendar.getInstance();
	      cal.set(year,monthfrom0,date,0,0,0);	      
	      return cal.getTime();
		}	
	
	
	public static Date dayEndTime(int year, int monthfrom0, int date){
	      Calendar cal = Calendar.getInstance();
	      cal.set(year,monthfrom0,date,23,59,59);
	      return cal.getTime();
		}
	
	public static  Date forToday(){
	      Calendar cal = Calendar.getInstance();
	      int year=cal.get(cal.YEAR);
	      int month=cal.get(cal.MONTH);
	      int date=cal.get(cal.DATE);
	      cal.set(year, month, date,0,0,1);
	      System.out.println("SET time to: "+cal.getTime());
	      return cal.getTime();
		}
	
	public static  Date addLibDays(Date date, int days){
	      Calendar cal = Calendar.getInstance();
	      cal.setTime(date);
	      cal.add(Calendar.DATE, days);
	      return cal.getTime();
		}
	
//	public static void diffDays(Date datex, Date datey){
//	      Calendar cal1 = Calendar.getInstance();
//	      cal1.setTime(datex);
//	      Calendar cal2= Calendar.getInstance();
//	      cal2.setTime(datey);
//	      System.out.println("date one is: "+cal1.get(cal1.DATE));
//	      System.out.println("date second is: "+cal2.get(cal2.DATE));
//	      System.out.println("compare is: "+cal1.compareTo(cal2));
//
//		}
	
	public static int daysBetween(Date day1, Date day2){
	    Calendar dayOne=Calendar.getInstance();
	    dayOne.setTime(day1);
	    Calendar dayTwo=Calendar.getInstance();
	    dayTwo.setTime(day2);

	    if (dayOne.get(Calendar.YEAR) == dayTwo.get(Calendar.YEAR)) {
	        return Math.abs(dayOne.get(Calendar.DAY_OF_YEAR) - dayTwo.get(Calendar.DAY_OF_YEAR));
	    } else {
	        if (dayTwo.get(Calendar.YEAR) > dayOne.get(Calendar.YEAR)) {
	            //swap them
	            Calendar temp = dayOne;
	            dayOne = dayTwo;
	            dayTwo = temp;
	        }
	        int extraDays = 0;

	        int dayOneOriginalYearDays = dayOne.get(Calendar.DAY_OF_YEAR);

	        while (dayOne.get(Calendar.YEAR) > dayTwo.get(Calendar.YEAR)) {
	            dayOne.add(Calendar.YEAR, -1);
	            // getActualMaximum() important for leap years
	            extraDays += dayOne.getActualMaximum(Calendar.DAY_OF_YEAR);
	        }

	        return extraDays - dayTwo.get(Calendar.DAY_OF_YEAR) + dayOneOriginalYearDays ;
	    }
	}
}
