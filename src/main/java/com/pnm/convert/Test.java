package com.pnm.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	
	public static void main(String args[]){
		String val = "Thu Jul 16 00:00:00 ICT 2015";
		Date d = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss zz yyyy");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(d.toString());
		System.out.println(sdf.format(d));
		try {
			Date d1 = sdf.parse(val);
			System.out.println("d1="+d1.toString());
			System.out.println(sdf2.format(d1));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
