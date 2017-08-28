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
	  //    cal.set
	      return cal.getTime();
		}
}
