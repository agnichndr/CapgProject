package com.capgemini.go.utility;



public class GoUtility {
  
  
	public static boolean validatePhoneNumber(Long number)
	{
		String s = Long.toString(number);
		s = s.trim();
		if(s.length() == 10)
		{
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	
	 public static boolean isValid(String email) {
	      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      return email.matches(regex);
	 }
	
   
}